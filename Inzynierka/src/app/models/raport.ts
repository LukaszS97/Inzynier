import { Task } from './task';

export class Raport {
  id?;
  comment?;
  finishDate?;
  task?: Task = new Task();
}
