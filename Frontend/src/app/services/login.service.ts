import { Router } from '@angular/router';
import { ToastService } from './../util/toast/toast.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  baseUrl = environment.baseUrl;

  SESSION = 'authUsuario';

  token: string;

  autenticado = false;

  constructor(private http: HttpClient, private toast: ToastService, private router: Router) {
  }

  login(usuario, senha) {
    const token = this.basicToken(usuario, senha);

    return this.http.get(`${this.baseUrl}/basiclogin`,
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
          console.log(error);
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
