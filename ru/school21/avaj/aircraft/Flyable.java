package ru.school21.avaj.aircraft;

import ru.school21.avaj.airport.WeatherTower;

public interface Flyable {

    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);

}
