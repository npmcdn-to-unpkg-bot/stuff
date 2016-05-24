import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ACCORDION_DIRECTIVES } from 'ng2-bootstrap/ng2-bootstrap';

import { AppService } from '../services/app.service';
import { BBSService } from '../services/bbs.service';
import { TinyMCE } from '../shared/tinyMCE';

import { WebUserDTO } from "../services/model/webuser_dto";
import { MessageBoardDTO } from './model/messageboard_dto';

declare var tinymce: any;

@Component({
  moduleId: module.id,
  templateUrl: 'bbs.html',
  providers: [ BBSService ],
  directives: [ TinyMCE, ACCORDION_DIRECTIVES ]
})
export class BBS { 
	public currentUser : WebUserDTO;
    public messageBoards : MessageBoardDTO[];
	public htmlContent : string;
    //<tinyMCE [mceContent]="htmlContent" (contentChanged)="contentChanged($event)"></tinyMCE>
	
	constructor(private _appService : AppService,
			private _bbsService : BBSService )
	{
	}
	
	ngOnInit() {		
		let bbs = this;
		this._appService.getCurrentUser().subscribe(
				user => bbs.currentUser = user,
				err => console.error(err),				
				() => console.log('done')
		);
		this._bbsService.getMessageBoards().subscribe(
				boards => bbs.messageBoards = boards,
				err => console.error(err),				
				() => console.log('done')
		);
	}
	
    contentChanged(newContent) {
        this.htmlContent = newContent;
    }
	
}
