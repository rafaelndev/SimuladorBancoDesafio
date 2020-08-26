import { RegistroComponent } from './usuario/registro/registro.component';
import { AuthGuardService } from './login/auth-guard.service';
import { DepositoComponent } from './conta/deposito/deposito.component';
import { LoginComponent } from './login/login.component';
import { ContaComponent } from './conta/conta.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';
import { SaqueComponent } from './conta/saque/saque.component';

const routes: Routes = [
  { path: '', redirectTo: '/conta', pathMatch: 'full' },
  { path: 'conta', component: ContaComponent, canActivate: [AuthGuardService] },
  { path: 'conta/deposito', component: DepositoComponent, canActivate: [AuthGuardService] },
  { path: 'conta/saque', component: SaqueComponent, canActivate: [AuthGuardService] },
  { path: 'login', component: LoginComponent },
  { path: 'registrar', component: RegistroComponent },
  { path: 'logout', component: LoginComponent, canActivate: [AuthGuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
