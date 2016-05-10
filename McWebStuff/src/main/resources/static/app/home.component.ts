import { Component }     from '@angular/core';
import { ROUTER_DIRECTIVES, OnActivate, Router, RouteSegment } from '@angular/router';
import { DialogService }  from './shared/dialog.service';
import { MagicComponent } from './magic/magic.component';

@Component({
  templateUrl: 'app/home.html',
  providers:  [DialogService],
  directives: [ROUTER_DIRECTIVES],
})

export class HomeComponent implements OnActivate {
	constructor(
		    private router: Router,
			 private dialog: DialogService) {}
	
	routerOnActivate(curr: RouteSegment): void {
	   
	}
}