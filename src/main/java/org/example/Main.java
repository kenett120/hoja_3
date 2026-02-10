package org.example;

import org.example.benchmark.SortBenchMark;
import jdk.jfr.Recording;
import jdk.jfr.Configuration;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  Benchmark de Algoritmos de Ordenamiento");
        System.out.println("  Con Java Flight Recorder (JFR) Profiling");
        System.out.println("=".repeat(60));
        System.out.println();

        // Start JFR profiling
        Configuration config = Configuration.getConfiguration("profile");
        Recording recording = new Recording(config);
        recording.setName("SortBenchmark");
        recording.setDestination(Path.of("benchmark-profile.jfr"));

        System.out.println("📊 Iniciando profiling con JFR...");
        recording.start();

        // Run benchmarks
        SortBenchMark benchmark = new SortBenchMark();
        benchmark.runBenchmarks();

        // Stop profiling
        recording.stop();
        recording.close();

        System.out.println();
        System.out.println("✓ Profiling completado!");
        System.out.println("  Archivo JFR: benchmark-profile.jfr");
        System.out.println();
        System.out.println("Para analizar el perfil, ejecuta:");
        System.out.println("  jfr print benchmark-profile.jfr > profile_summary.txt");
        System.out.println("  O ábrelo con JDK Mission Control");
    }
}
