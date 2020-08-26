import { Router } from '@angular/router';
import { ToastService } from './../util/toast/toast.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import { environment } from '../../environments/environment';
import {UsuarioEntity} from '../entity/usuario.entity';
import {UsuarioService} from './usuario.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = environment.baseUrl;

  private SESSION = 'authUsuario';

  private token: string;


  constructor(private http: HttpClient, private toast: ToastService, private router: Router, private usuarioService: UsuarioService) {
  }

  /**
   * Realiza o login de Autenticação Básica
   * @param usuario
   * @param senha 
   */
  login(usuario: string, senha: string): void {
    const token = this.basicToken(usuario, senha);


    this.http.get(`${this.baseUrl}/basiclogin`,
      {
        headers:
          new HttpHeaders({
            'Authorization': `${token}`
          })
      })
      .subscribe(
        data => {
          this.token = token;
          this.saveLogin(token);
          
          this.toast.sucesso('Login efetuado com sucesso, estamos te redirecionando para sua conta');
          this.router.navigate(['/']);
        },
        error => {
          this.toast.erro('Falha na autenticação, verifique seus dados ou crie uma nova conta.');
          this.logout();
        }
      );
  }
  logout(): void {
    sessionStorage.removeItem(this.SESSION);
    this.token = null;
  }
  basicToken(usuario: string, senha: string): string {
    return 'Basic ' + window.btoa(usuario + ':' + senha)
  }

  saveLogin(token: string): void {
    sessionStorage.setItem(this.SESSION, token);
  }

  getToken(): string {
    return sessionStorage.getItem(this.SESSION);
  }

  isAutenticado(): boolean {
    const usuario = sessionStorage.getItem(this.SESSION);

    if (usuario == null) { return false; }

    return true;
  }
}
