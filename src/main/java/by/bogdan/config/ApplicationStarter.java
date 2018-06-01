package by.bogdan.config;

import by.bogdan.routing.AppRoute;
import by.bogdan.routing.ApplicationRoute;
import org.reflections.Reflections;

import java.util.Set;

public class ApplicationStarter {

    public static void processAnnotations() {
        Reflections reflections = new Reflections("by.bogdan.routing");
        Set<Class<? extends AppRoute>> routers = reflections.getSubTypesOf(AppRoute.class);
        routers.forEach(router -> {
            if(router.isAnnotationPresent(ApplicationRoute.class)) {
                try {
                    AppRoute appRoute = router.newInstance();
                    appRoute.applyMappings();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        });
    }
}
