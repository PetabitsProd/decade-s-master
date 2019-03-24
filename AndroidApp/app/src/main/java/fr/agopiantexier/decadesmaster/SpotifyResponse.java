package fr.agopiantexier.decadesmaster;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.j256.ormlite.stmt.query.In;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SpotifyResponse {
    private static  String TAG = "SpotifyResponse";
    private Properties properties = new Properties();
    private String dbPath = properties.getProperty("dbPath");

    private static List<Items> items2010 = new ArrayList<>();
    private static List<Items> items2000 = new ArrayList<>();
    private static List<Items> items1990 = new ArrayList<>();
    private static List<Items> items1980 = new ArrayList<>();
    private static List<Items> items1970 = new ArrayList<>();

    public List<Items> getItems2010() {
        return items2010;
    }

    public void setItems2010(List<Items> items2010) {
        this.items2010 = items2010;
    }

    public static List<Items> getItems2000() {
        return items2000;
    }

    public void setItems2000(List<Items> items2000) {
        this.items2000 = items2000;
    }

    public static List<Items> getItems1990() {
        return items1990;
    }

    public void setItems1990(List<Items> items1990) {
        this.items1990 = items1990;
    }

    public static List<Items> getItems1980() {
        return items1980;
    }

    public void setItems1980(List<Items> items1980) {
        this.items1980 = items1980;
    }

    public static List<Items> getItems1970() {
        return items1970;
    }

    public void setItems1970(List<Items> items1970) {
        this.items1970 = items1970;
    }

    protected static void createFile(Context context) throws Exception{
        File file= new File(context.getFilesDir(), "playlistId2010.json");
        if(!file.exists()){
            file.createNewFile();
        }
        file= new File(context.getFilesDir(), "playlistId2000.json");
        if(!file.exists()){
            file.createNewFile();
        }
        file= new File(context.getFilesDir(), "playlistId1990.json");
        if(!file.exists()){
            file.createNewFile();
        }
        file= new File(context.getFilesDir(), "playlistId1880.json");
        if(!file.exists()){
            file.createNewFile();
        }
        file= new File(context.getFilesDir(), "playlistId1970.json");
        if(!file.exists()){
            file.createNewFile();
        }

    }

    protected static void getAllPlaylist(Context context){
        String url = "http://172.20.10.2:7000/playlist";
        OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, String.valueOf(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                FileOutputStream outputStream;
                AllPlaylists allPlaylists;

                Items items;
                String content = response.body().string();
                ObjectReader reader = mapper.reader().forType(AllPlaylists.class);
                allPlaylists = reader.readValue(content);
                for(Integer i =0; i < allPlaylists.getAllPlaylist().size(); i++){
                    items = allPlaylists.getAllPlaylist().get(i);
                    if(i  <=4){

                        items1970.add(items);

                    }
                    else if ( i <= 11){
                        items1980.add(items);

                    }
                    else if( i <=15){
                        items1990.add(items);


                    }
                    else if ( i <= 21){
                        items2000.add(items);

                    }else{
                        items2010.add(items);


                    }
                }
                System.out.print(allPlaylists);

             //   items = reader.readValue(content);


            }
        });



    }

    public static List<Item> testaa() {
        List<Item> item = new ArrayList<>();


        for (Integer i = 0; i < SpotifyResponse.getItems1970().size(); i++) {
            Items a = SpotifyResponse.getItems1970().get(i);
            for (Integer j = 0; j < a.getItems().size(); j++) {
                item.add(a.getItems().get(j));

            }


        }


        return item;
    }

}
