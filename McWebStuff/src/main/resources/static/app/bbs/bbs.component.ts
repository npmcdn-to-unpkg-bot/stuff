import { Component }     from '@angular/core';
import { ROUTER_DIRECTIVES, Router, RouteSegment, OnActivate, CanDeactivate } from '@angular/router';
import { DialogService }  from '../shared/dialog.service';

@Component({
	  templateUrl: 'app/bbs/bbs.html',
	  providers:  [DialogService],
	  directives: [ROUTER_DIRECTIVES],
})

export class BBSComponent implements OnActivate, CanDeactivate {
	currSegment = null;
	
	constructor(private router: Router, private dialog: DialogService) {
	}
	
	routerOnActivate(curr: RouteSegment) {
	    this.currSegment = curr;
	}
	
	routerCanDeactivate(): any {
		return true;
	}
}