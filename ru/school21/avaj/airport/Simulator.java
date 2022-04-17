package ru.school21.avaj.airport;

import ru.school21.avaj.exception.WrongArgumentException;
import ru.school21.avaj.parser.ConfigParser;
import ru.school21.avaj.writer.FileWriter;

import java.io.PrintWriter;


public class Simulator {

    public static PrintWriter writer;

    public void simulate(String[] args) {

        try {
            if (args.length != 1) {
                throw new WrongArgumentException("Proper program execution: 'java -cp . ru/school21/avaj/Simulator scenario.txt'");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + " Exiting..");
            System.exit(-1);
        }

        try {
            writer = new FileWriter().initFile();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Exiting..");
            System.exit(-1);
        }

        ConfigParser cp = new ConfigParser();
        WeatherTower tower = new WeatherTower();
        int cycles = 0;

        try {
            cycles = cp.getConfigData(args[0]);
            cp.addPlanes(tower, args[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Exiting..");
            System.exit(-1);
        }

        for (int i = 0; i < cycles; i++)
        {
            tower.changeWeather();
        }

        writer.close();
    }
}
