package ru.school21.avaj.writer;

import java.io.File;
import java.io.PrintWriter;

public class FileWriter {

    public PrintWriter initFile() throws Exception
    {

        File oldFile = new File(System.getProperty("user.dir") + "/" + "simulation.txt");

        if (oldFile.exists()) {
            if (!oldFile.delete())
                throw new Exception("Couldn't delete old output file.");
        }
        PrintWriter pw = new PrintWriter(new File(System.getProperty("user.dir") + "/" + "simulation.txt"), "UTF-8");

        return pw;
    }

}
