package pl.java.bootcamp.shop.dbs;

import pl.java.bootcamp.shop.objects.User;

import java.util.Collection;

public interface IUserDB {
    public void addUser(User user);

    public User findUser(String login);

    public User addUser(String login, String password,boolean isadmin);

    public void changeAcces(String key);

    public Collection<User> getUsers();
}
