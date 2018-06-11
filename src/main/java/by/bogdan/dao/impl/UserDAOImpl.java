package by.bogdan.dao.impl;

import by.bogdan.dao.UserDAO;
import by.bogdan.model.User;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private MongoCollection<Document> user;

    public UserDAOImpl(MongoClient mongoClient) {
        MongoDatabase user = mongoClient.getDatabase("user");
        try {
            this.user = user.getCollection("user");
        } catch (IllegalArgumentException e) {
            user.createCollection("user");
            this.user = user.getCollection("user");
        }

    }

    private static User fromDocument(Document foundDoc) {
        User user = new User();
        user.setId((String) foundDoc.get("_id"));
        user.setName((String) foundDoc.get("name"));
        user.setWeight((Double) foundDoc.get("weight"));
        user.setHeight((Double) foundDoc.get("height"));
        user.setUniversity((String) foundDoc.get("university"));
        return user;
    }

    private static Document createDocument(User user) {
        Document document = new Document();
        document.put("_id", user.getId());
        document.put("name", user.getName());
        document.put("weight", user.getWeight());
        document.put("height", user.getHeight());
        document.put("university", user.getUniversity());
        return document;
    }

    private static Bson getUpdates(User user) {
        ArrayList<Bson> bsons = new ArrayList<>();
        bsons.add(Updates.set("name", user.getName()));
        bsons.add(Updates.set("weight", user.getWeight()));
        bsons.add(Updates.set("height", user.getHeight()));
        bsons.add(Updates.set("university", user.getUniversity()));
        return Updates.combine(bsons);
    }

    @Override
    public void create(User user) {
        this.user.insertOne(createDocument(user));
    }

    @Override
    public User getOne(String id) {
        return fromDocument(this.user.find(Filters.eq("_id", id)).first());
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        this.user.find().map(doc -> {
            users.add(fromDocument(doc));
            return null;
        });
        return users;
    }

    @Override
    public void delete(String id) {
        this.user.deleteOne(Filters.eq("_id", id));
    }

    @Override
    public User update(User user) {
        this.user.updateOne(Filters.eq("_id", user.getId()), getUpdates(user));
        return user;
    }

    @Override
    public User getByName(String name) {
        return fromDocument(this.user.find(Filters.eq("name", name)).first());
    }

    @Override
    public List<User> find(Bson filters) {
        List<User> users = new ArrayList<>();
        this.user.find(filters).map(doc -> {
            users.add(fromDocument(doc));
            return null;
        });
        return users;
    }
}
