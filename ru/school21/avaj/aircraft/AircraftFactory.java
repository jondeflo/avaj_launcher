package ru.school21.avaj.aircraft;

public abstract class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int latitude, int longitude, int height)
    {
        Coordinates coordinates = new Coordinates(latitude, longitude, height);
        if (type.equals("Helicopter"))
        {
            return new Helicopter(name, coordinates);
        }
        else if (type.equals("JetPlane"))
        {
            return new JetPlane(name, coordinates);
        }
        else if (type.equals("Baloon"))
        {
            return new Baloon(name, coordinates);
        }
        else
            return null;
    }
}
