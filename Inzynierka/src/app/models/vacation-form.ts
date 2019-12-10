import { Employee } from './employee';

export class VacationForm {
  id?;
  startDate?;
  endDate?;
  isAccept?: boolean;
  employee: Employee;
}
