import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AppService } from '../services/app.service';

import { WebUserDTO } from "../services/model/webuser_dto";

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
			user => home._user = user,
			err => console.log(err),				
			() => console.log('done')
		);		
	}
}
