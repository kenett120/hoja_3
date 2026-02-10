#!/usr/bin/env python3
"""
Generate performance graphs from benchmark results CSV
"""
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

# Read CSV data
df = pd.read_csv('benchmark_results.csv')

# Separate unsorted and sorted scenarios
unsorted = df[df['Scenario'] == 'Unsorted']
sorted_data = df[df['Scenario'] == 'Sorted']

# Get unique sizes and algorithms
sizes = sorted(df['Size'].unique())
algorithms = ['GnomeSort', 'MergeSort', 'QuickSort', 'InsertionSort', 'RadixSort']

# Create comprehensive figure with multiple subplots
fig = plt.figure(figsize=(18, 12))

# 1. Unsorted data - Linear scale
ax1 = plt.subplot(2, 3, 1)
ax1.set_title('Caso Promedio (Datos Desordenados) - Escala Lineal', fontsize=12, fontweight='bold')
ax1.set_xlabel('Número de Elementos', fontsize=10)
ax1.set_ylabel('Tiempo (milisegundos)', fontsize=10)
ax1.grid(True, alpha=0.3)

for algo in algorithms:
    algo_data = unsorted[unsorted['Algorithm'] == algo]
    ax1.plot(algo_data['Size'], algo_data['TimeMs'], marker='o', label=algo, linewidth=2, markersize=6)

ax1.legend(fontsize=9)

# 2. Unsorted data - Log scale (for better visibility of fast algorithms)
ax2 = plt.subplot(2, 3, 2)
ax2.set_title('Caso Promedio (Datos Desordenados) - Escala Logarítmica', fontsize=12, fontweight='bold')
ax2.set_xlabel('Número de Elementos', fontsize=10)
ax2.set_ylabel('Tiempo (milisegundos)', fontsize=10)
ax2.grid(True, alpha=0.3, which='both')

for algo in algorithms:
    algo_data = unsorted[unsorted['Algorithm'] == algo]
    ax2.plot(algo_data['Size'], algo_data['TimeMs'], marker='o', label=algo, linewidth=2, markersize=6)

ax2.legend(fontsize=9)
ax2.set_yscale('log')

# 3. Sorted data - Linear scale
ax3 = plt.subplot(2, 3, 3)
ax3.set_title('Mejor Caso (Datos Ordenados) - Escala Lineal', fontsize=12, fontweight='bold')
ax3.set_xlabel('Número de Elementos', fontsize=10)
ax3.set_ylabel('Tiempo (milisegundos)', fontsize=10)
ax3.grid(True, alpha=0.3)

for algo in algorithms:
    algo_data = sorted_data[sorted_data['Algorithm'] == algo]
    ax3.plot(algo_data['Size'], algo_data['TimeMs'], marker='s', label=algo, linewidth=2, markersize=6)

ax3.legend(fontsize=9)

# 4. Comparison: Only fast algorithms (MergeSort, QuickSort, RadixSort)
ax4 = plt.subplot(2, 3, 4)
ax4.set_title('Comparación: Algoritmos Eficientes O(n log n)', fontsize=12, fontweight='bold')
ax4.set_xlabel('Número de Elementos', fontsize=10)
ax4.set_ylabel('Tiempo (milisegundos)', fontsize=10)
ax4.grid(True, alpha=0.3)

fast_algos = ['MergeSort', 'QuickSort', 'RadixSort']
for algo in fast_algos:
    unsorted_algo = unsorted[unsorted['Algorithm'] == algo]
    sorted_algo = sorted_data[sorted_data['Algorithm'] == algo]
    ax4.plot(unsorted_algo['Size'], unsorted_algo['TimeMs'], marker='o', label=f'{algo} (Desordenado)', linewidth=2)
    ax4.plot(sorted_algo['Size'], sorted_algo['TimeMs'], marker='s', linestyle='--', label=f'{algo} (Ordenado)', linewidth=2)

ax4.legend(fontsize=8)

# 5. Theoretical complexity curves
ax5 = plt.subplot(2, 3, 5)
ax5.set_title('Complejidad Teórica Esperada', fontsize=12, fontweight='bold')
ax5.set_xlabel('Número de Elementos (n)', fontsize=10)
ax5.set_ylabel('Operaciones Relativas', fontsize=10)
ax5.grid(True, alpha=0.3)

n = np.array(sizes)
# Normalize to make curves visible
ax5.plot(n, n / 1000, label='O(n) - RadixSort', linewidth=2.5, linestyle='-')
ax5.plot(n, (n * np.log2(n)) / 10000, label='O(n log n) - MergeSort, QuickSort', linewidth=2.5, linestyle='-')
ax5.plot(n, (n**2) / 10000000, label='O(n²) - GnomeSort, InsertionSort', linewidth=2.5, linestyle='-')

ax5.legend(fontsize=9)

# 6. Summary table with Big O notation
ax6 = plt.subplot(2, 3, 6)
ax6.axis('tight')
ax6.axis('off')

table_data = [
    ['Algoritmo', 'Mejor Caso', 'Caso Promedio', 'Peor Caso', 'Espacio'],
    ['GnomeSort', 'O(n)', 'O(n²)', 'O(n²)', 'O(1)'],
    ['MergeSort', 'O(n log n)', 'O(n log n)', 'O(n log n)', 'O(n)'],
    ['QuickSort*', 'O(n log n)', 'O(n log n)', 'O(n²)', 'O(log n)'],
    ['InsertionSort', 'O(n)', 'O(n²)', 'O(n²)', 'O(1)'],
    ['RadixSort', 'O(d·n)', 'O(d·n)', 'O(d·n)', 'O(n+k)'],
]

table = ax6.table(cellText=table_data, cellLoc='left', loc='center',
                  colWidths=[0.18, 0.18, 0.21, 0.18, 0.15])
table.auto_set_font_size(False)
table.set_fontsize(9)
table.scale(1, 2)

# Style header row
for i in range(5):
    table[(0, i)].set_facecolor('#4CAF50')
    table[(0, i)].set_text_props(weight='bold', color='white')

ax6.set_title('Complejidad de Tiempo y Espacio\n*QuickSort con median-of-three',
              fontsize=11, fontweight='bold', pad=20)

plt.suptitle('Análisis de Rendimiento: Algoritmos de Ordenamiento',
             fontsize=16, fontweight='bold', y=0.98)

plt.tight_layout(rect=[0, 0, 1, 0.96])
plt.savefig('performance_analysis.png', dpi=300, bbox_inches='tight')
print("✓ Graph saved as performance_analysis.png")

# Also save individual graphs for the report
# Simple comparison graph
fig2, ax = plt.subplots(figsize=(12, 7))
ax.set_title('Comparación de Rendimiento: Caso Promedio vs Mejor Caso', fontsize=14, fontweight='bold')
ax.set_xlabel('Número de Elementos', fontsize=12)
ax.set_ylabel('Tiempo (milisegundos)', fontsize=12)
ax.grid(True, alpha=0.3)

colors = {'GnomeSort': 'red', 'MergeSort': 'blue', 'QuickSort': 'green',
          'InsertionSort': 'orange', 'RadixSort': 'purple'}

for algo in algorithms:
    unsorted_algo = unsorted[unsorted['Algorithm'] == algo]
    sorted_algo = sorted_data[sorted_data['Algorithm'] == algo]

    ax.plot(unsorted_algo['Size'], unsorted_algo['TimeMs'],
            marker='o', color=colors[algo], label=f'{algo} (Desordenado)',
            linewidth=2, markersize=5)
    ax.plot(sorted_algo['Size'], sorted_algo['TimeMs'],
            marker='s', color=colors[algo], linestyle='--',
            label=f'{algo} (Ordenado)', linewidth=2, markersize=5, alpha=0.7)

ax.legend(fontsize=9, ncol=2)
plt.tight_layout()
plt.savefig('simple_comparison.png', dpi=300, bbox_inches='tight')
print("✓ Simple comparison saved as simple_comparison.png")

print("\n📊 Graphs generated successfully!")
print("   - performance_analysis.png (comprehensive analysis)")
print("   - simple_comparison.png (for report)")
