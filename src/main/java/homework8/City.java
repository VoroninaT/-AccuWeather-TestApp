package homework8;

public class City {
    private String name;
    private String localDate;
    private String weather;
    private double temperature;

    public City(String name, String localDate, String weather, double temperature) {
        this.name = name;
        this.localDate = localDate;
        this.weather = weather;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocal(String localDate) {
        this.localDate = localDate;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
