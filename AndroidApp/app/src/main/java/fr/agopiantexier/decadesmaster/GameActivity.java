package fr.agopiantexier.decadesmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import java.util.List;

import static fr.agopiantexier.decadesmaster.SpotifyResponse.testaa;

public class GameActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Intent intent = getIntent();
        String room = intent.getStringExtra("room");
        List<Item> a = testaa();

            if(room.equals("Année 1970")){

            }


    }
}
