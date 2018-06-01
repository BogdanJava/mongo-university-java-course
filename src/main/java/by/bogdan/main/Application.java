package by.bogdan.main;

import spark.Spark;

public class Application {

    public static void main(String[] args) {
        Spark.port(8080);
        Spark.get("/", (req, res) -> "Hello world from spark!");
    }
}
