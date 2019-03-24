package fr.agopiantexier.decadesmaster.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "playlist")
public class Playlist {
    public Playlist(){}
    public Playlist(String name){
        this.name = name;
    }

    @DatabaseField(unique = true, generatedId = true)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @DatabaseField(columnName = "name" , canBeNull = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
