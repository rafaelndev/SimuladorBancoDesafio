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

  realizarDeposito(formData) {
    let mov = new MovimentacaoEntity();
    mov.quantia = formData.quantia;
    this.contaService.deposito(mov).subscribe(
      data => {
          this.toast.show('Deposito realizado com sucesso.');
          this.router.navigate(['/conta']);
      },
      error => {
          this.toast.show('Ocorreu uma falha ao tentar realizar o deposito, favor tentar novamente mais tarde', { classname: 'bg-danger text-light' });
      }
    )
  }

}
