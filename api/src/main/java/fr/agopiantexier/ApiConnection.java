package fr.agopiantexier;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.javalin.Context;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ApiConnection {

    private static InputStream playlistsId = ApiConnection.class.getResourceAsStream("/Playlists/playlists.json");


    public ApiConnection() {
        super();
    }

    private static String base64;
    private static final OkHttpClient client = new OkHttpClient();
    private static GetToken token;

    private static List<String> year2010 = new ArrayList<>();
    private static List<String> year2000 = new ArrayList<>();
    private static List<String> year1990 = new ArrayList<>();
    private static List<String> year1980 = new ArrayList<>();
    private static List<String> year1970 = new ArrayList<>();


    public static List<String> getYear2010() {
        return year2010;
    }

    public void setYear2010(List<String> year2010) {
        this.year2010 = year2010;
    }

    public static List<String> getYear2000() {
        return year2000;
    }

    public void setYear2000(List<String> year2000) {
        this.year2000 = year2000;
    }

    public static List<String> getYear1990() {
        return year1990;
    }

    public void setYear1990(List<String> year1990) {
        this.year1990 = year1990;
    }

    public static List<String> getYear1980() {
        return year1980;
    }

    public void setYear1980(List<String> year1980) {
        this.year1980 = year1980;
    }

    public static List<String> getYear1970() {
        return year1970;
    }

    public void setYear1970(List<String> year1970) {
        this.year1970 = year1970;
    }


    public static InputStream getPlaylistsid() {
        return playlistsId;
    }

    public static void setPlaylistsid(InputStream playlistsid) {
        ApiConnection.playlistsId = playlistsid;
    }

    public static void refreshToken(String base64) {
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

    public static void getJsonPlaylists() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Playlists playlists;

        if(playlistsId != null){
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(playlistsId))){
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
            }
            ObjectReader reader = mapper.reader().forType(Playlists.class);
            playlists = reader.readValue(stringBuilder.toString());
            for (Playlist playlist: playlists.getPlaylists()
                 ) {


                if( playlist.getName().equals("playlistId2010") ){
                    year2010 = playlist.getId() ;
                    System.out.println(mapper.writeValueAsString(year2010));
                }
                else if(playlist.getName().equals("playlistId2000")){
                    year2000 = playlist.getId() ;
                    System.out.println(mapper.writeValueAsString(year2000));
                }
                else if(playlist.getName().equals("playlistId1990")){
                    year1990 = playlist.getId() ;
                    System.out.println(mapper.writeValueAsString(year1990));
                }
                else if(playlist.getName().equals("playlistId1980")){
                    year1980 = playlist.getId() ;
                    System.out.println(mapper.writeValueAsString(year1980));
                }
                else if(playlist.getName().equals("playlistId1970") ){
                    year1970 = playlist.getId() ;
                    System.out.println(mapper.writeValueAsString(year1970));
                }
            }
            System.out.println(mapper.writeValueAsString(playlists));
        }
    }

    public static void getSpotify2010Response()throws Exception {
        List<Items> itemsList = new ArrayList<>();

        File file= new File("OutputPlaylistsFile/playlistId2010.json");

        if(!file.exists()){
            file.getParentFile().mkdirs();

            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Items items;

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String s: getYear2010()
            ) {
                Request request = new Request.Builder()
                        .url("https://api.spotify.com/v1/playlists/"+s+"/tracks")
                        .header("Authorization", "Bearer "+ token)
                        .get()
                        .build();
                try(Response response = client.newCall(request).execute()){
                    String content = response.body().string();
                    ObjectReader reader = mapper.reader().forType(Items.class);
                    items = reader.readValue(content);
                    itemsList.add(items);
                }
            }
            fileWriter.append(mapper.writeValueAsString(itemsList));

        }

    }

    public static void getSpotify2000Response()throws Exception {
        List<Items> itemsList = new ArrayList<>();

        File file= new File("OutputPlaylistsFile/playlistId2000.json");

        if(!file.exists()){
            file.getParentFile().mkdirs();

            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Items items;
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String s: getYear2000()
            ) {
                Request request = new Request.Builder()
                        .url("https://api.spotify.com/v1/playlists/"+s+"/tracks")
                        .header("Authorization", "Bearer "+ token)
                        .get()
                        .build();
                try(Response response = client.newCall(request).execute()){
                    String content = response.body().string();
                    ObjectReader reader = mapper.reader().forType(Items.class);
                    items = reader.readValue(content);
                    itemsList.add(items);
                }
            }
            fileWriter.append(mapper.writeValueAsString(itemsList));

        }

    }

    public static void getSpotify1990Response()throws Exception {
        List<Items> itemsList = new ArrayList<>();
        File file= new File("OutputPlaylistsFile/playlistId1990.json");


        if(!file.exists()) {
            file.getParentFile().mkdirs();

            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Items items;
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String s: getYear1990()
            ) {
                Request request = new Request.Builder()
                        .url("https://api.spotify.com/v1/playlists/"+s+"/tracks")
                        .header("Authorization", "Bearer "+ token)
                        .get()
                        .build();
                try(Response response = client.newCall(request).execute()){
                    String content = response.body().string();
                    ObjectReader reader = mapper.reader().forType(Items.class);
                    items = reader.readValue(content);
                    itemsList.add(items);
                }
            }
            fileWriter.append(mapper.writeValueAsString(itemsList));

        }


    }

    public static void getSpotify1980Response()throws Exception {
        List<Items> itemsList = new ArrayList<>();

        File file= new File("OutputPlaylistsFile/playlistId1980.json");
        if(!file.exists()){
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Items items;
            file.getParentFile().mkdirs();

            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String s: getYear1980()
            ) {
                Request request = new Request.Builder()
                        .url("https://api.spotify.com/v1/playlists/"+s+"/tracks")
                        .header("Authorization", "Bearer "+ token)
                        .get()
                        .build();
                try(Response response = client.newCall(request).execute()){
                    String content = response.body().string();
                    ObjectReader reader = mapper.reader().forType(Items.class);
                    items = reader.readValue(content);
                    itemsList.add(items);
                }
            }
            fileWriter.append(mapper.writeValueAsString(itemsList));
        }




    }

    public static void getSpotify1970Response()throws Exception {

        List<Items> itemsList = new ArrayList<>();
        File file= new File("OutputPlaylistsFile/playlist1970.json");
        if(!file.exists()){
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Items items;
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String s: getYear1970()
            ) {
                Request request = new Request.Builder()
                        .url("https://api.spotify.com/v1/playlists/"+s+"/tracks")
                        .header("Authorization", "Bearer "+ token)
                        .get()
                        .build();
                try(Response response = client.newCall(request).execute()){
                    String content = response.body().string();
                    ObjectReader reader = mapper.reader().forType(Items.class);
                    items = reader.readValue(content);
                    itemsList.add(items);
                }
            }
            fileWriter.append(mapper.writeValueAsString(itemsList));

            bufferedWriter.close();
        }



    }

    public static void getAllSpotifyResponse() throws Exception{
        getSpotify2010Response();
        getSpotify2000Response();
        getSpotify1990Response();
        getSpotify1980Response();
        getSpotify1970Response();
    }


}
