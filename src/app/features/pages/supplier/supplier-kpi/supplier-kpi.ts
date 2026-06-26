import { Component, input } from '@angular/core';

@Component({
  selector: 'app-supplier-kpi',
  imports: [],
  templateUrl: './supplier-kpi.html',
  styleUrl: './supplier-kpi.css',
})
export class SupplierKpi {
  totalProveedores = input.required<number>();
  activos = input.required<number>();
  inactivos = input.required<number>();
  conRuc = input.required<number>();
}