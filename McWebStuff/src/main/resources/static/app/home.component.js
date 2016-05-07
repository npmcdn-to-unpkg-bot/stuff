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
var dialog_service_1 = require('./shared/dialog.service');
var magic_component_1 = require('./magic/magic.component');
var HomeComponent = (function () {
    function HomeComponent(router, dialog) {
        this.router = router;
        this.dialog = dialog;
    }
    HomeComponent.prototype.routerOnActivate = function (curr) {
    };
    HomeComponent = __decorate([
        core_1.Component({
            templateUrl: 'app/home.html',
            providers: [dialog_service_1.DialogService],
            directives: [router_1.ROUTER_DIRECTIVES],
        }),
        router_1.Routes([
            { path: '/magic', component: magic_component_1.MagicComponent },
        ]), 
        __metadata('design:paramtypes', [router_1.Router, dialog_service_1.DialogService])
    ], HomeComponent);
    return HomeComponent;
}());
exports.HomeComponent = HomeComponent;
//# sourceMappingURL=home.component.js.map