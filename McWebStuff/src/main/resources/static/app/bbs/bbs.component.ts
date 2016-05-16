import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SecurityService } from '../services/security.service';
import {Observable} from 'rxjs/Observable';
import "rxjs/Rx";

@Component({
  moduleId: module.id,
  templateUrl: 'bbs.component.html'
})
export class BBSComponent { 
	private _auth : any;

	constructor(private _securityService : SecurityService ) {
	}
	
	ngOnInit() {
		let bbs = this;
		this._securityService.getCurrentAuth().subscribe(
				auth => bbs._auth = auth,
				err => console.log(err),				
				() => console.log('done')
		);		
	}
}
