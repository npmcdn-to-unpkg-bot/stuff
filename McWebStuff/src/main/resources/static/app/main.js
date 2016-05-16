"use strict";
var platform_browser_dynamic_1 = require('@angular/platform-browser-dynamic');
var router_1 = require('@angular/router');
var http_1 = require('@angular/http');
var security_service_1 = require('./services/security.service');
var app_component_1 = require('./app.component');
platform_browser_dynamic_1.bootstrap(app_component_1.AppComponent, [router_1.ROUTER_PROVIDERS, http_1.HTTP_PROVIDERS, security_service_1.SecurityService]);
//# sourceMappingURL=main.js.map