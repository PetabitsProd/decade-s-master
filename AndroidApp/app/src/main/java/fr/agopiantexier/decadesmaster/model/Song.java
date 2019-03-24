package fr.agopiantexier.decadesmaster.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "song")
public class Song {
    public Song(){}

    public Song(Integer idPlaylist, String url, String name){
        this.idPlaylist = idPlaylist;
        this.url = url;
        this.name = name;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @DatabaseField(unique = true, id = true)
    private long id;

    @DatabaseField(columnName = "idPlaylist", canBeNull = false, foreign = true, foreignColumnName = "id", foreignAutoCreate = true)
    private Integer idPlaylist;

    @DatabaseField(columnName = "url", canBeNull = false)
    private String url;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    public Integer getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(Integer idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
