package pl.java.bootcamp.shop.dbs;

import pl.java.bootcamp.shop.objects.Game;

import java.util.Collection;

public interface IGamesDB {
    public void buyGames(String key, int order);

    public void addGames(String key, int quantity);

    public void addGame(Game game);

    public Collection<Game> getGames();
}
