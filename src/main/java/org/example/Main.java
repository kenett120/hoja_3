package org.example;

import org.example.benchmark.SortBenchMark;
import jdk.jfr.Recording;
import jdk.jfr.Configuration;

import java.nio.file.Path;

/**
 * Clase principal encargada de ejecutar el benchmark de los
 * algoritmos de ordenamiento y realizar el profiling utilizando
 * Java Flight Recorder (JFR).
 *
 * Esta clase:
 * - Configura y activa una grabación de profiling.
 * - Ejecuta las pruebas de rendimiento.
 * - Detiene la grabación y genera un archivo .jfr
 *   para su posterior análisis.
 *
 * El archivo generado puede analizarse con:
 * - jfr print
 * - JDK Mission Control
 *
 * @author Joao
 * @version 1.0
 */
public class Main {

    /**
     * Método principal del programa.
     *
     * Inicia el profiling con Java Flight Recorder,
     * ejecuta el benchmark de los algoritmos de ordenamiento
     * y guarda el archivo de resultados para su análisis.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     * @throws Exception si ocurre algún error durante la ejecución
     */
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  Benchmark de Algoritmos de Ordenamiento");
        System.out.println("  Con Java Flight Recorder (JFR) Profiling");
        System.out.println("=".repeat(60));
        System.out.println();

        // Configuración del perfilador JFR
        Configuration config = Configuration.getConfiguration("profile");
        Recording recording = new Recording(config);
        recording.setName("SortBenchmark");
        recording.setDestination(Path.of("benchmark-profile.jfr"));

        System.out.println("Iniciando profiling con JFR...");
        recording.start();

        // Ejecución del benchmark
        SortBenchMark benchmark = new SortBenchMark();
        benchmark.runBenchmarks();

        // Finalización del profiling
        recording.stop();
        recording.close();

        System.out.println();
        System.out.println("Profiling completado.");
        System.out.println("Archivo generado: benchmark-profile.jfr");
        System.out.println();
        System.out.println("Para analizar el perfil:");
        System.out.println("  jfr print benchmark-profile.jfr > profile_summary.txt");
        System.out.println("  O abrirlo con JDK Mission Control");
    }
}
