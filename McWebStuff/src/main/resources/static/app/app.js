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
var home_1 = require('./home/home');
var bbs_1 = require('./bbs/bbs');
var App = (function () {
    function App(_router, _securityService) {
        this._router = _router;
        this._securityService = _securityService;
    }
    App.prototype.ngOnInit = function () {
        var _this = this;
        this._securityService.getCurrentAuth().subscribe(function (auth) {
            _this.currentAuth = auth;
            _this.currentUser = auth.principal.account.display;
        });
        this._router.navigate(['/app/home']);
    };
    App = __decorate([
        core_1.Component({
            selector: 'McWebStuff',
            moduleId: module.id,
            templateUrl: 'app.html',
            directives: [router_1.ROUTER_DIRECTIVES],
        }),
        router_1.Routes([
            { path: '/app/home', component: home_1.Home },
            { path: '/app/bbs', component: bbs_1.BBS }
        ]), 
        __metadata('design:paramtypes', [router_1.Router, security_service_1.SecurityService])
    ], App);
    return App;
}());
exports.App = App;
//# sourceMappingURL=app.js.map