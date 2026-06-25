import { Component, input, output } from '@angular/core';
import { DialogModule } from 'primeng/dialog';

@Component({
  selector: 'app-purchase-detail',
  imports: [DialogModule],
  templateUrl: './purchase-detail.html',
})
export class PurchaseDetail {
  compra = input<any>(null);
  visible = input<boolean>(false);
  visibleChange = output<boolean>();

  protected readonly Number = Number;

  getIdCorto(id: string): string {
    return id?.substring(0, 8).toUpperCase() ?? '—';
  }

  getCostoTotal(compra: any): number {
    return (compra?.detalles ?? []).reduce((acc: number, d: any) =>
      acc + (Number(d.quantity) * Number(d.unitCost)), 0);
  }
}