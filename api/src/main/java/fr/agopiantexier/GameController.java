package fr.agopiantexier;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.javalin.Handler;

public class GameController {
    public static Handler inscription = ctx -> {
        System.out.println(ctx.body());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        ObjectReader reader = mapper.reader().forType(Inscription.class);
        Inscription resp = reader.readValue(ctx.body());
        System.out.println(resp.getPseudo());
        System.out.println(resp.getPassword());
        //mapper.readValue(ctx.body(), Account.class);
    };
}
