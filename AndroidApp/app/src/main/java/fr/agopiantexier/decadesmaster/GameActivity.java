package fr.agopiantexier.decadesmaster;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static fr.agopiantexier.decadesmaster.SpotifyResponse.getAllPlaylist;
import static fr.agopiantexier.decadesmaster.SpotifyResponse.testaa;

public class GameActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Intent intent = getIntent();
        String room = intent.getStringExtra("room");
        MediaPlayer mediaPlayer = new MediaPlayer();

        List<Item> a = testaa();
        Collections.shuffle(a);
        List<String> url =new ArrayList<>();
        Integer index = 0;
        if(room.equals("Année 1970")){
            for(Integer i = 0; i< a.size(); i++){
                if(index < 16){
                    if(a.get(i).getTrack().getPreviewUrls() != null){
                        url.add(a.get(i).getTrack().getPreviewUrls());
                        index ++;

                    }
                }else
                    {
                    break;
                }

            }


        }
        try{

            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(url.get(0));
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
            mediaPlayer.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }







    }
}
