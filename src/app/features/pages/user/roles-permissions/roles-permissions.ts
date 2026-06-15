import { Component } from '@angular/core';
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';

interface Permiso {
  modulo: string;
  icono: string;
  accion: string;
  vendedor: boolean;
  quimico: boolean;
}

@Component({
  selector: 'app-roles-permisos',
  imports: [TableModule, TagModule],
  templateUrl: './roles-permissions.html',
  styleUrl: './roles-permissions.css',
})
export class RolesPermissions {

  permisos: Permiso[] = [
    { modulo: 'Productos', icono: 'pi-box', accion: 'Ver', vendedor: true, quimico: true },
    { modulo: 'Productos', icono: 'pi-box', accion: 'Crear / Editar', vendedor: true, quimico: false },
    { modulo: 'Productos', icono: 'pi-box', accion: 'Eliminar', vendedor: false, quimico: false },
    { modulo: 'Ventas', icono: 'pi-shopping-cart', accion: 'Ver', vendedor: true, quimico: false },
    { modulo: 'Ventas', icono: 'pi-shopping-cart', accion: 'Crear', vendedor: true, quimico: false },
    { modulo: 'Clientes', icono: 'pi-users', accion: 'Ver', vendedor: true, quimico: false },
    { modulo: 'Clientes', icono: 'pi-users', accion: 'Crear / Editar', vendedor: true, quimico: false },
    { modulo: 'Inventario', icono: 'pi-warehouse', accion: 'Ver', vendedor: true, quimico: true },
    { modulo: 'Inventario', icono: 'pi-warehouse', accion: 'Editar stock', vendedor: false, quimico: true },
    { modulo: 'Reportes', icono: 'pi-chart-bar', accion: 'Ver', vendedor: false, quimico: true },
    { modulo: 'Alertas', icono: 'pi-bell', accion: 'Ver', vendedor: true, quimico: true },
    { modulo: 'Configuración', icono: 'pi-cog', accion: 'Ver / Editar', vendedor: false, quimico: false },
    { modulo: 'Usuarios', icono: 'pi-user', accion: 'Ver / Editar', vendedor: false, quimico: false },
  ];

  roles = [
    { key: 'administrador', label: 'Administrador', icon: 'pi-shield', class: 'bg-purple-100 text-purple-700' },
    { key: 'vendedor', label: 'Vendedor', icon: 'pi-shopping-cart', class: 'bg-blue-100 text-blue-700' },
    { key: 'quimico', label: 'Químico', icon: 'pi-flask', class: 'bg-green-100 text-green-700' },
  ];

  tienePermiso(permiso: Permiso, rol: string): boolean {
    if (rol === 'administrador') return true;
    if (rol === 'vendedor') return permiso.vendedor;
    if (rol === 'quimico') return permiso.quimico;
    return false;
  }
}