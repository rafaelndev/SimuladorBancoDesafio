import { LoginService } from './services/login.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Frontend';

  constructor(private loginService: LoginService, private router: Router) {

      
    }

  logout(): void {
    if (this.loginService.isAutenticado()) {
      this.loginService.logout();
      this.router.navigate(['/login']);
    }
  }
}
