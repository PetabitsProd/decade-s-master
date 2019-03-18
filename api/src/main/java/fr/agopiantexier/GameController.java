package fr.agopiantexier;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.javalin.Handler;

public class GameController {
    static String pseudo;
    static String password;

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

    public static Handler inscription = ctx -> {
        System.out.println(ctx.body());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        ObjectReader reader = mapper.reader().forType(Inscription.class);
        Inscription resp = reader.readValue(ctx.body());
        pseudo = resp.getPseudo();
        password = resp.getPassword();

        System.out.println(pseudo);
        System.out.println(password);

        Inscription inscription = new Inscription();
        inscription.inscription(pseudo, password);
        //mapper.readValue(ctx.body(), Account.class);
    };
}
