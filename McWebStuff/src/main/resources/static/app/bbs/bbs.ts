import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SecurityService } from '../services/security.service';
import { BBSService } from '../services/bbs.service';
import { MessageBoard } from './model/messageboard';
import { TinyMCE } from '../shared/tinyMCE';

declare var tinymce: any;

@Component({
  moduleId: module.id,
  templateUrl: 'bbs.html',
  providers: [ BBSService ],
  directives: [ TinyMCE ]
})
export class BBS { 
	public currentAuth : any;
    public messageBoards : MessageBoard[];
	public htmlContent : string;
	
	constructor(private _securityService : SecurityService,
			private _bbsService : BBSService )
	{
	}
	
	ngOnInit() {		
		let bbs = this;
		this._securityService.getCurrentAuth().subscribe(
				auth => bbs.currentAuth = auth,
				err => console.log(err),				
				() => console.log('done')
		);
		this._bbsService.getMessageBoards().subscribe(
				boards => bbs.messageBoards = <MessageBoard[]> boards,
				err => console.log(err),				
				() => console.log('done')
		);
	}
	
    contentChanged(newContent) {
        this.htmlContent = newContent;
    }
	
}
