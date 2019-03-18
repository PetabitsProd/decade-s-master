package fr.agopiantexier;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {

    String pseudo, password;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Utilisateur connexion() throws IOException, SQLException {
        System.out.println(pseudo + " et " + password);

        Properties properties = new Properties();
        InputStream input = null;
        input = new FileInputStream("config.properties");
        properties.load(input);
        String dbPath = properties.getProperty("dbPath");

        ConnectionSource source = new JdbcConnectionSource("jdbc:sqlite:" + dbPath + "DecadesMasterDB.sqlite");

        Dao<Utilisateur, String> userDao =
                DaoManager.createDao(source
                        , Utilisateur.class);
        Utilisateur user = new Utilisateur();
        user.setPseudo(pseudo);
        user.setPassword(password);
        Utilisateur utilisateurConnecte = userDao.queryForMatchingArgs(user).stream().findFirst().orElse(null);
        return utilisateurConnecte;
    }
}
