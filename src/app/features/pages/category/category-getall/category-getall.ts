import { Component, inject, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Api } from '../../../../api/api';
import { categoryGetall } from '../../../../api/functions';
import { DialogModule } from 'primeng/dialog';
import { TagModule } from 'primeng/tag';
import { TooltipModule } from 'primeng/tooltip';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { CategorySidebar } from '../category-sidebar/category-sidebar';
import { CategoryTable } from '../category-table/category-table';
import { MessageService } from 'primeng/api';
import { CategoryManager } from '../category-manager/category-manager';

@Component({
  selector: 'app-category-getall',
  standalone: true,
  imports: [
    CommonModule,
    DialogModule,
    TagModule,
    TooltipModule,
    ProgressSpinnerModule,
    CategorySidebar,
    CategoryTable,
    CategoryManager
  ],
  templateUrl: './category-getall.html',
  styleUrl: './category-getall.css'
})
export class CategoryGetall implements OnInit {
  private readonly api = inject(Api);
  private readonly messageService = inject(MessageService);

  categorias = signal<any[]>([]);
  loading = signal<boolean>(true);
  error = signal<string>('');
  busqueda = signal<string>('');
  estadoFiltro = signal<string>('');
  showInsertDialog = signal<boolean>(false);

  filtrados = computed(() => {
    const q = this.busqueda().toLowerCase().trim();
    const estado = this.estadoFiltro().toLowerCase();
    let lista = this.categorias();

    if (q) lista = lista.filter((c: any) => c.name?.toLowerCase().includes(q));
    if (estado) lista = lista.filter((c: any) => c.status?.toLowerCase() === estado);

    return lista;
  });

  totalCategorias = computed(() => this.categorias().length);
  totalActivas = computed(() => this.categorias().filter((c: any) => c.status?.toLowerCase() === 'activo').length);
  totalInactivas = computed(() => this.categorias().filter((c: any) => c.status?.toLowerCase() !== 'activo').length);

  ngOnInit(): void {
    this.initialization();
  }

  private initialization(): void {
    this.loading.set(true);
    this.error.set('');
    this.api.invoke$Response(categoryGetall).then((raw: any) => {
      const data = typeof raw.body === 'string' ? JSON.parse(raw.body) : raw.body;
      if (data.type !== 'success') {
        this.error.set(data.listMessage[0] ?? 'Error al cargar categorías.');
        return;
      }
      this.categorias.set(data.listCategories ?? []);
    }).catch(() => {
      this.error.set('Error al cargar categorías.');
    }).finally(() => {
      this.loading.set(false);
    });
  }

  getSeverity(status: string): 'success' | 'danger' {
    return status?.toLowerCase() === 'activo' ? 'success' : 'danger';
  }

  onBusquedaChange(valor: string): void { this.busqueda.set(valor); }
  onEstadoChange(valor: string): void { this.estadoFiltro.set(valor); }
  onCrearCategoria(): void { this.showInsertDialog.set(true); }

  onCategoriaRegistrada(): void {
    this.showInsertDialog.set(false);
    this.initialization();
  }

  onEditar(categoria: any): void {
  }

  onToggleStatus(categoria: any): void {
    const nuevoEstado = categoria.status?.toLowerCase() === 'activo' ? 'inactivo' : 'activo';
    categoria.status = nuevoEstado;
    this.categorias.set([...this.categorias()]);
    this.messageService.add({
      severity: nuevoEstado === 'activo' ? 'success' : 'warn',
      summary: 'Estado cambiado',
      detail: `Categoría ${nuevoEstado}.`,
      life: 3000
    });
  }

  onEliminar(idCategory: any): void { }
}