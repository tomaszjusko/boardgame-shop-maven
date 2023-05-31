package pl.java.bootcamp.shop.dbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.java.bootcamp.shop.objects.Boardgame;
import pl.java.bootcamp.shop.objects.RpgGame;
import pl.java.bootcamp.shop.objects.User;
import pl.java.bootcamp.shop.objects.Writable;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Component
public class FileLoader implements IFileLoader {

    @Autowired
    IGamesDB gamesDB;
    @Autowired
    IUserDB userDB;

        @Override
        public void readDataFromFile() throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader("db.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] objectData = line.split(";");
                String[] vars = Arrays.copyOfRange(objectData, 1, objectData.length);
                switch (objectData[0]) {
                    case "Boardgame":
                        gamesDB.addGame(new Boardgame(vars));
                        break;
                    case "RpgGame":
                        gamesDB.addGame(new RpgGame(vars));
                        break;
                    case "User":
                        userDB.addUser(new User(vars));
                        break;
                    default:
                        System.out.println("Unexpected type during DB loading !!");
                        break;
                }
            }
            reader.close();
        }

        @Override
        public void saveDataToFile() throws IOException {
            Collection<Writable> entries = new ArrayList<>();
            entries.addAll(gamesDB.getGames());
            entries.addAll(userDB.getUsers());
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter("db.csv"));
            boolean firstTime = true;
            for (Writable entry : entries) {
                String lineInFile = entry.toCSV();
                if (!firstTime) {
                    writer.newLine();
                }
                firstTime = false;
                writer.write(lineInFile);
            }
            writer.close();
        }
}
