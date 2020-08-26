import { ToastService } from './toast.service';
import { Component, OnInit, TemplateRef } from '@angular/core';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.css']
})
/**
 * ToastComponent:
 * https://ng-bootstrap.github.io/#/components/toast/overview
 */
export class ToastComponent implements OnInit {

  constructor(public toastService: ToastService) {}
  ngOnInit(): void {
  }

  isTemplate(toast) { return toast.textOrTpl instanceof TemplateRef; }

}
