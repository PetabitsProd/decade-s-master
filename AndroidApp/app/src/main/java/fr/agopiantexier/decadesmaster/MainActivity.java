package fr.agopiantexier.decadesmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.stetho.json.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = "OkHttp";
    private String url = "http://192.168.2.11:7000/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (sharedPreferences.contains("pseudo") && sharedPreferences.contains("password")) {

            JSONObject myPrefObject = new JSONObject();

            try {
                myPrefObject.put("pseudo", sharedPreferences.getString("pseudo", null));
                myPrefObject.put("password", sharedPreferences.getString("password", null));
            } catch (Exception e){
            }

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, myPrefObject.toString());
            Request request = new Request.Builder()
                    .url(url + "connexion")
                    .post(body)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    loginTry();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        gettingStarted();
                    }
                }
            });

        } else {
            loginTry();




            /*OkHttpClient okHttpClient = new OkHttpClient();
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

            });*/
        }
    }

    public void gettingStarted() {
        Intent intent = new Intent(this, GettingStarted.class);
        startActivity(intent);
    }

    public void loginTry() {
        final EditText pseudo = findViewById(R.id.pseudo);
        final EditText password = findViewById(R.id.password);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        final Button bConnexion = findViewById(R.id.connexion);
        bConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JSONObject object = new JSONObject();

                try {
                    object.put("pseudo", pseudo.getText().toString());
                    object.put("password", password.getText().toString());
                } catch (Exception e) {
                }


                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, object.toString());
                Request request = new Request.Builder()
                        .url(url + "connexion")
                        .post(body)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("pseudo", pseudo.getText().toString());
                            editor.putString("password", password.getText().toString());
                            editor.apply();
                            gettingStarted();

                            Log.i("Test", sharedPreferences.getString("pseudo", null));
                            Log.i("Test", sharedPreferences.getString("password", null));
                        } else {
                        }
                    }
                });

            }
        });

        final Button bInscription = findViewById(R.id.inscription);
        bInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject object = new JSONObject();
                try {
                    object.put("pseudo", pseudo.getText().toString());
                    object.put("password", password.getText().toString());
                } catch (Exception e) {
                }


                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, object.toString());
                Request request = new Request.Builder()
                        .url(url + "inscription")
                        .post(body)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("pseudo", pseudo.getText().toString());
                        editor.putString("password", password.getText().toString());
                        editor.apply();
                        gettingStarted();
                    }
                });
            }
        });
    }
}
