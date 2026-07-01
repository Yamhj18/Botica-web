import { Component, inject, input, output } from '@angular/core';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { DecimalPipe } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sales-table',
  imports: [TableModule, ButtonModule, TooltipModule, DecimalPipe],
  templateUrl: './sales-table.html',
})
export class SalesTable {


  private readonly router = inject(Router);

  onNuevaVenta(): void {
    this.router.navigate(['/admin/ventas/nueva']);
  }
  ventas = input.required<any[]>();
  loading = input<boolean>(false);
  error = input<string>('');

  verDetalle = output<any>();
  nuevaVenta = output<void>();

  protected readonly Number = Number;
}