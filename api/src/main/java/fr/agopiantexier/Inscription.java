package fr.agopiantexier;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import io.javalin.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Inscription {
    private String pseudo;
    private String password;

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void inscription(String pseudo, String password) throws IOException, SQLException {
        //ctx.result("Inscription");

        System.out.println(pseudo + " et " + password);

        Properties properties = new Properties();
        InputStream input = null;
        input = new FileInputStream("config.properties");
        properties.load(input);
        String dbPath = properties.getProperty("dbPath");

        ConnectionSource source = new JdbcConnectionSource("jdbc:sqlite:"+ dbPath +"DecadesMasterDB.sqlite");

        Dao<Account, String> accountDao =
                DaoManager.createDao(source
                        , Account.class);
        System.out.println(accountDao.countOf());
        Account account = new Account();
        account.setPseudo(pseudo);
        account.setPassword(password);
        accountDao.create(account);
    }
}