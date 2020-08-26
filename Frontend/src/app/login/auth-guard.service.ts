import { ToastService } from './../util/toast/toast.service';
import { LoginService } from './../services/login.service';

import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

@Injectable()
export class AuthGuardService implements CanActivate {
    constructor(public loginService: LoginService, public toast: ToastService, public router: Router) { } canActivate(): boolean {
        if (!this.loginService.isAutenticado()) {
            this.router.navigate(['/login']);
            this.toast.show('Você precisa estar autenticado para acessar essa função.', { classname: 'bg-danger text-light' });
            return false;
        }
        return true;
    }
}
