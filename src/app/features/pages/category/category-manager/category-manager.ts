import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChartModule } from 'primeng/chart';

@Component({
  selector: 'app-category-manager',
  standalone: true,
  imports: [
    CommonModule,
    ChartModule,
  ],
  templateUrl: './category-manager.html',
  styleUrl: './category-manager.css',
})
export class CategoryManager implements OnInit {

  listaCategorias = signal([
    { image: 'meds.jpg', name: 'Medicamentos', totalProducts: 77, status: 'activo' },
    { image: 'care.jpg', name: 'Cuidado Personal', totalProducts: 50, status: 'activo' },
    { image: '', name: 'Suplementos', totalProducts: 95, status: 'activo' },
    { image: 'beauty.jpg', name: 'Belleza', totalProducts: 60, status: 'inactivo' },
    { image: 'devices.jpg', name: 'Dispositivos', totalProducts: 30, status: 'activo' }
  ]);

  chartData: any;
  chartOptions: any;

  ngOnInit() {
    this.initChart();
  }

  initChart() {
    this.chartData = {
      labels: ['Medicamentos', 'Cuidado Personal', 'Suplementos', 'Belleza', 'Dispositivos'],
      datasets: [
        {
          label: 'Productos Activos',
          backgroundColor: '#ec4899',
          hoverBackgroundColor: '#db2777',
          data: [65, 42, 80, 51, 24]
        },
        {
          label: 'Productos Inactivos',
          backgroundColor: '#cbd5e1',
          hoverBackgroundColor: '#94a3b8',
          data: [12, 8, 15, 9, 6]
        }
      ]
    };

    this.chartOptions = {
      maintainAspectRatio: false,
      aspectRatio: 0.8,
      plugins: {
        legend: {
          position: 'top',
          labels: {
            color: '#475569',
            boxWidth: 12,
            usePointStyle: true,
            font: { size: 11, weight: '600' }
          }
        },
        tooltip: {
          padding: 12,
          cornerRadius: 12
        }
      },
      scales: {
        x: {
          ticks: { color: '#94a3b8', font: { size: 11 } },
          grid: { display: false }
        },
        y: {
          ticks: { color: '#94a3b8', font: { size: 11 } },
          grid: { color: '#f1f5f9' }
        }
      }
    };
  }

}