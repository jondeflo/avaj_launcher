package ru.school21.avaj.aircraft;

import ru.school21.avaj.airport.Simulator;
import ru.school21.avaj.airport.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates)
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
            latitude += 10;
            height += 2;
            Simulator.writer.println(this + ": Sunny! Yesterday my life was filled with rain..");
        }
        else if (newWeather.equals("RAIN"))
        {
            latitude += 5;
            Simulator.writer.println(this + ": It's a rainy day, yeah the sun will shine!");
        }
        else if (newWeather.equals("FOG"))
        {
            latitude += 1;
            Simulator.writer.println(this + ": Damn, we will never land in this Silent Hill!");
        }
        else if (newWeather.equals("SNOW"))
        {
            height -= 7;
            Simulator.writer.println(this + ": Let it snow, let it snow, let it snow!");
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
        return "JetPlane#" + this.name + "(" + this.id + ")";
    }
}
