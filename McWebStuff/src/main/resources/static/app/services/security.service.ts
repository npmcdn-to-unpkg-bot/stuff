import { Injectable, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from "rxjs/Rx";
import "rxjs/Rx";

@Injectable()
export class SecurityService {
	private _currentAuth : any;

	constructor(private _http: Http) {
	}
	
	ngOnInit() {
		this.getCurrentAuth();
	}
	
	public getCurrentAuth() {
		var service = this;
		if(this._currentAuth) {
			return new Observable(function(observer){
				observer.next(service._currentAuth);
				observer.complete();
				return;
			});
		} else {
			return this._http.get('/rest/security/currentAuth')
			.map(function(res: Response) {	
				service._currentAuth = res.json();
				return service._currentAuth;
			});	
		}
	}
	
}