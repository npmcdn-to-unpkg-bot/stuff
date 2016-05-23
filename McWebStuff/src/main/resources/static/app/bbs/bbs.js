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
var app_service_1 = require('../services/app.service');
var bbs_service_1 = require('../services/bbs.service');
var tinyMCE_1 = require('../shared/tinyMCE');
var ng2_bootstrap_1 = require('ng2-bootstrap/ng2-bootstrap');
var BBS = (function () {
    //<tinyMCE [mceContent]="htmlContent" (contentChanged)="contentChanged($event)"></tinyMCE>
    function BBS(_appService, _bbsService) {
        this._appService = _appService;
        this._bbsService = _bbsService;
    }
    BBS.prototype.ngOnInit = function () {
        var bbs = this;
        this._appService.getCurrentUser().subscribe(function (user) { return bbs.currentUser = user; }, function (err) { return console.log(err); }, function () { return console.log('done'); });
        this._bbsService.getMessageBoards().subscribe(function (boards) { return bbs.messageBoards = boards; }, function (err) { return console.log(err); }, function () { return console.log('done'); });
    };
    BBS.prototype.contentChanged = function (newContent) {
        this.htmlContent = newContent;
    };
    BBS = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: 'bbs.html',
            providers: [bbs_service_1.BBSService],
            directives: [tinyMCE_1.TinyMCE, ng2_bootstrap_1.ACCORDION_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [app_service_1.AppService, bbs_service_1.BBSService])
    ], BBS);
    return BBS;
}());
exports.BBS = BBS;
//# sourceMappingURL=bbs.js.map