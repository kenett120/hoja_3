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

# Define colors consistently
colors = {
    'GnomeSort': '#e74c3c',      # Red
    'MergeSort': '#3498db',       # Blue
    'QuickSort': '#2ecc71',       # Green
    'InsertionSort': '#f39c12',   # Orange
    'RadixSort': '#9b59b6'        # Purple
}

# Create comprehensive figure
fig = plt.figure(figsize=(20, 12))
gs = fig.add_gridspec(3, 3, hspace=0.35, wspace=0.3)

# ============================================================================
# 1. ALL ALGORITHMS - Unsorted (Linear Scale)
# ============================================================================
ax1 = fig.add_subplot(gs[0, 0])
ax1.set_title('Caso Promedio - Todos los Algoritmos', fontsize=13, fontweight='bold')
ax1.set_xlabel('Número de Elementos', fontsize=11)
ax1.set_ylabel('Tiempo (ms)', fontsize=11)
ax1.grid(True, alpha=0.3, linestyle='--')

for algo in algorithms:
    algo_data = unsorted[unsorted['Algorithm'] == algo]
    ax1.plot(algo_data['Size'], algo_data['TimeMs'],
             marker='o', label=algo, linewidth=2.5, markersize=7,
             color=colors[algo], markeredgecolor='white', markeredgewidth=1.5)

ax1.legend(fontsize=10, framealpha=0.9, loc='upper left')
ax1.set_xlim(left=-2000)

# ============================================================================
# 2. FAST ALGORITHMS ONLY - Unsorted (Linear Scale) - ZOOMED IN
# ============================================================================
ax2 = fig.add_subplot(gs[0, 1])
ax2.set_title('Algoritmos Eficientes - Caso Promedio (Zoom)', fontsize=13, fontweight='bold')
ax2.set_xlabel('Número de Elementos', fontsize=11)
ax2.set_ylabel('Tiempo (ms)', fontsize=11)
ax2.grid(True, alpha=0.3, linestyle='--')

fast_algos = ['MergeSort', 'QuickSort', 'RadixSort']
for algo in fast_algos:
    algo_data = unsorted[unsorted['Algorithm'] == algo]
    ax2.plot(algo_data['Size'], algo_data['TimeMs'],
             marker='o', label=algo, linewidth=2.5, markersize=7,
             color=colors[algo], markeredgecolor='white', markeredgewidth=1.5)

ax2.legend(fontsize=10, framealpha=0.9)
ax2.set_xlim(left=-2000)

# ============================================================================
# 3. ALL ALGORITHMS - Logarithmic Scale
# ============================================================================
ax3 = fig.add_subplot(gs[0, 2])
ax3.set_title('Caso Promedio - Escala Logarítmica', fontsize=13, fontweight='bold')
ax3.set_xlabel('Número de Elementos', fontsize=11)
ax3.set_ylabel('Tiempo (ms) - Log Scale', fontsize=11)
ax3.grid(True, alpha=0.3, which='both', linestyle='--')

for algo in algorithms:
    algo_data = unsorted[unsorted['Algorithm'] == algo]
    # Add 1 to avoid log(0)
    ax3.plot(algo_data['Size'], algo_data['TimeMs'] + 1,
             marker='o', label=algo, linewidth=2.5, markersize=7,
             color=colors[algo], markeredgecolor='white', markeredgewidth=1.5)

ax3.set_yscale('log')
ax3.legend(fontsize=10, framealpha=0.9)

# ============================================================================
# 4. BEST CASE - All algorithms
# ============================================================================
ax4 = fig.add_subplot(gs[1, 0])
ax4.set_title('Mejor Caso (Datos Ordenados)', fontsize=13, fontweight='bold')
ax4.set_xlabel('Número de Elementos', fontsize=11)
ax4.set_ylabel('Tiempo (ms)', fontsize=11)
ax4.grid(True, alpha=0.3, linestyle='--')

for algo in algorithms:
    algo_data = sorted_data[sorted_data['Algorithm'] == algo]
    ax4.plot(algo_data['Size'], algo_data['TimeMs'],
             marker='s', label=algo, linewidth=2.5, markersize=7,
             color=colors[algo], markeredgecolor='white', markeredgewidth=1.5)

ax4.legend(fontsize=10, framealpha=0.9)
ax4.set_xlim(left=-2000)

# ============================================================================
# 5. COMPARISON - Unsorted vs Sorted for fast algorithms
# ============================================================================
ax5 = fig.add_subplot(gs[1, 1])
ax5.set_title('Promedio vs Mejor Caso (Algoritmos Eficientes)', fontsize=13, fontweight='bold')
ax5.set_xlabel('Número de Elementos', fontsize=11)
ax5.set_ylabel('Tiempo (ms)', fontsize=11)
ax5.grid(True, alpha=0.3, linestyle='--')

for algo in fast_algos:
    unsorted_algo = unsorted[unsorted['Algorithm'] == algo]
    sorted_algo = sorted_data[sorted_data['Algorithm'] == algo]

    ax5.plot(unsorted_algo['Size'], unsorted_algo['TimeMs'],
             marker='o', label=f'{algo} (Desordenado)', linewidth=2.5, markersize=6,
             color=colors[algo], markeredgecolor='white', markeredgewidth=1.5)
    ax5.plot(sorted_algo['Size'], sorted_algo['TimeMs'],
             marker='s', linestyle='--', label=f'{algo} (Ordenado)', linewidth=2.5, markersize=6,
             color=colors[algo], alpha=0.7, markeredgecolor='white', markeredgewidth=1.5)

ax5.legend(fontsize=9, framealpha=0.9, ncol=1)
ax5.set_xlim(left=-2000)

# ============================================================================
# 6. THEORETICAL COMPLEXITY
# ============================================================================
ax6 = fig.add_subplot(gs[1, 2])
ax6.set_title('Complejidad Teórica', fontsize=13, fontweight='bold')
ax6.set_xlabel('Número de Elementos (n)', fontsize=11)
ax6.set_ylabel('Operaciones (Normalizadas)', fontsize=11)
ax6.grid(True, alpha=0.3, linestyle='--')

n = np.array(sizes)
# Normalize for better visualization
ax6.plot(n, n / 1000, label='O(n) - RadixSort',
         linewidth=3.5, color='#9b59b6', linestyle='-')
ax6.plot(n, (n * np.log2(n)) / 8000, label='O(n log n) - MergeSort, QuickSort',
         linewidth=3.5, color='#3498db', linestyle='-')
ax6.plot(n, (n**2) / 8000000, label='O(n²) - GnomeSort, InsertionSort',
         linewidth=3.5, color='#e74c3c', linestyle='-')

ax6.legend(fontsize=10, framealpha=0.9)
ax6.set_xlim(left=-2000)

# ============================================================================
# 7-9. BOTTOM ROW: Individual algorithm comparisons
# ============================================================================

# O(n²) algorithms comparison
ax7 = fig.add_subplot(gs[2, 0])
ax7.set_title('Algoritmos O(n²) - Promedio vs Mejor Caso', fontsize=13, fontweight='bold')
ax7.set_xlabel('Número de Elementos', fontsize=11)
ax7.set_ylabel('Tiempo (ms)', fontsize=11)
ax7.grid(True, alpha=0.3, linestyle='--')

slow_algos = ['GnomeSort', 'InsertionSort']
for algo in slow_algos:
    unsorted_algo = unsorted[unsorted['Algorithm'] == algo]
    sorted_algo = sorted_data[sorted_data['Algorithm'] == algo]

    ax7.plot(unsorted_algo['Size'], unsorted_algo['TimeMs'],
             marker='o', label=f'{algo} (Desordenado)', linewidth=2.5, markersize=6,
             color=colors[algo], markeredgecolor='white', markeredgewidth=1.5)
    ax7.plot(sorted_algo['Size'], sorted_algo['TimeMs'],
             marker='s', linestyle='--', label=f'{algo} (Ordenado)', linewidth=2.5, markersize=6,
             color=colors[algo], alpha=0.7, markeredgecolor='white', markeredgewidth=1.5)

ax7.legend(fontsize=9, framealpha=0.9)
ax7.set_xlim(left=-2000)

# Complexity table
ax8 = fig.add_subplot(gs[2, 1:])
ax8.axis('tight')
ax8.axis('off')

table_data = [
    ['Algoritmo', 'Mejor Caso', 'Caso Promedio', 'Peor Caso', 'Espacio', 'Estable'],
    ['GnomeSort', 'O(n)', 'O(n²)', 'O(n²)', 'O(1)', 'Sí'],
    ['MergeSort', 'O(n log n)', 'O(n log n)', 'O(n log n)', 'O(n)', 'Sí'],
    ['QuickSort*', 'O(n log n)', 'O(n log n)', 'O(n²)', 'O(log n)', 'No'],
    ['InsertionSort', 'O(n)', 'O(n²)', 'O(n²)', 'O(1)', 'Sí'],
    ['RadixSort', 'O(d·n)', 'O(d·n)', 'O(d·n)', 'O(n+k)', 'Sí'],
]

table = ax8.table(cellText=table_data, cellLoc='center', loc='center',
                  colWidths=[0.16, 0.16, 0.18, 0.16, 0.14, 0.12])
table.auto_set_font_size(False)
table.set_fontsize(11)
table.scale(1, 3)

# Style header row
for i in range(6):
    cell = table[(0, i)]
    cell.set_facecolor('#2c3e50')
    cell.set_text_props(weight='bold', color='white', size=12)

# Alternate row colors
for i in range(1, 6):
    for j in range(6):
        cell = table[(i, j)]
        if i % 2 == 0:
            cell.set_facecolor('#ecf0f1')
        else:
            cell.set_facecolor('#ffffff')
        cell.set_text_props(size=10)

ax8.set_title('Tabla de Complejidades\n*QuickSort implementado con median-of-three pivot',
              fontsize=13, fontweight='bold', pad=30)

# Main title
plt.suptitle('Análisis Completo de Rendimiento: Algoritmos de Ordenamiento\n' +
             'Joao Castillo (25776) y Kenett Ortega (25777)',
             fontsize=16, fontweight='bold', y=0.995)

plt.savefig('performance_analysis.png', dpi=300, bbox_inches='tight', facecolor='white')
print("✓ Comprehensive analysis saved as performance_analysis.png")

# ============================================================================
# SIMPLE GRAPH FOR REPORT
# ============================================================================
fig2, (ax_top, ax_bottom) = plt.subplots(2, 1, figsize=(14, 10))

# Top: Unsorted data
ax_top.set_title('Caso Promedio (Datos Desordenados)', fontsize=14, fontweight='bold', pad=15)
ax_top.set_xlabel('Número de Elementos', fontsize=12)
ax_top.set_ylabel('Tiempo (milisegundos)', fontsize=12)
ax_top.grid(True, alpha=0.3, linestyle='--', linewidth=0.7)

for algo in algorithms:
    algo_data = unsorted[unsorted['Algorithm'] == algo]
    ax_top.plot(algo_data['Size'], algo_data['TimeMs'],
                marker='o', color=colors[algo], label=algo,
                linewidth=3, markersize=8, markeredgecolor='white', markeredgewidth=2)

ax_top.legend(fontsize=11, framealpha=0.95, loc='upper left')
ax_top.set_xlim(left=-2000)

# Bottom: Sorted data
ax_bottom.set_title('Mejor Caso (Datos Ordenados)', fontsize=14, fontweight='bold', pad=15)
ax_bottom.set_xlabel('Número de Elementos', fontsize=12)
ax_bottom.set_ylabel('Tiempo (milisegundos)', fontsize=12)
ax_bottom.grid(True, alpha=0.3, linestyle='--', linewidth=0.7)

for algo in algorithms:
    algo_data = sorted_data[sorted_data['Algorithm'] == algo]
    ax_bottom.plot(algo_data['Size'], algo_data['TimeMs'],
                   marker='s', color=colors[algo], label=algo,
                   linewidth=3, markersize=8, markeredgecolor='white', markeredgewidth=2)

ax_bottom.legend(fontsize=11, framealpha=0.95, loc='upper left')
ax_bottom.set_xlim(left=-2000)

plt.suptitle('Comparación de Rendimiento: Algoritmos de Ordenamiento',
             fontsize=16, fontweight='bold', y=0.995)
plt.tight_layout()
plt.savefig('simple_comparison.png', dpi=300, bbox_inches='tight', facecolor='white')
print("✓ Simple comparison saved as simple_comparison.png")

print("\n📊 Graphs generated successfully!")
print("   - performance_analysis.png (comprehensive 9-panel analysis)")
print("   - simple_comparison.png (clean 2-panel comparison)")
