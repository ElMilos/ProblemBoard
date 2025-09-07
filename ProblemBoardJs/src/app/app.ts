import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from './services/auth-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterOutlet, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  u = ''; p = '';
  constructor(public auth: AuthService) { }
  login() { if (this.u && this.p) { this.auth.login(this.u, this.p); location.reload(); } }
  logout() { this.auth.logout(); location.reload(); }
}
