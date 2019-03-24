package fr.agopiantexier;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class SpotifyFinalPlaylists {

    public SpotifyFinalPlaylists() {
        super();
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static AllPlaylists getAllPlaylists() throws Exception {
        List<Items> items = new ArrayList<>();
        AllPlaylists allPlaylists = new AllPlaylists();

        items.addAll(getPlaylist("OutputPlaylistsFile/playlistId1970.json"));
        items.addAll(getPlaylist("OutputPlaylistsFile/playlistId1980.json"));
        items.addAll(getPlaylist("OutputPlaylistsFile/playlistId1990.json"));
        items.addAll(getPlaylist("OutputPlaylistsFile/playlistId2000.json"));
        items.addAll(getPlaylist("OutputPlaylistsFile/playlistId2010.json"));
        allPlaylists.setAllPlaylist(items);

        return allPlaylists;
    }

    public static List<Items> getPlaylist(String pathname) throws Exception {
        Items[] items = objectMapper.readValue(new File(pathname), Items[].class);
        List<Items> itemsList = Arrays.asList(items);

        for (int i = 0; i < items.length; i++) {
            List<Item> item = items[i].getItems();
            for (int j = 0; j < item.size(); j++) {
                if (item.get(j).getTrack().getPreviewUrls() == null) {
                    itemsList.remove(item.get(i));

                }
            }
        }

        Set<String> dejaVus = new HashSet<>();
        itemsList.stream().forEach(currentItems -> {
            System.out.println("a" +currentItems.getItems().size());
            List<Item> newList = currentItems.getItems().stream().filter(item -> {
                if (dejaVus.contains(item.getTrack().getName())) {
                    return false;
                }
                else {
                    dejaVus.add(item.getTrack().getName());
                    return true;
                }
            }).collect(Collectors.toList());
            currentItems.setItems(newList);
            System.out.println("b" +currentItems.getItems().size());

        });

        return itemsList;
    }
}
