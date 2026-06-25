import { Component, input, output } from '@angular/core';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { TagModule } from 'primeng/tag';

@Component({
  selector: 'app-purchase-table',
  imports: [TableModule, ButtonModule, TooltipModule, TagModule],
  templateUrl: './purchase-table.html',
})
export class PurchaseTable {
  compras = input.required<any[]>();
  loading = input<boolean>(false);
  error = input<string>('');

  verDetalle = output<any>();
  nuevaCompra = output<void>();

  protected readonly Number = Number;

  getIdCorto(id: string): string {
    return id?.substring(0, 8).toUpperCase() ?? '—';
  }
}