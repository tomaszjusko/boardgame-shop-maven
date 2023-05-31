package pl.java.bootcamp.shop.core;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.java.bootcamp.shop.dbs.IUserDB;
import pl.java.bootcamp.shop.objects.User;

@Component
public class Authenticator implements IAuthenticator {

    private final String seed = "6a51A94NGCoTPuefU2Z9L9mo7rAZJ%8b";

    @Autowired
    private IGUI gui;
    @Autowired
    private IUserDB userDB;

    @Override
    public int authenticate(){
        int counter = 0;
        while (counter < 3){
            User userFromGui = gui.getLoginAndPassword();
            User userFromDb = userDB.findUser(userFromGui.getUsername());
            if (userFromDb != null && userFromDb.getPassword().equals(DigestUtils.md5Hex(userFromGui.getPassword() + seed))){
                if (userFromDb.isAdmin()){
                    return 2;
                }else {
                    return 1;
                }
            }
            System.out.println("Incorrect login or password");
            counter++;
        }
        return 0;
    }
}
