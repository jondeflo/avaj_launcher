package ru.school21.avaj.airport;

import ru.school21.avaj.aircraft.Coordinates;
import java.util.Random;

public class WeatherProvider {

    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = new String[]{"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider(){}

    public static WeatherProvider getProvider()
    {
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates)
    {
        int randInt = new Random().nextInt(
                coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude()) % 4;
        return weather[randInt];
    }

}
