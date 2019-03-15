package fr.agopiantexier;

import io.javalin.Handler;

import static java.sql.DriverManager.println;

public class GameController {
    public static Handler inscription = ctx -> {
        System.out.println(ctx.body().string());
    };
}
