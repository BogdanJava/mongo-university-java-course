package by.bogdan.config;

import by.bogdan.dao.UserDAO;
import by.bogdan.dao.impl.UserDAOImpl;
import com.mongodb.Function;
import com.mongodb.MongoClient;

public class MongoConnector {
    private static MongoClient mongoClient = null;

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            mongoClient = new MongoClient("localhost", 27017);
        }
        return mongoClient;
    }

    public static Function<MongoClient, UserDAO> userDAO() {
        return (UserDAOImpl::new);
    }
}
