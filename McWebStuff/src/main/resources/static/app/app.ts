import { Component, OnInit } from '@angular/core';
import { ROUTER_DIRECTIVES, Routes, Router } from '@angular/router';
import { AppService, WebUserDTO } from './services/app.service';

import { Home } from './home/home';
import { BBS } from './bbs/bbs';


@Component({
  selector: 'McWebStuff',
  moduleId: module.id,
  templateUrl: 'app.html',
  directives: [ ROUTER_DIRECTIVES ],
})
@Routes([
    { path: '/app/home', component: Home },
    { path: '/app/bbs', component: BBS }
])
export class App {
	currentUser : WebUserDTO = null);
	
	constructor(private _router: Router, private _appService : AppService) {
	}
	
	ngOnInit() {
		var app = this;
		this._appService.getCurrentUser().subscribe(user => {
			app.currentUser = <WebUserDTO> user;
		});
		this._router.navigate(['/app/home']);
	}
		
}
