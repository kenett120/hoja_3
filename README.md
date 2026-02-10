# Hoja de Trabajo #3 - Algoritmos de Ordenamiento

**Estudiantes:** Joao Castillo (25776) y Kenett Ortega (25777)

## Requisitos

- Java 21+
- Maven
- Python 3 con matplotlib y pandas

## Ejecución

### 1. Ejecutar Tests
```bash
mvn test
```

### 2. Ejecutar Benchmark con Profiling
```bash
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

Esto genera:
- `benchmark_results.csv` - Datos de rendimiento
- `benchmark-profile.jfr` - Archivo de profiling JFR

### 3. Generar Gráficas
```bash
python3 generate_graph.py
```

Genera:
- `performance_analysis.png` - Análisis completo
- `simple_comparison.png` - Comparación simplificada

### 4. Ver Reporte
Abrir `REPORTE.pdf`

## Análisis del Profiler (Opcional)

```bash
jfr summary benchmark-profile.jfr
jfr print benchmark-profile.jfr > profile_details.txt
```

## Estructura del Proyecto

```
src/main/java/org/example/
├── sorts/               # Implementaciones de algoritmos
│   ├── GnomeSort.java
│   ├── MergeSort.java
│   ├── QuickSort.java
│   ├── InsertionSort.java
│   └── RadixSort.java
├── benchmark/           # Sistema de benchmarking
└── Main.java           # Punto de entrada

src/test/java/org/example/sorts/
└── SortAlgorithmContract.java  # Tests compartidos
```

## Algoritmos Implementados

| Algoritmo | Mejor Caso | Caso Promedio | Peor Caso | Espacio |
|-----------|------------|---------------|-----------|---------|
| GnomeSort | O(n) | O(n²) | O(n²) | O(1) |
| MergeSort | O(n log n) | O(n log n) | O(n log n) | O(n) |
| QuickSort* | O(n log n) | O(n log n) | O(n²) | O(log n) |
| InsertionSort | O(n) | O(n²) | O(n²) | O(1) |
| RadixSort | O(d·n) | O(d·n) | O(d·n) | O(n+k) |

*QuickSort usa median-of-three para evitar peor caso en datos ordenados
