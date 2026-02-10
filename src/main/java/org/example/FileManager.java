package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILE_NAME = "numbers.txt";

    /**
     * Escribe los números en un archivo
     */
    public void writeNumbersToFile(List<Integer> numbers) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Integer number : numbers) {
                writer.write(number.toString());
                writer.newLine();
            }
        }
    }

    /**
     * Lee los números desde el archivo
     */
    public List<Integer> readNumbersFromFile() throws IOException {
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        }
        return numbers;
    }
}

