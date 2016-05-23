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
var http_1 = require('@angular/http');
var Rx_1 = require("rxjs/Rx");
require("rxjs/Rx");
var WebUserDTO = (function () {
    function WebUserDTO() {
    }
    return WebUserDTO;
}());
exports.WebUserDTO = WebUserDTO;
var AppService = (function () {
    function AppService(_http) {
        this._http = _http;
    }
    AppService.prototype.ngOnInit = function () {
        this.getCurrentUser();
    };
    AppService.prototype.getCurrentUser = function () {
        var service = this;
        if (this._currentUser) {
            return new Rx_1.Observable(function (observer) {
                observer.next(service._currentUser);
                observer.complete();
                return;
            });
        }
        else {
            return this._http.get('/rest/app/currentUser')
                .map(function (res) {
                service._currentUser = res.json();
                return service._currentUser;
            });
        }
    };
    AppService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], AppService);
    return AppService;
}());
exports.AppService = AppService;
//# sourceMappingURL=app.service.js.map