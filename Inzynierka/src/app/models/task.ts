import { User } from './User';

export class Task {
  idTask?;
  startTask?;
  endTask?;
  taskName?;
  taskDescription?;
  isDone?;
  user?: User;
}
