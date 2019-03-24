package fr.agopiantexier.decadesmaster;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static fr.agopiantexier.decadesmaster.SpotifyResponse.getAllPlaylist;


public class PlaylistActivity extends Activity {
    private static final String TAG = "PlaylistActivity";
    PlaylistAdapter adapter;
    List<String > list = new ArrayList<>();
    MediaPlayer mediaplayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAllPlaylist(this);

        setContentView(R.layout.playlists_page);
        list.add("Année 2010");
        list.add("Année 2000");
        list.add("Année 1990");
        list.add("Année 1980");
        list.add("Année 1970");

        adapter = new PlaylistAdapter(list, this);
        ListView listView = findViewById(R.id.playlistListView);
        listView.setAdapter(adapter);
 /*
        }*/

    }
}
