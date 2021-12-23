package homework8;

import homework7.RequestSend;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {

        RequestSend requestSend = new RequestSend();
        City moscow = requestSend.getCityWeather("Moscow");
        City london = requestSend.getCityWeather("London");
        City omsk = requestSend.getCityWeather("Omsk");


        DbHandler dbHandler = new DbHandler();
        dbHandler.addCity(moscow);
        dbHandler.addCity(london);
        dbHandler.addCity(omsk);

        System.out.println();
        System.out.println();
        System.out.println("Database data:");

        dbHandler.printTable();
    }
}
