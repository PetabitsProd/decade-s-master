package fr.agopiantexier.decadesmaster;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Track {

    private List<Artists> artists;

    //  private Album album;



    @JsonProperty("preview_url")
    private String previewUrls;

    public String getPreviewUrls() {
        return previewUrls;
    }

    public void setPreviewUrls(String previewUrls) {
        this.previewUrls = previewUrls;
    }

    //nom du son
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Artists> getArtists() {
        return artists;
    }

    public void setArtists(List<Artists> artists) {
        this.artists = artists;
    }

}
