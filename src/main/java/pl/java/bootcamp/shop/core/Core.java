package pl.java.bootcamp.shop.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.java.bootcamp.shop.dbs.*;
import pl.java.bootcamp.shop.objects.User;

import java.io.IOException;

@Component
public class Core implements ICore {
    @Autowired
    private IAuthenticator authenticator;
    @Autowired
    private IFileLoader fileLoader;
    @Autowired
    private IGUI gui;
    @Autowired
    private IGamesDB gamesDB;
    @Autowired
    private IUserDB userDB;

    @Override
    public void Start(){

        mainloop:
        while (true){

            try {
                fileLoader.readDataFromFile();
            } catch (IOException e) {
                System.out.println("Database is malformed !!");
                return;
            }

            switch (gui.mainMenu()){
                case "1":
                    int logged = authenticator.authenticate();
                    if (logged == 0){
                        System.out.println("bledne logowanie");
                        return;
                    }else{
                        loggedloop:
                        while (true){
                            switch ((logged == 2) ? gui.adminMenu() : gui.userMenu()){
                                case "1":
                                    gui.ListGames(logged);
                                    break;
                                case "2":
                                    gamesDB.buyGames(gui.pickProduct(),gui.pickQuantity());
                                    break;
                                case "3":
                                    try {
                                        fileLoader.saveDataToFile();
                                        break loggedloop;
                                    } catch (IOException e) {
                                        System.out.println("Database Error !!");
                                    }
                                case "4":
                                    if (logged == 2){
                                        gamesDB.addGames(gui.pickProduct(),gui.pickQuantity());
                                    }else {
                                        System.out.println("Access denied");
                                    }
                                    break;
                                case "5":
                                    userDB.changeAcces(gui.pickUser());
                                    break;
                                default:
                                    System.out.println("Wrong chose");
                                    break;
                            }
                        }
                    }
                    break;
                case "2":
                    User userFromGui = gui.getLoginAndPassword();
                    userDB.addUser(userFromGui.getUsername(),userFromGui.getPassword(),false);
                    try {
                        fileLoader.saveDataToFile();
                        break;
                    } catch (IOException e) {
                        System.out.println("Database Error !!");
                    }
                case "3":
                    try {
                        fileLoader.saveDataToFile();
                        break mainloop;
                    } catch (IOException e) {
                        System.out.println("Database Error !!");
                    }
            }
        }
    }
}
