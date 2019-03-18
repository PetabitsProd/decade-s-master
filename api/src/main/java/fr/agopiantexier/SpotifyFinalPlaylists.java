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

    public static List<Items> getReal2010Playlist() throws Exception {
        Items[] items = objectMapper.readValue(new File("OutputPlaylistsFile/playlistId2010.json"), Items[].class);
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

    public static List<Items> getReal2000Playlist() throws Exception {
        Items[] items = objectMapper.readValue(new File("OutputPlaylistsFile/playlistId2000.json"), Items[].class);
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

    public static List<Items> getReal1990Playlist() throws Exception {
        Items[] items = objectMapper.readValue(new File("OutputPlaylistsFile/playlistId1990.json"), Items[].class);
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

    public static List<Items> getReal1980Playlist() throws Exception {
        Items[] items = objectMapper.readValue(new File("OutputPlaylistsFile/playlistId1980.json"), Items[].class);
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

    public static List<Items> getReal1970Playlist() throws Exception {
        Items[] items = objectMapper.readValue(new File("OutputPlaylistsFile/playlistId1970.json"), Items[].class);
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
