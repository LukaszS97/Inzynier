import { Employee } from './employee';

export class VacationForm {
  id?;
  startDate?;
  endDate?;
  isAccept?: boolean;
  timeSendingForm?;
  isDone?;
  isAccepted?: boolean;
  employee: Employee = new Employee();
}
