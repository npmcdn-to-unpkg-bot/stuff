import { bootstrap } from '@angular/platform-browser-dynamic';
import { ROUTER_PROVIDERS } from '@angular/router';
import { HTTP_PROVIDERS } from '@angular/http';

import { AppService } from './services/app.service';

import { App } from './app';

bootstrap(App, [ROUTER_PROVIDERS, HTTP_PROVIDERS, AppService ]);