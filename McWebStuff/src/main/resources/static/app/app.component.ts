import { Component, OnInit } from '@angular/core';
import { Routes, Router, RouteSegment, ROUTER_DIRECTIVES } from '@angular/router';
import { Location } from '@angular/common';
import { DialogService }  from './shared/dialog.service';
import { SecurityService } from './shared/security.service';
import { HomeComponent } from './home.component';
import { BBSComponent } from './bbs/bbs.component';
import { MagicComponent } from './magic/magic.component';


@Component({
  selector: 'McWebStuff',
  templateUrl: 'app/app.html',
  providers:  [DialogService, SecurityService],
  directives: [ROUTER_DIRECTIVES]
})

@Routes([
  {path: '/home', component: HomeComponent},
  {path: '/bbs', component: BBSComponent},
  {path: '/magic', component: MagicComponent},
  
])

export class AppComponent implements OnInit {
  private _auth : any;
  constructor(
		 private _security: SecurityService,
		 private _location: Location,
		 private _router: Router,
		 private _dialog: DialogService) {
  }

  ngOnInit() {
	  let vm = this;
	  this._security.getCurrentAuth().then(function(){
		  vm._auth = vm._security.getAuth();
		  vm._router.navigate(['/home']);
	  });
  }
  
}

