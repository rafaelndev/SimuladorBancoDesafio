import { HttpClient } from '@angular/common/http';
import { UsuarioEntity } from './../entity/usuario.entity';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {


  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  novoUsuario(user: UsuarioEntity): Observable<UsuarioEntity> {
    const url = `${this.baseUrl}/registro`;

    return this.http.post<UsuarioEntity>(url, user);
  }
}
