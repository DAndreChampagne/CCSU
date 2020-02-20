package cs501;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Helpers {
	
    public static <T> String PrintArray(T[] data) {
        String result = "[ ";

        for (T item : data) {
            result += item.toString() + ", ";
        }
        
        result += "]";

        return result;
    }
   
	
	// generates a random number file for use by the sorting algorithms
    public static void GenerateRandomNumberFile(String path, int count, double min, double max) throws FileNotFoundException {
        String data = "";
        Random r = new Random();

        for (; count > 0; --count) {
            Integer x = (int)(min + (r.nextDouble() * (max-min)));
            data += x + " ";
        }

        PrintWriter writer = new PrintWriter(path);
        writer.print(data);
        writer.close();
    }

    // loads random number file into an array
    public static Integer[] ImportNumbersFromFile(String path) throws IOException {
        String data = ""; 
        data = String.join("", Files.readAllLines(Paths.get(path)));
        
        String[] intermediate = data.split(" ", 0);
        Integer[] results = new Integer[intermediate.length];
        
        for (int i=0; i<intermediate.length; ++i) {
            Integer value = (int)Double.parseDouble(intermediate[i]);
            results[i] = value;
        }
        
        return results;
    }
}
