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
    public static void inscription(Context ctx) throws IOException, SQLException {
        ctx.result("Inscription");

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
        account.setPseudo(":)");
        account.setPassword("secret");
        accountDao.create(account);
    }
}