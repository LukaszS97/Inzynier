import { Employee } from './employee';

export class VacationForm {
  id?;
  startDate?;
  endDate?;
  isAccept?: boolean;
  timeSendingForm?;
  isDone?;
  isAccepted?;
  employee: Employee = new Employee();
}
