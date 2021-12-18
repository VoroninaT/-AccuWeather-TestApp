package homework7;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        RequestSend requestSend = new RequestSend();

        String city = "Belgorod";

        System.out.println("Получим код города для \"" + city + "\"");
        String cityCode = requestSend.sendCityRequest(city);

        System.out.println("Определен код города: " + cityCode);

        System.out.println();

        System.out.println("Погода на завтра: ");
        requestSend.printForecastRequest(cityCode);

    }
}
