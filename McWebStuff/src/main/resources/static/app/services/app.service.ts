import { Injectable, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from "rxjs/Rx";
import "rxjs/Rx";

import { WebUserDTO } from "./model/webuser_dto";

@Injectable()
export class AppService implements OnInit {
	private _currentUser : WebUserDTO;

	constructor(private _http: Http) {
	}
	
	ngOnInit() {
		this.getCurrentUser();
	}
	
	public getCurrentUser() : Observable<WebUserDTO> {
		var service = this;
		if(this._currentUser) {
			return new Observable<WebUserDTO> (function(observer){
				observer.next(service._currentUser);
				observer.complete();
				return;
			});
		} else {
			return this._http.get('/rest/app/currentUser')
			.map(function(res: Response) {
				return service._currentUser = res.json();
			});	
		}
	}
	
}