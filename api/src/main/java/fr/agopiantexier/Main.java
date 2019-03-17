package fr.agopiantexier;

import io.javalin.Javalin;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import static fr.agopiantexier.ApiConnection.refreshToken;


public class Main {

    private static String base64;

    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        //get conf for Spotify API
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("config.properties");
        prop.load(input);


        long period = 30*60000;

        //create strings to send in Spotify header
        String clientId = prop.getProperty("clientId");
        String secret = prop.getProperty("secretId");
        base64 = Base64.getEncoder().encodeToString((clientId+":"+secret).getBytes());

        refreshToken(base64);

        // Request for Spotify Token every 30mn
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){

               refreshToken(base64);
            }
        }, period, period);



        Javalin app = Javalin.create().start(7000);

        app.get("/", ApiConnection::getSpotifyResponse);

    }

}
