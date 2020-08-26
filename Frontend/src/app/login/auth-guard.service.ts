import { ToastService } from './../util/toast/toast.service';
import { LoginService } from './../services/login.service';

import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

@Injectable()
/**
 * RouteGuard para validar se o usuário pode ou não acessar o conteudo dependendo do estado de autenticação
 */
export class AuthGuardService implements CanActivate {
    constructor(public loginService: LoginService, public toast: ToastService, public router: Router) { } canActivate(): boolean {
        if (!this.loginService.isAutenticado()) {
            this.router.navigate(['/login']);
            this.toast.erro('Você precisa estar autenticado para acessar essa função.');
            return false;
        }
        return true;
    }
}
