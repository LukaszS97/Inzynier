import { Task } from './task';

export class Raport {
  id?;
  comment?;
  task?: Task = new Task();
}
