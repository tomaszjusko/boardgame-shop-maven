package pl.java.bootcamp.shop.dbs;

import org.springframework.stereotype.Component;
import pl.java.bootcamp.shop.objects.Game;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class GamesDB implements IGamesDB {

    private final Map<String, Game> games = new HashMap<>();

    @Override
    public void buyGames(String key, int order){
        if(games.containsKey(key) && games.get(key).getQuantity() - order >= 0) {
            games.get(key).setQuantity(games.get(key).getQuantity() - order);
        }else{
            System.out.println("Product out of stock");
        }
    }

    @Override
    public void addGames(String key, int quantity){
        if (games.containsKey(key)) {
            games.get(key).setQuantity(games.get(key).getQuantity() + quantity);
        }else{
            System.out.println("There is no such product");
        }
    }

    @Override
    public void addGame(Game game){this.games.put(game.getName(),game);}

    @Override
    public Collection<Game> getGames() {
        return games.values();
    }
}
