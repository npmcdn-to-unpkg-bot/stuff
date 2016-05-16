import { Component, OnInit } from '@angular/core';
import { ROUTER_DIRECTIVES, Routes, Router } from '@angular/router';

import { SecurityService } from './services/security.service';

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
	currentAuth : any;
	currentUser : any;
	
	constructor(private _router: Router, private _securityService : SecurityService) {
	}
	
	ngOnInit() {
		this._securityService.getCurrentAuth().subscribe(auth => {
			this.currentAuth = auth;
			this.currentUser = auth.principal.account.display;
		});
		this._router.navigate(['/app/home']);
	}
		
}
