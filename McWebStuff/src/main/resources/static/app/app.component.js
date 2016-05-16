"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var router_1 = require('@angular/router');
var security_service_1 = require('./services/security.service');
var home_component_1 = require('./home/home.component');
var bbs_component_1 = require('./bbs/bbs.component');
var AppComponent = (function () {
    function AppComponent(_securityService) {
        this._securityService = _securityService;
    }
    AppComponent = __decorate([
        core_1.Component({
            selector: 'McWebStuff',
            moduleId: module.id,
            templateUrl: 'app.component.html',
            directives: [router_1.ROUTER_DIRECTIVES],
        }),
        router_1.Routes([
            { path: '/home', component: home_component_1.HomeComponent },
            { path: '/bbs', component: bbs_component_1.BBSComponent }
        ]), 
        __metadata('design:paramtypes', [security_service_1.SecurityService])
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map