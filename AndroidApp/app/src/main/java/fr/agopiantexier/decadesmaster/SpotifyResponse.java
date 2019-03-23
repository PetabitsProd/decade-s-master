package fr.agopiantexier.decadesmaster;

import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

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

    protected static void getAllPlaylist(){
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

                String content = response.body().string();
                ObjectReader reader = mapper.reader().forType(AllPlaylists.class);
                allPlaylists = reader.readValue(content);
                System.out.print(allPlaylists);

             //   items = reader.readValue(content);
                Log.i(TAG, "ok playlist" + response.body().string());
                Log.i(TAG, "ok playlist" + response.body().contentLength());

            }
        });



    }
}
