package fr.agopiantexier.decadesmaster.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "sing")
public class Sing {
    public Sing(){}

    public Song getSongId() {
        return songId;
    }

    public void setSongId(Song s) {
        this.songId = s;
    }

    public Artists getArtist_ID() {
        return artistId;
    }

    public void setArtistId(Artists artist_ID) {
        this.artistId = artist_ID;
    }

    public Sing(Song idSong, Artists idArtist){
        this.songId = idSong;
        this.artistId = idArtist;
    }

    @DatabaseField(unique = true, generatedId = true)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @DatabaseField(columnName = "playlistid", canBeNull = false, foreign = true, foreignColumnName = "id", foreignAutoCreate = true)
    private Song songId;

    @DatabaseField(columnName = "artistid", canBeNull = false, foreign = true, foreignColumnName = "id", foreignAutoCreate = true)
    private Artists artistId;

}
