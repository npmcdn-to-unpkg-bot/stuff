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
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
var core_1 = require('@angular/core');
var TinyMCE = (function () {
    function TinyMCE(elementRef) {
        this.elementRef = elementRef;
        var randLetter = String.fromCharCode(65 + Math.floor(Math.random() * 26));
        var uniqid = randLetter + Date.now();
        this.elementID = 'tinymce' + uniqid;
        this.contentChanged = new core_1.EventEmitter();
    }
    TinyMCE.prototype.ngAfterViewInit = function () {
        //Clone base textarea
        var baseTextArea = this.elementRef.nativeElement.querySelector("#baseTextArea");
        var clonedTextArea = baseTextArea.cloneNode(true);
        clonedTextArea.id = this.elementID;
        var formGroup = this.elementRef.nativeElement.querySelector("#tinyFormGroup");
        formGroup.appendChild(clonedTextArea);
        //Attach tinyMCE to cloned textarea
        var plugins1 = 'advlist autolink lists link image charmap print preview anchor';
        var plugins2 = 'searchreplace visualblocks code fullscreen';
        var plugins3 = 'insertdatetime media table contextmenu paste code';
        var toolbars1 = 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify';
        var toolbars2 = ' | bullist numlist outdent indent | link image';
        tinymce.init({
            mode: 'exact',
            height: 500,
            theme: 'modern',
            plugins: [
                plugins1,
                plugins2,
                plugins3
            ],
            toolbar: [
                toolbars1,
                toolbars2
            ],
            elements: this.elementID,
            setup: this.tinyMCESetup.bind(this),
            schema: 'html5',
            entity_encoding: 'raw',
            resize: false,
            auto_focus: true,
            browser_spellcheck: true
        });
    };
    TinyMCE.prototype.ngOnDestroy = function () {
        //destroy cloned elements
        tinymce.get(this.elementID).remove();
        var elem = document.getElementById(this.elementID);
        if (elem) {
            elem.parentElement.removeChild(elem);
        }
    };
    TinyMCE.prototype.tinyMCESetup = function (ed) {
        ed.on('keyup', this.tinyMCEOnKeyup.bind(this));
    };
    TinyMCE.prototype.tinyMCEOnKeyup = function (e) {
        this.contentChanged.emit(tinymce.get(this.elementID).getContent());
    };
    Object.defineProperty(TinyMCE.prototype, "mceContent", {
        set: function (content) {
            this.htmlContent = content;
        },
        enumerable: true,
        configurable: true
    });
    __decorate([
        core_1.Output(), 
        __metadata('design:type', core_1.EventEmitter)
    ], TinyMCE.prototype, "contentChanged", void 0);
    TinyMCE = __decorate([
        core_1.Component({
            selector: 'tinyMCE',
            moduleId: module.id,
            templateUrl: 'tinyMCE.html',
            inputs: ['mceContent']
        }),
        __param(0, core_1.Inject(core_1.ElementRef)), 
        __metadata('design:paramtypes', [core_1.ElementRef])
    ], TinyMCE);
    return TinyMCE;
}());
exports.TinyMCE = TinyMCE;
//# sourceMappingURL=tinyMCE.js.map