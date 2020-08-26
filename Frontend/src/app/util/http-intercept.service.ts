import { Router } from '@angular/router';
import { LoginService } from './../services/login.service';

import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {tap, catchError} from 'rxjs/operators';
import { ToastService } from './toast/toast.service';

@Injectable()
export class HttpInterceptService implements HttpInterceptor {

    constructor(private loginService: LoginService, private router: Router, private toast: ToastService ) { }

    /**
     * Intercepta as requisições HTTP para enviar/validar a autenticação básica
     * @param req 
     * @param next 
     */
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.loginService.isAutenticado()) {
            const token = this.loginService.getToken();
            const authReq = req.clone({
                headers: new HttpHeaders({
                    'Content-Type': 'application/json',
                    'Authorization': `${token}`
                })
            });
            return next.handle(authReq).pipe(tap(() => { },
                (err: any) => {
                    this.validarPermissao(err);
                }
            ));
        } else {
            return next.handle(req).pipe(tap(() => { },
                (err: HttpErrorResponse) => {
                    this.validarPermissao(err);
                }
            ));
        }
    }


    /**
     * Validar caso a requisição retorne erro de permissão ou autorização
     * @param err 
     */
    validarPermissao(err: any): void {
        if (err instanceof HttpErrorResponse && (err.status === 403 || err.status === 401)) {
            this.loginService.logout();
            this.toast.erro('Sua sessão expirou, por favor entre novamente.');
            this.router.navigate(['/login']);
        } else {

        }
    }
}