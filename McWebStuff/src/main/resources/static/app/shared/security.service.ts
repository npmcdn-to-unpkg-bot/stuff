import { Injectable }     from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';

@Injectable()
export class SecurityService {
  private _securityUrl = 'rest/security';  // URL to web api
  private _auth = null;
  constructor (private _http: Http) {}
  public getAuth() {
	  return this._auth;
  }
  public getCurrentAuth() {
    return this._http.get(this._securityUrl +'/currentAuth')
	.forEach(res => {
		this._auth = res.json();
	});
  }
}