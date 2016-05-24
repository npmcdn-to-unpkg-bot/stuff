import { BaseDTO } from '../../services/model/base_dto';

export class MessageBoardDTO extends BaseDTO {
	id: number;
	name: string;
	description: string;
}
