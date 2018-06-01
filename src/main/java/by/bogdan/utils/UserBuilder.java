package by.bogdan.utils;

import by.bogdan.model.User;

public class UserBuilder {

    private final User user;

    private UserBuilder(User user) {
        this.user = user;
    }

    public static UserBuilder builder() {
        return new UserBuilder(new User());
    }

    public UserBuilder name(String name) {
        this.user.setName(name);
        return this;
    }

    public UserBuilder weight(double weight) {
        this.user.setWeight(weight);
        return this;
    }

    public UserBuilder height(double height) {
        this.user.setHeight(height);
        return this;
    }

    public UserBuilder university(String university) {
        this.user.setUniversity(university);
        return this;
    }

    public User build() {
        return user;
    }

}
