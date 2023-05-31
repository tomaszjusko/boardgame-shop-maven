package pl.java.bootcamp.shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.java.bootcamp.shop.configuration.AppConfiguration;
import pl.java.bootcamp.shop.core.ICore;

public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

        ICore core = context.getBean(ICore.class);
        core.Start();
    }
}