package pl.java.bootcamp.shop.core;

import pl.java.bootcamp.shop.objects.Game;
import pl.java.bootcamp.shop.objects.User;

public interface IGUI {
    public String mainMenu() ;

    public String userMenu();

    public String adminMenu();

    public User getLoginAndPassword();

    public void ListGames(int privilege);

    public String pickProduct();

    public int pickQuantity();

    public String pickUser();
}
