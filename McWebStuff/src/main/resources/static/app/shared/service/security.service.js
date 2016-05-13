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
var SecurityService = (function () {
    function SecurityService(_http) {
        this._http = _http;
        this._securityUrl = 'rest/security';
        this._auth = null;
    }
    SecurityService.prototype.getAuth = function () {
        return this._auth;
    };
    SecurityService.prototype.getCurrentAuthFromServer = function () {
        if (this._auth) {
            return new Promise(function (resolve, reject) {
                resolve(null);
            });
        }
        else {
            var service = this;
            return this._http.get(this._securityUrl + '/currentAuth')
                .forEach(function (res) {
                service._auth = res.json();
            });
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