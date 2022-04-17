package ru.school21.avaj.airport;

import ru.school21.avaj.aircraft.Flyable;

import java.util.ArrayList;

public abstract class Tower {

    private ArrayList<Flyable> observers = new ArrayList<>();
    private ArrayList<Flyable> landed = new ArrayList<>();

    public void register(Flyable flyable)
    {
        observers.add(flyable);
        Simulator.writer.println("Tower says: " + flyable + " registered to weather tower.");
    }

    public void unregister(Flyable flyable)
    {
        landed.add(flyable);
        Simulator.writer.println("Tower says: " + flyable + " unregistered from weather tower.");
    }

    protected void conditionsChanged()
    {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
        observers.removeAll(landed);
        landed.clear();
    }

}
