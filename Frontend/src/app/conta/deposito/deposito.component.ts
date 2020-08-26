import { MovimentacaoEntity } from './../../entity/movimentacao.entity';
import { Router } from '@angular/router';
import { ToastService } from './../../util/toast/toast.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ContaService } from 'src/app/services/conta.service';

@Component({
  selector: 'app-deposito',
  templateUrl: './deposito.component.html',
  styleUrls: ['./deposito.component.css']
})
export class DepositoComponent implements OnInit {

  depositoForm: FormGroup;
  constructor(private contaService: ContaService, private toast: ToastService, private router: Router) { }

  ngOnInit(): void {

    this.depositoForm = new FormGroup({
      quantia: new FormControl()
    });
  }

  /**
   * Fazer o deposito na conta, baseada com dados passados pelo formulário
   */
  realizarDeposito(formData) {
    let mov = new MovimentacaoEntity();
    mov.quantia = formData.quantia;
    this.contaService.deposito(mov).subscribe(
      data => {
          this.toast.sucesso('Deposito realizado com sucesso.');
          this.router.navigate(['/conta']);
      },
      error => {
          this.toast.erro('Ocorreu uma falha ao tentar realizar o deposito, favor tentar novamente mais tarde');
      }
    )
  }

}
