"use strict";
var platform_browser_dynamic_1 = require('@angular/platform-browser-dynamic');
var router_1 = require('@angular/router');
var http_1 = require('@angular/http');
var app_service_1 = require('./services/app.service');
var app_1 = require('./app');
platform_browser_dynamic_1.bootstrap(app_1.App, [router_1.ROUTER_PROVIDERS, http_1.HTTP_PROVIDERS, app_service_1.AppService]);
//# sourceMappingURL=main.js.map