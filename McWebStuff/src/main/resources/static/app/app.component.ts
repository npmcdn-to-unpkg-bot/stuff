import { Component, OnInit } from '@angular/core';
import { Routes, Router, RouteSegment, ROUTER_DIRECTIVES } from '@angular/router';
import { Location } from '@angular/common';
import { DialogService }  from './shared/dialog.service';
import { HomeComponent } from './home.component';
import { MagicComponent } from './magic/magic.component';


@Component({
  selector: 'McWebStuff',
  templateUrl: 'app/app.html',
  providers:  [DialogService],
  directives: [ROUTER_DIRECTIVES]
})

@Routes([
  {path: '/home', component: HomeComponent},
  {path: '/magic', component: MagicComponent},
])

export class AppComponent implements OnInit {
  
  constructor(
		 private _location: Location,
		 private _router: Router,
		 private _dialog: DialogService) {
  }

  ngOnInit() {
	  this._router.navigate(['/home']);
  }
  
}
