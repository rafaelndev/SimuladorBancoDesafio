import { TemplateRef, Injectable } from '@angular/core';
/**
 * ToastService:
 * 
https://ng-bootstrap.github.io/#/components/toast/overview
 */
@Injectable({ providedIn: 'root' })
export class ToastService {
  toasts: any[] = [];

  show(textOrTpl: string | TemplateRef<any>, options: any = {}) {
    this.toasts.push({ textOrTpl, ...options });
  }

  remove(toast) {
    this.toasts = this.toasts.filter(t => t !== toast);
  }

  sucesso(texto: string): void {
    this.show(texto, { classname: 'bg-success' });
  }

  erro(texto: string): void {
    this.show(texto, { classname: 'bg-danger text-light' });
  }
}