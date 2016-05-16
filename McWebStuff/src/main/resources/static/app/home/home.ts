import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SecurityService } from '../services/security.service';

@Component({
  moduleId: module.id,
  templateUrl: 'home.html'
})
export class Home { 
	private _auth : any;

	constructor(private _securityService : SecurityService ) {
	}
	
	ngOnInit() {
		let home = this;
		this._securityService.getCurrentAuth().subscribe(
			auth => home._auth = auth,
			err => console.log(err),				
			() => console.log('done')
		);		
	}
}
