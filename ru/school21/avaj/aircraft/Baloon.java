package ru.school21.avaj.aircraft;

import ru.school21.avaj.airport.Simulator;
import ru.school21.avaj.airport.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    @Override
    public void updateConditions()
    {
        String newWeather = this.weatherTower.getWeather(this.coordinates);
        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();
        int height = this.coordinates.getHeight();

        if (newWeather.equals("SUN"))
        {
            longitude += 2;
            height += 4;
            Simulator.writer.println(this + ": The sand in my bags will become hot as on the beach");
        }
        else if (newWeather.equals("RAIN"))
        {
            height -= 5;
            Simulator.writer.println(this + ": Hope that rain will not extinguish our burners..");
        }
        else if (newWeather.equals("FOG"))
        {
            height -= 3;
            Simulator.writer.println(this + ": Is it a fog or a smoke from my burning shell?");
        }
        else if (newWeather.equals("SNOW"))
        {
            height -= 15;
            Simulator.writer.println(this + ": Am i look like a snowball?");
        }

        if (height > 100)
            height = 100;

        if (height <= 0)
        {
            Simulator.writer.println(this + " landing. Longitude: " + this.coordinates.getLongitude() +
                    ", latitude: " + this.coordinates.getLatitude() + ", height: 0");
            this.weatherTower.unregister(this);
        }
        else
        {
            this.coordinates = new Coordinates(longitude, latitude, height);
        }

    }

    @Override
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }

    @Override
    public String toString()
    {
        return "Baloon#" + this.name + "(" + this.id + ")";
    }
}
