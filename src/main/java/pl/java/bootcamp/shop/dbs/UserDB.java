package pl.java.bootcamp.shop.dbs;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.java.bootcamp.shop.objects.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Component
public class UserDB implements IUserDB {

    private final String seed = "6a51A94NGCoTPuefU2Z9L9mo7rAZJ%8b";
    public Map<String, User> users = new HashMap<>();

    @Override
    public void addUser(User user){this.users.put(user.getUsername(),user);}

    @Override
    public User findUser(String login) {
        if (this.users.containsKey(login)) {
            return this.users.get(login);
        } else {
            return null;
        }
    }

    @Override
    public User addUser(String login, String password,boolean isadmin) {
        if (this.users.containsKey(login)) {
            System.out.println("Given username already exists in the database");
            return null;
        } else {
            System.out.println("Registration successfully");
            return this.users.put(login,new User(login, DigestUtils.md5Hex((password) + seed),isadmin));
        }
    }

    @Override
    public void changeAcces(String key){
        if (users.containsKey(key)) {
            if (this.users.get(key).isAdmin()){
                System.out.println("This user is already an admin");
            }else {
                this.users.get(key).setAdmin(true);
            }
        }else {
            System.out.println("There is no such user");
        }
    }

    @Override
    public Collection<User> getUsers() {
        return this.users.values();
    }
}
