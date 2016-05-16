import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable }     from 'rxjs/Observable';

import { SecurityService } from '../services/security.service';

@Component({
  moduleId: module.id,
  templateUrl: 'bbs.component.html'
})
export class BBSComponent { 
	private _auth : any;

	constructor(private _securityService : SecurityService ) {
		let bbs = this;
		this._securityService.getCurrentAuth().subscribe(function(auth) {
			bbs._auth = auth;
		}, function(error: any){
			
		})
	}
}
