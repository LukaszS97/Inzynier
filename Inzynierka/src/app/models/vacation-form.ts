import { Employee } from './employee';

export class VacationForm {
  id?;
  startDate?;
  endDate?;
  isAccept?: boolean;
  timeSendingForm?;
  isDone?;
  isAccpeted?;
  employee: Employee = new Employee();
}
