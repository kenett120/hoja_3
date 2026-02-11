package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de la gestión de archivos para almacenamiento
 * y recuperación de números enteros.
 *
 * Permite escribir una lista de números en un archivo de texto
 * y posteriormente leerlos para su procesamiento.
 *
 * El archivo utilizado es "numbers.txt".
 *
 * @author Kenett
 * @version 1.0
 */
public class FileManager {

    /**
     * Nombre del archivo donde se almacenan los números.
     */
    private static final String FILE_NAME = "numbers.txt";

    /**
     * Escribe una lista de números enteros en el archivo especificado.
     *
     * Cada número se guarda en una línea independiente.
     *
     * @param numbers lista de números enteros a escribir en el archivo
     * @throws IOException si ocurre un error durante la escritura
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
     * Lee los números enteros almacenados en el archivo especificado.
     *
     * Cada línea del archivo se interpreta como un número entero
     * y se agrega a una lista que es retornada al finalizar la lectura.
     *
     * @return lista de números enteros leídos del archivo
     * @throws IOException si ocurre un error durante la lectura
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
