import { User } from './User';

export class WorkedHours {
  id?;
  year?;
  month?;
  hours?;
  user?: User = new User();
}
