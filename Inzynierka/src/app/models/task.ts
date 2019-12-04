import { User } from './User';

export class Task {
  id?;
  startTask?;
  endTask?;
  nameTask?;
  descriptionTask?;
  isDone?: boolean;
  user?: User = new User();
}
