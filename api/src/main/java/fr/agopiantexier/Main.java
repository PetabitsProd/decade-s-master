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


    public static void main(String[] args) throws Exception {

        Timer timer = new Timer();

        Properties prop = new Properties();
        InputStream input = null;

        input = new FileInputStream("config.properties");
        prop.load(input);

        System.out.println(prop.getProperty("clientId"));


        long period = 30*60000;
        String clientId = prop.getProperty("clientId");
        String secret = prop.getProperty("secretId");
         ObjectMapper mapper = new ObjectMapper();
         mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        base64 = Base64.getEncoder().encodeToString((clientId+":"+secret).getBytes());

        // Request for Spotify Token every 30mb
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){

                Request request = new Request.Builder()
                        .url("https://accounts.spotify.com/api/token")
                        .header("Content-Type","x-www-form-urlencoded")
                        .header("Authorization","Basic "+base64 )
                        .post(new FormBody.Builder().add("grant_type","client_credentials").build())
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    String content = response.body().string();
                    ObjectReader reader = mapper.reader().forType((GetToken.class));
                    GetToken token = reader.readValue(content);
                    System.out.println("token" + token);

                    System.out.println(content);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }, 1, period);




        Javalin app = Javalin.create().start(7000);

        app.get("/", Main::accueil);


    }

    public static void accueil(Context ctx) {

            ctx.result(":)");
    }


}
