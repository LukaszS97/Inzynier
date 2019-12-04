import { User } from './User';
import { WorkSchedule } from './work-schedule';

export class Employee {
  id?;
  address?;
  bankAccountNumber?;
  firstName?;
  lastName?;
  phoneNumber?;
  user?: User;
  workSchedule?: WorkSchedule;
}
