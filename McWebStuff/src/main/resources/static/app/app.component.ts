import { Component, OnInit } from '@angular/core';
import { Routes, Router, ROUTER_DIRECTIVES } from '@angular/router';
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
		 private router: Router,
		 private dialog: DialogService) {
	  
  }

  ngOnInit() {
    this.router.navigate(['/home']);
  }
}

