package fr.agopiantexier;

import java.util.ArrayList;
import java.util.List;

public class AllPlaylists {
    private List<Items> allPlaylist = new ArrayList<>();

    public List<Items> getAllPlaylist() {
        return allPlaylist;
    }

    public void setAllPlaylist(List<Items> allPlaylist) {
        this.allPlaylist = allPlaylist;
    }
}
