import { ToastService } from './../util/toast/toast.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from './../services/login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private loginService: LoginService, private router: Router, private toast: ToastService) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      usuario: new FormControl(),
      senha: new FormControl()
    });
  }

  autenticar(formData): void {
    if (!formData.usuario || !formData.senha) {
          this.toast.show('Por favor verifique seus dados.', { classname: 'bg-danger text-light' });
      return;
    }
    this.loginService.login(formData.usuario, formData.senha);
  }
}
