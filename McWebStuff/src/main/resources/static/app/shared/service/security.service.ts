import { Injectable }     from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';

@Injectable()
export class SecurityService {
  private _securityUrl = 'rest/security'; 
  private _auth = null;
  
  constructor (private _http: Http) {}
  
  public getAuth() {
	  return this._auth;
  }
  
  public getCurrentAuthFromServer() {
	if(this._auth) {
		return new Promise<any>(function(resolve, reject){
			resolve(null);
	    });
	} else {
		var service = this;
	    return this._http.get(this._securityUrl +'/currentAuth')
		.forEach(res => {
			service._auth = res.json();
		});
	}
  }
}