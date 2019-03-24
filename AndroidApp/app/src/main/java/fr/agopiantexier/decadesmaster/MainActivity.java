package fr.agopiantexier.decadesmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.facebook.stetho.Stetho;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import fr.agopiantexier.decadesmaster.database.DatabaseHelper;
import fr.agopiantexier.decadesmaster.model.Playlist;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static fr.agopiantexier.decadesmaster.SpotifyResponse.createFile;
import static fr.agopiantexier.decadesmaster.SpotifyResponse.getAllPlaylist;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private String url = "http://172.20.10.2:7000/";
    OkHttpClient okHttpClient = new OkHttpClient();
    private  static Properties prop = new Properties();
    private static String dbPath = prop.getProperty("dbPath");
    private DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Stetho.initializeWithDefaults(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText pseudo = findViewById(R.id.pseudo);
        final EditText password = findViewById(R.id.password);

        final Button bConnexion = findViewById(R.id.connexion);
        try{
            createFile(this);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        bConnexion.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               OkHttpClient okHttpClient = new OkHttpClient();
               Request request = new Request.Builder()
                       .url(url + "connexion")
                       .build();
               okHttpClient.newCall(request).enqueue(new Callback() {
                   @Override
                   public void onFailure(Call call, IOException e) {
                       Toast toast = new Toast(getApplicationContext());
                       toast.setText("Erreur lors de la connexion");
                       toast.setDuration(Toast.LENGTH_LONG);
                       toast.show();
                   }

                   @Override
                   public void onResponse(Call call, Response response) throws IOException {
                        gettingStarted();
                   }
               });
             }
        });

        final Button bInscription = findViewById(R.id.inscription);
        bInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("pseudo", pseudo.toString())
                        .add("password", password.toString())
                        .build();
                Request request = new Request.Builder()
                        .url(url + "inscription")
                        .post(body)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        /*Toast toast = new Toast(getApplicationContext());
                        toast.setText("Erreur lors de l'inscription");
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();*/
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        gettingStarted();
                    }
                });
            }
        });


        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, String.valueOf(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, String.valueOf(response));

            }

        });


    }

    public void gettingStarted() {
        Intent intent = new Intent(this, PlaylistActivity.class);
        startActivity(intent);
    }


}
