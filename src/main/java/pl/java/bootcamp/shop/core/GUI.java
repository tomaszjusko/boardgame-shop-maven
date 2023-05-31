package pl.java.bootcamp.shop.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.java.bootcamp.shop.dbs.GamesDB;
import pl.java.bootcamp.shop.dbs.IGamesDB;
import pl.java.bootcamp.shop.objects.Game;
import pl.java.bootcamp.shop.objects.User;

import java.util.Scanner;

@Component
public class GUI implements IGUI{

    @Autowired
    IGamesDB gamesDB;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String mainMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");

        return scanner.nextLine();
    }

    @Override
    public String userMenu() {
        System.out.println("1. Show products");
        System.out.println("2. Buy product");
        System.out.println("3. Logout");

        return scanner.nextLine();
    }

    @Override
    public String adminMenu() {
        System.out.println("1. Show products");
        System.out.println("2. Buy product");
        System.out.println("3. Logout");
        System.out.println("4. Add product");
        System.out.println("5. Give admin access");

        return scanner.nextLine();
    }

    @Override
    public User getLoginAndPassword(){
        System.out.println("Podaj login");
        String login = scanner.nextLine();
        System.out.println("Podaj haslo");
        return new User(login,scanner.nextLine(),false);
    }

    @Override
    public void ListGames(int privilege){
        for(Game game : gamesDB.getGames()) {
            if (game.getQuantity() > 0 || privilege == 2) {
                System.out.println(game);
            }
        }
}

    @Override
    public String pickProduct(){
    System.out.println("Give product name");
        return scanner.nextLine();
}

    @Override
    public int pickQuantity(){
        System.out.println("How many?");
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String pickUser(){
        System.out.println("What user do you want to change to admin?");
        return scanner.nextLine();
    }
}
