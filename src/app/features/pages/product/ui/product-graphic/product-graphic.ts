import { ChangeDetectionStrategy, Component, signal, computed, input } from '@angular/core';
import { ChartModule } from 'primeng/chart';
import { ChartData, ChartOptions } from 'chart.js';
import { NgClass } from '@angular/common';

type Periodo = 'dia' | 'semana' | 'mes' | 'anio';

@Component({
  selector: 'app-product-graphic',
  standalone: true,
  imports: [ChartModule, NgClass],
  templateUrl: './product-graphic.html',
  styleUrl: './product-graphic.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProductGraphic {

  productos = input<any[]>([]);

  periodo = signal<Periodo>('dia');

  readonly opcionesPeriodo: { label: string; value: Periodo }[] = [
    { label: 'Días', value: 'dia' },
    { label: 'Semana', value: 'semana' },
    { label: 'Meses', value: 'mes' },
    { label: 'Año', value: 'anio' },
  ];

  onPeriodoChange(valor: Periodo): void {
    this.periodo.set(valor);
  }

  private toLocalDateString(fecha: Date): string {
    const y = fecha.getFullYear();
    const m = String(fecha.getMonth() + 1).padStart(2, '0');
    const d = String(fecha.getDate()).padStart(2, '0');
    return `${y}-${m}-${d}`;
  }

  private agrupados = computed(() => {
    const lista = this.productos();
    const mapa = new Map<string, number>();

    for (const p of lista) {
      if (!p.createdAt) continue;
      const fecha = new Date(p.createdAt.replace(' ', 'T'));
      const key = this.getKey(fecha, this.periodo());
      mapa.set(key, (mapa.get(key) ?? 0) + 1);
    }

    return new Map([...mapa.entries()].sort());
  });

  private getKey(fecha: Date, periodo: Periodo): string {
    switch (periodo) {
      case 'dia':
        return this.toLocalDateString(fecha);

      case 'semana': {
        const inicio = new Date(fecha);
        const dia = inicio.getDay();
        const diff = inicio.getDate() - dia + (dia === 0 ? -6 : 1);
        inicio.setDate(diff);
        return this.toLocalDateString(inicio);
      }

      case 'mes':
        return `${fecha.getFullYear()}-${String(fecha.getMonth() + 1).padStart(2, '0')}`;

      case 'anio':
        return `${fecha.getFullYear()}`;
    }
  }

  private formatLabel(key: string, periodo: Periodo): string {
    switch (periodo) {
      case 'dia':
        return new Date(key + 'T00:00:00').toLocaleDateString('es-PE', {
          day: '2-digit',
          month: 'short',
        });

      case 'semana':
        return `Sem. ${new Date(key + 'T00:00:00').toLocaleDateString('es-PE', {
          day: '2-digit',
          month: 'short',
        })}`;

      case 'mes': {
        const [anio, mes] = key.split('-');
        return new Date(Number(anio), Number(mes) - 1).toLocaleDateString('es-PE', {
          month: 'short',
          year: 'numeric',
        });
      }

      case 'anio':
        return key;
    }
  }

  chartData = computed<ChartData>(() => {
    const mapa = this.agrupados();
    const labels = [...mapa.keys()].map(k => this.formatLabel(k, this.periodo()));
    const data = [...mapa.values()];

    return {
      labels,
      datasets: [
        {
          label: 'Productos registrados',
          data,
          borderColor: '#ec4899',
          backgroundColor: 'rgba(236, 72, 153, 0.1)',
          tension: 0.4,
          fill: true,
          pointBackgroundColor: '#ec4899',
          pointRadius: 4,
          pointHoverRadius: 6,
        },
      ],
    };
  });

  chartOptions = signal<ChartOptions>({
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        padding: 12,
        cornerRadius: 12,
        callbacks: {
          label: (ctx) => ` ${ctx.parsed.y} producto(s) registrado(s)`,
        },
      },
    },
    scales: {
      x: {
        ticks: { color: '#94a3b8', font: { size: 11 } },
        grid: { display: false },
      },
      y: {
        beginAtZero: true,
        ticks: {
          color: '#94a3b8',
          font: { size: 11 },
          stepSize: 1,
        },
        grid: { color: '#f1f5f9' },
      },
    },
  });
}