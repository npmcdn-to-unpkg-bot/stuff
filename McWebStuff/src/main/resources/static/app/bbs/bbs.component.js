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
var dialog_service_1 = require('../shared/dialog.service');
var BBSComponent = (function () {
    function BBSComponent(router, dialog) {
        this.router = router;
        this.dialog = dialog;
        this.currSegment = null;
    }
    BBSComponent.prototype.routerOnActivate = function (curr) {
        this.currSegment = curr;
    };
    BBSComponent.prototype.routerCanDeactivate = function () {
        return true;
    };
    BBSComponent = __decorate([
        core_1.Component({
            templateUrl: 'app/bbs/bbs.html',
            providers: [dialog_service_1.DialogService],
            directives: [router_1.ROUTER_DIRECTIVES],
        }), 
        __metadata('design:paramtypes', [router_1.Router, dialog_service_1.DialogService])
    ], BBSComponent);
    return BBSComponent;
}());
exports.BBSComponent = BBSComponent;
//# sourceMappingURL=bbs.component.js.map