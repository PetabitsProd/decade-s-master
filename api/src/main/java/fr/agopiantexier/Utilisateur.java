package fr.agopiantexier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class Utilisateur {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(unique = true)
    private String pseudo;
    @DatabaseField
    private String password;

    public Utilisateur() {
        // ORMLite needs a no-arg constructor
    }
    public Utilisateur(String name, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}