import { TransacaoEnum } from './../entity/transacao.enum';
import { TransacaoEntity } from './../entity/transacao.entity';
import { ContaService } from './../services/conta.service';
import { ContaEntity } from './../entity/conta.entity';
import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import {UsuarioService} from '../services/usuario.service';
import {LoginService} from '../services/login.service';
import {UsuarioEntity} from '../entity/usuario.entity';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.css']
})
export class ContaComponent implements OnInit {
  closeResult = '';

  public conta: ContaEntity = null;
  public transacoes: TransacaoEntity[];

  public usuarioLogado: UsuarioEntity;

  TransacaoEnum = TransacaoEnum;

  constructor(private contaService: ContaService, private router: Router, private route: ActivatedRoute, private usuarioService: UsuarioService) { }

  ngOnInit(): void {

    this.loadConta();
    this.loadTransacoes();
    this.loadUsuarioLogado();
  }

  loadConta(): void {
    this.contaService.getConta().subscribe(res => {
      this.conta = res;
    });
  }

  loadTransacoes(): void {
    this.contaService.listaTransacoes().subscribe(res => {
      this.transacoes = res;
    });
  }

  loadUsuarioLogado(): void {
    this.usuarioService.getUsuarioLogado().subscribe(
      (user: UsuarioEntity) => {
        console.log(this.usuarioLogado);
        this.usuarioLogado = user;
      }
    );
  }

  saque() {
    this.router.navigate(['saque'], { relativeTo: this.route });
  }

  deposito() {
    this.router.navigate(['deposito'], { relativeTo: this.route });
  }
}
