import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';

@Injectable()
export class SecurityService {
	private _currentAuth : any;

	constructor(private _http: Http) {
		this.getCurrentAuth();
	}
	
	public getCurrentAuth() : Observable<any> {
		if(!this._currentAuth) {
			var service = this;
			return this._http.get('/rest/security/currentAuth')
			.map(function(res: Response) {				
				service._currentAuth = res.json();
				return service._currentAuth || { };
			})
			.catch(function(error, caught) : Observable<any> {
				let errMsg = error.message || 'Server error';
			    console.error(errMsg); // log to console instead
			    return Observable.throw(errMsg);
			})			
		} else {
			return new Observable(this._currentAuth);
		}
	}
	
}