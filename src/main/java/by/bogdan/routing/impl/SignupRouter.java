package by.bogdan.routing.impl;

import by.bogdan.routing.AppRoute;
import by.bogdan.routing.ApplicationRoute;
import spark.Spark;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@ApplicationRoute
public class SignupRouter implements AppRoute {

    @Override
    public void applyMappings() {
        Spark.get("signup", (req, res) -> {
            //TODO
            throw new NotImplementedException();
        });
    }
}
