package org.example;

import org.example.benchmark.SortBenchMark;

public class Main {
    public static void main(String[] args) throws Exception {
        SortBenchMark benchmark = new SortBenchMark();
        benchmark.runBenchmarks();
    }
}
