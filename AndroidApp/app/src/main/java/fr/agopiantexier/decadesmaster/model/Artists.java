package fr.agopiantexier.decadesmaster.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "artist")
public class Artists {
    public Artists(){}
    public Artists(String name){
        this.name = name;
    }

    @DatabaseField(unique = true, id = true)
    private int id;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
