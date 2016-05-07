import { Component }     from '@angular/core';
import { ROUTER_DIRECTIVES, Routes, Router, RouteSegment, OnActivate, CanDeactivate } from '@angular/router';
import { DialogService }  from '../shared/dialog.service';
import { HomeComponent } from '../home.component';

@Component({
  templateUrl: 'app/magic/magic.html',
  providers:  [DialogService],
  directives: [ROUTER_DIRECTIVES],
})

@Routes([
	{path: '/home', component: HomeComponent},
])

export class MagicComponent implements OnActivate, CanDeactivate {
	currSegment = null;
	constructor(
			 private router: Router,
			 private dialog: DialogService) {
	  }
	
	routerOnActivate(curr: RouteSegment) {
	    this.currSegment = curr;
	  }
	
	  routerCanDeactivate(): any {
	   
	    // Otherwise ask the user with the dialog service and return its
	    // promise which resolves to true or false when the user decides
	    return this.dialog.confirm('Discard changes?');
	  }
}