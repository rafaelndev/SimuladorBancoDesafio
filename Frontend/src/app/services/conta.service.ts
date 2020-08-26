import { TransacaoEntity } from './../entity/transacao.entity';
import { MovimentacaoEntity } from './../entity/movimentacao.entity';
import { ContaEntity } from './../entity/conta.entity';
import { Injectable } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  baseUrl = environment.baseUrl + '/conta';

  constructor(private http: HttpClient) { }

  getConta(): Observable<ContaEntity> {
    const url = `${this.baseUrl}`;
    return this.http.get<ContaEntity>(url);
  }

  saque(mov: MovimentacaoEntity) {
    const url = `${this.baseUrl}/saque`;
    return this.http.post<MovimentacaoEntity>(url, mov);
  }

  deposito(mov: MovimentacaoEntity) {
    const url = `${this.baseUrl}/deposito`;
    return this.http.post<MovimentacaoEntity>(url, mov);
  }

  /**
   * Retorna lista de transações feitas pelo cliente
   */
  listaTransacoes(): Observable<TransacaoEntity[]> {
    const url = `${this.baseUrl}/transacoes`;

    return this.http.get<TransacaoEntity[]>(url);
  }
}
