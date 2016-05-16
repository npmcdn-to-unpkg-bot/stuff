import { Component } from '@angular/core';
import { ROUTER_DIRECTIVES, Routes } from '@angular/router';

import { SecurityService } from './services/security.service';

import { HomeComponent } from './home/home.component';
import { BBSComponent } from './bbs/bbs.component';


@Component({
  selector: 'McWebStuff',
  moduleId: module.id,
  templateUrl: 'app.component.html',
  directives: [ ROUTER_DIRECTIVES ],
})
@Routes([
    { path: '/home', component: HomeComponent },
    { path: '/bbs', component: BBSComponent }
])
export class AppComponent {
	
	constructor(private _securityService : SecurityService) {
	}
	
}
