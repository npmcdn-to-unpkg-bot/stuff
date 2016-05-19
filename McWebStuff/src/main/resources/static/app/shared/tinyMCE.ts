import {bind, Component, ElementRef, EventEmitter, Output, Inject, ComponentRef} from '@angular/core';
import {Routes, ROUTER_DIRECTIVES} from '@angular/router';
import {Http} from '@angular/http';

declare var tinymce: any;

@Component({
    selector: 'tinyMCE',
    moduleId: module.id,
    templateUrl: 'tinyMCE.html',
    inputs: ['mceContent']
})

export class TinyMCE {

    private elementRef: ElementRef;
    private elementID: string;
    private htmlContent: string;

    @Output() contentChanged: EventEmitter<any>;

    constructor(@Inject(ElementRef) elementRef: ElementRef)
    {
        this.elementRef = elementRef;

        var randLetter = String.fromCharCode(65 + Math.floor(Math.random() * 26));
        var uniqid = randLetter + Date.now();

        this.elementID = 'tinymce' + uniqid;
        this.contentChanged = new EventEmitter();
    }

    ngAfterViewInit()
    {
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
        
        tinymce.init(
            {
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
            }
        );
    }

    ngOnDestroy() {
        //destroy cloned elements
        tinymce.get(this.elementID).remove();

        var elem = document.getElementById(this.elementID);
        if(elem) {
        	elem.parentElement.removeChild(elem)
        }
    }


    tinyMCESetup(ed) {
        ed.on('keyup', this.tinyMCEOnKeyup.bind(this));
    }

    tinyMCEOnKeyup(e) {
        this.contentChanged.emit(tinymce.get(this.elementID).getContent());
    }


    set mceContent(content) {
        this.htmlContent = content;
    }
}