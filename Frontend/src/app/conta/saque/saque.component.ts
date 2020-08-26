import { ToastService } from './../../util/toast/toast.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MovimentacaoEntity } from 'src/app/entity/movimentacao.entity';
import { ContaService } from 'src/app/services/conta.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-saque',
  templateUrl: './saque.component.html',
  styleUrls: ['./saque.component.css']
})
export class SaqueComponent implements OnInit {

  saqueForm: FormGroup;
  constructor(private contaService: ContaService, private router: Router, private toast: ToastService) { }

  ngOnInit(): void {
    this.saqueForm = new FormGroup({
      quantia: new FormControl()
    });
  }

  realizarSaque(formData) {
    let mov = new MovimentacaoEntity();
    mov.quantia = formData.quantia;
    this.contaService.saque(mov).subscribe(
      data => {
          this.toast.show('Saque realizado com sucesso.');
          this.router.navigate(['/conta']);
      },
      err => {
        console.log(err);
          this.toast.show(err.error.message, { classname: 'bg-danger text-light' });
      }
    )
  }

}
