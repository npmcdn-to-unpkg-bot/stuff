import { Component }     from '@angular/core';
import { ROUTER_DIRECTIVES, OnActivate, Router, RouteSegment } from '@angular/router';
import { MagicComponent } from './magic/magic.component';

@Component({
  templateUrl: 'app/home.html',
  providers:  [],
  directives: [ROUTER_DIRECTIVES],
})

export class HomeComponent implements OnActivate {
	constructor(
		    private router: Router) {}
	
	routerOnActivate(curr: RouteSegment): void {
	   
	}
}