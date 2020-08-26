import { Router } from '@angular/router';
import { ToastService } from './../../util/toast/toast.service';
import { UsuarioService } from './../../services/usuario.service';
import { UsuarioEntity } from './../../entity/usuario.entity';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  registroForm: FormGroup;

  constructor(private usuarioService: UsuarioService, private toast: ToastService, private router: Router) { }

  ngOnInit(): void {
    this.registroForm = new FormGroup({
      nome: new FormControl(),
      sobrenome: new FormControl(),
      email: new FormControl(),
      telefone: new FormControl(),
      usuario: new FormControl(),
      senha: new FormControl()
    });
  }

  registro(formData) {
    let novoUsuario = new UsuarioEntity();
    novoUsuario.primeiroNome = formData.nome;
    novoUsuario.segundoNome = formData.sobrenome;
    novoUsuario.email = formData.email;
    novoUsuario.telefone = formData.telefone;
    novoUsuario.usuario = formData.usuario;
    novoUsuario.senha = formData.senha;

    this.usuarioService.novoUsuario(novoUsuario).subscribe(
      data => {
        this.toast.sucesso('Cadastro realizado com sucesso, transferindo para tela de Login.');
        this.router.navigate(['/login']);
      },
      error => {
        this.toast.erro('Falha ao cadastrar novo usuário.')
      }
    );

  }

}
