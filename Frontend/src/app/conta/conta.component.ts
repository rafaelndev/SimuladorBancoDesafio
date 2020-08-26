import { TransacaoEnum } from './../entity/transacao.enum';
import { TransacaoEntity } from './../entity/transacao.entity';
import { ContaService } from './../services/conta.service';
import { ContaEntity } from './../entity/conta.entity';
import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.css']
})
export class ContaComponent implements OnInit {
  closeResult = '';

  public conta: ContaEntity = null;
  public transacoes: TransacaoEntity[];

  TransacaoEnum = TransacaoEnum;

  constructor(private contaService: ContaService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.loadConta();
    this.loadTransacoes();
  }

  loadConta(): void {
    this.contaService.getConta().subscribe(res => {
      this.conta = res;
    });
  }

  loadTransacoes(): void {
    this.contaService.listaTransacoes().subscribe(res => {
      this.transacoes = res;
      console.log(this.transacoes);
    });
  }

  saque() {
    this.router.navigate(['saque'], { relativeTo: this.route });
  }

  deposito() {
    this.router.navigate(['deposito'], { relativeTo: this.route });
  }
}
