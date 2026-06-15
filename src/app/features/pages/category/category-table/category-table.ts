import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';

export interface Categoria {
  id: number | string;
  name: string;
  estado: 'activo' | 'inactivo';
}

@Component({
  selector: 'app-category-table',
  standalone: true,
  imports: [
    CommonModule,
    TableModule,
    TooltipModule
  ],
  templateUrl: './category-table.html',
  styleUrl: './category-table.css',
})
export class CategoryTable {
  @Input() categorias: Categoria[] = [];
  @Input() total: number = 0;
  @Input() loading: boolean = false;
  @Input() error: string | null = null;
  @Output() crearCategoria = new EventEmitter<void>();
  @Output() onEdit = new EventEmitter<Categoria>();
  @Output() onToggleStatus = new EventEmitter<Categoria>();
  @Output() onDelete = new EventEmitter<number | string>();

  exportarExcel(): void {
  }
}