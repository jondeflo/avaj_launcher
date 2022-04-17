package ru.school21.avaj.parser;

import ru.school21.avaj.aircraft.AircraftFactory;
import ru.school21.avaj.aircraft.Flyable;
import ru.school21.avaj.exception.WrongArgumentException;
import ru.school21.avaj.exception.WrongFlyableTypeException;
import ru.school21.avaj.exception.WrongScenarioException;
import ru.school21.avaj.airport.WeatherTower;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ConfigParser {

    public void addPlanes(WeatherTower tower, String args) throws Exception
    {
        File file = new File(System.getProperty("user.dir") + "/" + args);

        FileInputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);

        int count = 0;
        String line;

        while ((line = reader.readLine()) != null) {
            if (count == 0)
                count++;
            else
            {
                String[] arr = line.split(" ");
                Flyable tmp = AircraftFactory.newAircraft(arr[0], arr[1],
                        Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
                if (tmp == null)
                    throw new WrongFlyableTypeException("Unknown Flyable type found. Please recheck scenario file.");
                tmp.registerTower(tower);
            }

        }
        reader.close();
        isr.close();
        is.close();
    }

    public int getConfigData(String args) throws Exception
    {
        File file = new File(System.getProperty("user.dir") + "/" + args);
        FileInputStream is;
        try {
             is = new FileInputStream(file);
        }
        catch (Exception e)
        {
            throw new WrongArgumentException("No scenario file '" + args + "' found.");
        }
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);

        int res = 0;
        int count = 0;
        String line;

        while ((line = reader.readLine()) != null)
        {
            if (count == 0) {
                try
                {
                    res = Integer.parseInt(line);
                } catch (Exception e)
                {
                    throw new WrongScenarioException("Invalid config: first string must be an integer. ");
                }
                count++;
            }
            else
            {
                if (!checkLine(line))
                    throw new WrongScenarioException("Invalid config in string " + (count + 1) + ": \'" + line + "\'.");
                count++;
            }
        }
        reader.close();
        isr.close();
        is.close();

        if (count < 2)
            throw new WrongScenarioException("Invalid config: too less data.");
        if (res < 1)
            throw new WrongScenarioException("Invalid config: number of cycles must be positive.");
        return res;
    }

    public boolean checkLine(String line) throws Exception
    {

        String[] arr = line.split(" ");
        if (arr.length != 5)
            throw new WrongScenarioException("Wrong amount of arguments in string: '" + line + "'");
        if (!Arrays.asList("Helicopter", "JetPlane", "Baloon").contains(arr[0])) {
            throw new WrongFlyableTypeException("Unknown Flyable type found at string: '" + line + "'. Please recheck scenario file.");
        }

        if (arr[1].length() < 1)
            return false;
        try{
            int lon = Integer.parseInt(arr[2]);
            int lat = Integer.parseInt(arr[3]);
            int height = Integer.parseInt(arr[4]);
            if (lon < 1 || lat < 1 || height < 1)
                throw new WrongScenarioException("Coordinates and height must be positive numbers.");
            if (height > 100)
                throw new WrongScenarioException("Height must be in range 1-100.");

        } catch (Exception e) {return false;}
        return true;
    }
}
