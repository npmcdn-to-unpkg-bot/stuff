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
var Observable_1 = require('rxjs/Observable');
var SecurityService = (function () {
    function SecurityService(_http) {
        this._http = _http;
        this.getCurrentAuth();
    }
    SecurityService.prototype.getCurrentAuth = function () {
        if (!this._currentAuth) {
            var service = this;
            return this._http.get('/rest/security/currentAuth')
                .map(function (res) {
                service._currentAuth = res.json();
                return service._currentAuth || {};
            })
                .catch(function (error, caught) {
                var errMsg = error.message || 'Server error';
                console.error(errMsg); // log to console instead
                return Observable_1.Observable.throw(errMsg);
            });
        }
        else {
            return new Observable_1.Observable(this._currentAuth);
        }
    };
    SecurityService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], SecurityService);
    return SecurityService;
}());
exports.SecurityService = SecurityService;
//# sourceMappingURL=security.service.js.map