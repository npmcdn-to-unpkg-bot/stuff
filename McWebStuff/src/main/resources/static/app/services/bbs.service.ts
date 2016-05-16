import { Injectable, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from "rxjs/Rx";
import "rxjs/Rx";
import { MessageBoard } from '../bbs/model/messageboard';


@Injectable()
export class BBSService implements OnInit {
	messageBoards : MessageBoard[];
	
	constructor(private _http: Http) {
	}
	
	ngOnInit() {
		this.getMessageBoards();
	}
	
	public getMessageBoards() {
		var service = this;
		if(this.messageBoards) {
			return new Observable(function(observer){
				observer.next(service.messageBoards);
				observer.complete();
				return;
			});
		} else {
			return this._http.get('/rest/bbs/messageBoard')
			.map(function(res: Response) {	
				service.messageBoards = res.json();
				return service.messageBoards;
			});	
		}
	}
}