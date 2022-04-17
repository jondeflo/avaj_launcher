package ru.school21.avaj.aircraft;

import ru.school21.avaj.airport.Simulator;
import ru.school21.avaj.airport.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates)
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
            longitude += 10;
            height += 2;
            Simulator.writer.println(this + ": Hey! Stay under me, i will cool you!");
        }
        else if (newWeather.equals("RAIN"))
        {
            longitude += 5;
            Simulator.writer.println(this + ": Brrr... I hate wet propellers..");
        }
        else if (newWeather.equals("FOG"))
        {
            longitude += 1;
            Simulator.writer.println(this + ": I'm a hedgehog in a fog!");
        }
        else if (newWeather.equals("SNOW"))
        {
            height -= 12;
            Simulator.writer.println(this + ": I like to grind snowflakes!");
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
            this.coordinates = new Coordinates(longitude, latitude, height);

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
        return "Helicopter#" + this.name + "(" + this.id + ")";
    }
}
