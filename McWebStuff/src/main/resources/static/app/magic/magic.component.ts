import { Component }     from '@angular/core';
import { ROUTER_DIRECTIVES, Router, RouteSegment, OnActivate, CanDeactivate } from '@angular/router';
import { DialogService }  from '../shared/dialog.service';

declare var McWebJS: any;

@Component({
  templateUrl: 'app/magic/magic.html',
  providers:  [DialogService],
  directives: [ROUTER_DIRECTIVES],
})

export class MagicComponent implements OnActivate, CanDeactivate {
	currSegment = null;
	mcWebJS: any;
	
	constructor(private router: Router, private dialog: DialogService) {
		this.mcWebJS = new McWebJS();
	}
	
	routerOnActivate(curr: RouteSegment) {
	    this.currSegment = curr;
	    //this.mcWebJS.doSomething();
	}
	
	routerCanDeactivate(): any {
	    // Otherwise ask the user with the dialog service and return its
	    // promise which resolves to true or false when the user decides
	    //return this.dialog.confirm('Discard changes?');
		return true;
	}
}