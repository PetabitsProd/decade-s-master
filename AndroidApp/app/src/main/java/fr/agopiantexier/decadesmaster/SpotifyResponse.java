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
import java.util.Arrays;
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
            AllPlaylists allPlaylists;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, String.valueOf(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                FileOutputStream outputStream;
                Items items;
                String content = response.body().string();
                ObjectReader reader = mapper.reader().forType(AllPlaylists.class);
                allPlaylists = reader.readValue(content);
                for(Integer i =0; i < allPlaylists.getAllPlaylist().size(); i++){
                    items = allPlaylists.getAllPlaylist().get(i);
                    if(i  <=4){

                        File file= new File(context.getFilesDir(), "playlistId1970.json");
                        if(file.length() == 0){
                            FileWriter fileWriter = new FileWriter(file, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            fileWriter.append(mapper.writeValueAsString(items));
                        }

                    }
                    else if ( i <= 11){
                        File file= new File(context.getFilesDir(), "playlistId1980.json");
                        if(file.length() == 0){
                            FileWriter fileWriter = new FileWriter(file, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            fileWriter.append(mapper.writeValueAsString(items));
                        }

                    }
                    else if( i <=15){
                        File file= new File(context.getFilesDir(), "playlistId1990.json");
                        if(file.length() == 0){
                            FileWriter fileWriter = new FileWriter(file, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            fileWriter.append(mapper.writeValueAsString(items));
                        }

                    }
                    else if ( i <= 21){
                        File file= new File(context.getFilesDir(), "playlistId2000.json");
                        if(file.length() != 0){
                            FileWriter fileWriter = new FileWriter(file, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            fileWriter.append(mapper.writeValueAsString(items));
                        }

                    }else{
                        File file= new File(context.getFilesDir(), "playlistId2010.json");
                        if(file.length() != 0){
                            FileWriter fileWriter = new FileWriter(file, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            fileWriter.append(mapper.writeValueAsString(items));
                        }

                    }
                }
                System.out.print(allPlaylists);

             //   items = reader.readValue(content);


            }
        });



    }

    protected static void testaa(Context context){
        ObjectMapper objectMapper = new ObjectMapper();
        FileOutputStream outputStream;

        try{
            Items items = objectMapper.readValue(new File(context.getFilesDir(), "playlistId1990.json"), Items.class);
            List<Items> itemsList = Arrays.asList(items);


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
