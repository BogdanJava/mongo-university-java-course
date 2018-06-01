package by.bogdan.dao;

import by.bogdan.model.User;
import org.bson.conversions.Bson;

import java.util.List;

public interface UserDAO {
    void create(User user);
    User getOne(String id);
    List<User> getAll();
    void delete(String id);
    User update(User user);
    User getByName(String name);
    List<User> find(Bson filters);
}
