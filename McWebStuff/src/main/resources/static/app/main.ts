import { bootstrap } from '@angular/platform-browser-dynamic';
import { ROUTER_PROVIDERS } from '@angular/router';
import { HTTP_PROVIDERS } from '@angular/http';

import { SecurityService } from './services/security.service';

import { AppComponent } from './app.component';

bootstrap(AppComponent, [ROUTER_PROVIDERS, HTTP_PROVIDERS, SecurityService ]);