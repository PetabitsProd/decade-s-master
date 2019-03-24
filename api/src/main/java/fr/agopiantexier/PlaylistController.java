package fr.agopiantexier;

import io.javalin.Context;

public class PlaylistController {
    public static void getPlaylist(Context context){
        try{
            context.json(SpotifyFinalPlaylists.getAllPlaylists());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
