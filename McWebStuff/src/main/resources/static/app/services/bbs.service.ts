import { Injectable, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from "rxjs/Rx";
import "rxjs/Rx";

import { MessageBoardDTO } from '../bbs/model/messageboard_dto';


@Injectable()
export class BBSService implements OnInit {
	messageBoards : MessageBoardDTO[];
	
	constructor(private _http: Http) {
	}
	
	ngOnInit() {
		this.getMessageBoards();
	}
	
	public getMessageBoards() : Observable<MessageBoardDTO[]> {
		var service = this;
		if(this.messageBoards) {
			return new Observable<MessageBoardDTO[]>(function(observer){
				observer.next(service.messageBoards);
				observer.complete();
				return;
			});
		} else {
			return this._http.get('/rest/bbs/messageBoard')
			.map(function(res: Response) {	
				return service.messageBoards = res.json();
			});	
		}
	}
}