package fr.agopiantexier.decadesmaster;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ListView;

import static fr.agopiantexier.decadesmaster.SpotifyResponse.testaa;

public class PlaylistActivity extends Activity {
    private static final String TAG = "PlaylistActivity";
    PlaylistAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlists_page);

        testaa(this);
        adapter = new PlaylistAdapter(this);
        ListView listView = findViewById(R.id.playlistListView);
        listView.setAdapter(adapter);

    }
}
