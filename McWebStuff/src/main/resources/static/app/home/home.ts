import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService, WebUserDTO } from '../services/app.service';

@Component({
	moduleId: module.id,
	templateUrl: 'home.html',
})
export class Home { 
	private _user : WebUserDTO;

	constructor(private _appService : AppService ) {
	}
	
	ngOnInit() {
		let home = this;
		this._appService.getCurrentUser().subscribe(
			user => home._user = <WebUserDTO> user,
			err => console.log(err),				
			() => console.log('done')
		);		
	}
}
