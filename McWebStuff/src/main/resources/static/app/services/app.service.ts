import { Injectable, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from "rxjs/Rx";
import "rxjs/Rx";

export class WebUserDTO {
	public userName: string;
	public displayName: string;
}

@Injectable()
export class AppService implements OnInit {
	private _currentUser : WebUserDTO;

	constructor(private _http: Http) {
	}
	
	ngOnInit() {
		this.getCurrentUser();
	}
	
	public getCurrentUser() {
		var service = this;
		if(this._currentUser) {
			return new Observable(function(observer){
				observer.next(service._currentUser);
				observer.complete();
				return;
			});
		} else {
			return this._http.get('/rest/app/currentUser')
			.map(function(res: Response) {	
				service._currentUser = <WebUserDTO> res.json();
				return service._currentUser;
			});	
		}
	}
	
}