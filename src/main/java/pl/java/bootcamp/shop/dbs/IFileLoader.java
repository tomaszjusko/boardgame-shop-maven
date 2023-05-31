package pl.java.bootcamp.shop.dbs;
import java.io.*;

public interface IFileLoader {

    public void readDataFromFile() throws IOException;

    public void saveDataToFile() throws IOException;
}
