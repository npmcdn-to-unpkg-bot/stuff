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
var security_service_1 = require('../services/security.service');
var bbs_service_1 = require('../services/bbs.service');
var BBS = (function () {
    function BBS(_securityService, _bbsService) {
        this._securityService = _securityService;
        this._bbsService = _bbsService;
    }
    BBS.prototype.ngOnInit = function () {
        var bbs = this;
        this._securityService.getCurrentAuth().subscribe(function (auth) { return bbs.currentAuth = auth; }, function (err) { return console.log(err); }, function () { return console.log('done'); });
        this._bbsService.getMessageBoards().subscribe(function (boards) { return bbs.messageBoards = boards; }, function (err) { return console.log(err); }, function () { return console.log('done'); });
    };
    BBS = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: 'bbs.html',
            providers: [bbs_service_1.BBSService]
        }), 
        __metadata('design:paramtypes', [security_service_1.SecurityService, bbs_service_1.BBSService])
    ], BBS);
    return BBS;
}());
exports.BBS = BBS;
//# sourceMappingURL=bbs.js.map