import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private key = 'tri_auth';

  private _token: string | null = null;

  constructor() {
    if (typeof localStorage !== 'undefined') {
      this._token = localStorage.getItem(this.key);
    }
  }

  get token() { return this._token; }

  login(username: string, password: string) {
    this._token = btoa(`${username}:${password}`);
    localStorage.setItem(this.key, this._token);
  }

  logout() {
    this._token = null;
    localStorage.removeItem(this.key);
  }

  isLoggedIn() {
    return !!this._token;
  }
}
