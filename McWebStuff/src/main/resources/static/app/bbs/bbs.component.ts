import { Component }     from '@angular/core';
import { ROUTER_DIRECTIVES, Router, RouteSegment, OnActivate, CanDeactivate } from '@angular/router';
import { DialogService }  from '../shared/service/dialog.service';
import { SecurityService }  from '../shared/service/security.service';

@Component({
	  templateUrl: 'app/bbs/bbs.html',
	  providers:  [DialogService, SecurityService],
	  directives: [ROUTER_DIRECTIVES],
})

export class BBSComponent implements OnActivate, CanDeactivate {
	private _currSegment = null;
	private _auth = null;
	
	constructor(
			private _router: Router, private _dialog: DialogService,
			private _security: SecurityService) {
		this._auth = this._security.getAuth();
	}
	
	routerOnActivate(curr: RouteSegment) {
	    this._currSegment = curr;
	}
	
	routerCanDeactivate(): any {
		return true;
	}
}