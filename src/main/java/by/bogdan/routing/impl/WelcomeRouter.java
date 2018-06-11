package by.bogdan.routing.impl;

import by.bogdan.main.Application;
import by.bogdan.routing.AppRoute;
import by.bogdan.routing.ApplicationRoute;
import by.bogdan.utils.UserBuilder;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@ApplicationRoute
public class WelcomeRouter implements AppRoute {

    @Override
    public void applyMappings() {
        Spark.get("/", (req, res) -> {
            Template welcomeTemplate = Application.freeMarkerConfig.getTemplate("welcome.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> params = new HashMap<>();
            params.put("user", UserBuilder.builder().name("Bogdan").height(1.5).weight(2.3).university("bsuir").build());

            welcomeTemplate.process(params, writer);
            return writer;
        });
    }
}
