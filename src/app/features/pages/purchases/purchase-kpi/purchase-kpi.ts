import { Component, input } from '@angular/core';

@Component({
  selector: 'app-purchase-kpi',
  imports: [],
  templateUrl: './purchase-kpi.html',
})
export class PurchaseKpi {
  totalCompras = input.required<number>();
  montoTotal = input.required<number>();
  proveedoresDistintos = input.required<number>();
  promedioPorCompra = input.required<number>();
}