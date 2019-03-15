package fr.agopiantexier;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.javalin.Context;
import io.javalin.Javalin;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;


public class Main {

    private static String base64;
    private static final OkHttpClient client = new OkHttpClient();
    private static GetToken token;


    public static void main(String[] args) throws Exception {

        Timer timer = new Timer();

        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("config.properties");
        prop.load(input);

        long period = 30*60000;
        String clientId = prop.getProperty("clientId");
        String secret = prop.getProperty("secretId");
        base64 = Base64.getEncoder().encodeToString((clientId+":"+secret).getBytes());

        refreshToken();





        // Request for Spotify Token every 30mb
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){

               refreshToken();
            }
        }, period, period);



        Javalin app = Javalin.create().start(7000);

        app.get("/", Main::accueil);


    }

    public static void refreshToken() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url("https://accounts.spotify.com/api/token")
                .header("Content-Type","x-www-form-urlencoded")
                .header("Authorization","Basic "+base64 )
                .post(new FormBody.Builder().add("grant_type","client_credentials").build())
                .build();
        try (Response response = client.newCall(request).execute()) {
            String content = response.body().string();
            ObjectReader reader = mapper.reader().forType((GetToken.class));
            token = reader.readValue(content);
            System.out.println("token" + token);

            System.out.println(content);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void accueil(Context ctx) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Items test;
        String playlistId = "21THa8j9TaSGuXYNBU5tsC";
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks")
                .header("Authorization", "Bearer "+ token)
                .get()
                .build();
        try(Response response = client.newCall(request).execute()){
            String content = response.body().string();
            ObjectReader reader = mapper.reader().forType(Items.class);
            test = reader.readValue(content);
            ctx.result(mapper.writeValueAsString(test));

            System.out.println(test);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }


}
