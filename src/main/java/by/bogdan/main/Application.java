package by.bogdan.main;

import by.bogdan.config.ApplicationStarter;
import freemarker.template.Configuration;
import spark.Spark;

public class Application {

    public static Configuration freeMarkerConfig = new Configuration(Configuration.VERSION_2_3_22);

    public static void main(String[] args) {
        Spark.port(8080);
        freeMarkerConfig.setClassForTemplateLoading(Application.class, "/freemarker/templates");
        ApplicationStarter.processAnnotations();
    }
}
