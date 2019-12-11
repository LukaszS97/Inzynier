package app.service;

import app.model.*;
import app.repository.EmployeeRepository;
import app.repository.UserRepository;
import app.repository.VacationFormRepository;
import app.repository.WorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class VacationFormService {

    private VacationFormRepository vacationFormRepository;
    private WorkScheduleService workScheduleService;
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private WorkScheduleRepository workScheduleRepository;
    private MailService mailService;

    @Autowired
    public void setVacationFormRepository(VacationFormRepository vacationFormRepository) {
        this.vacationFormRepository = vacationFormRepository;
    }

    @Autowired
    public void setWorkScheduleService(WorkScheduleService workScheduleService) {
        this.workScheduleService = workScheduleService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setWorkScheduleRepository(WorkScheduleRepository workScheduleRepository) {
        this.workScheduleRepository = workScheduleRepository;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public void saveVacationForm(VacationForm vacationForm) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User user = userRepository.findByEmail(name);
        Employee employee = employeeRepository.findById(user.getId_user()).orElseThrow(
                () -> new NoSuchElementException("Not found"));

        vacationForm.setEmployee(employee);
        vacationForm.setTimeSendingForm(LocalDate.now());
        vacationFormRepository.save(vacationForm);
    }


    public List<VacationForm> getVacationForms() {
        List<VacationForm> list = vacationFormRepository.findAll();

        list = list.stream()
                .filter(x -> x.getIsDone() == false)
                .collect(Collectors.toList());
        return list;
    }


    public void refreshVacationForms(VacationForm vacationForm) throws MessagingException {

        //System.out.println("isaccepted " + vacationForm.getIsAccepted() );
        //System.out.println("is " + vacationForm.getIsDone() );

        long idEmployee = vacationForm.getEmployee().getId();
        //System.out.println("edEmployaa " + idEmployee);

        VacationForm vacationFormAllData = vacationFormRepository.findById(vacationForm.getId()).orElseThrow(
                () -> new NoSuchElementException("Not Found"));
        //System.out.println("costamaammmm " +vacationFormAllData);

        //System.out.println("Vacationnnn "+vacationFormAllData);
        //System.out.println("Vacationnnnnnnnnnnn111111 "+vacationFormAllData.getIsAccepted());
        //if(vacationForm.isAccepted() == 1)
        vacationFormAllData.setIsAccepted(vacationForm.getIsAccepted());
        //System.out.println("Vacationnnnnnnnnnnn2222 "+vacationFormAllData.getIsAccepted());


        Employee employee = employeeRepository.findById(idEmployee).orElseThrow(
                () -> new NoSuchElementException("Not found"));
        //System.out.println("emplojjjjjjjjj " + employee);

        List<WorkSchedule> list = employee.getWorkSchedule();
        //System.out.println("lista scheduli " + list);



        vacationFormAllData.setIsDone(true);
        vacationFormRepository.save(vacationFormAllData);



//wysylanie maiala z odp
        if(vacationFormAllData.getIsAccepted() == false){
            Mail mail = new Mail();
            mail.setText("Niestety wniosek o urlop zostal odrzucony");
            mail.setSubject("Wniosek urlopowy");
            mail.setTo(employee.getUser().getEmail());
            mailService.sendMail(mail,true);
        }else{
            Mail mail = new Mail();
            mail.setText("Wniosek urlopowy zostal przyjety");
            mail.setSubject("Wniosek urlopowy");
            mail.setTo(employee.getUser().getEmail());
            mailService.sendMail(mail,true);
        }

        /*
        //tylko daty w przedziale urlopu
        if (vacationFormAllData.getIsAccepted() == true) {
            list = list.stream()
                    .filter(x -> (!(x.getLocalDate().isAfter(vacationForm.getEndDate())) &&
                            !(x.getLocalDate().isBefore(vacationForm.getStartDate()))))
                    .collect(Collectors.toList());
            System.out.println("66666666666666666666666" + list);

            for (WorkSchedule work : list) {
                workScheduleRepository.delete(work);
            }
            System.out.println("lista scheduli22222222222 " + list);
            System.out.println("777777777777777777777777");

            //workScheduleRepository.deleteAll();
            //employee.setWorkSchedule(list);
            //employeeRepository.save(employee);
            vacationForm.setIsDone(true);
            System.out.println("88888888888888888888888");
            vacationFormRepository.save(vacationForm);
            System.out.println("9999999999999999999999999999");
        } else {
            vacationForm.setIsDone(true);
            vacationFormRepository.save(vacationForm);
        }


         */

    }
}

/*
Employee employee = employeeRepository.findById(idEmployee).orElseThrow(
                () -> new NoSuchElementException("Not found"));

        List<WorkSchedule> list = employee.getWorkSchedule();


//tylko daty w przedziale urlopu
        if (vacationFormAllData.isAccepted() == true) {
            list = list.stream()
                    .filter(x -> (!(x.getLocalDate().isAfter(vacationForm.getEndDate())) &&
                            !(x.getLocalDate().isBefore(vacationForm.getStartDate()))))
                    .collect(Collectors.toList());
            System.out.println("66666666666666666666666" + list );

            for (WorkSchedule work : list){
                workScheduleRepository.delete(work);
            }
            System.out.println("777777777777777777777777");

            //workScheduleRepository.deleteAll();
            //employee.setWorkSchedule(list);
            //employeeRepository.save(employee);
            vacationForm.setDone(true);
            System.out.println("88888888888888888888888");
            vacationFormRepository.save(vacationForm);
            System.out.println("9999999999999999999999999999");
        } else {
            vacationForm.setDone(true);
        }


 */











        /*
        List<WorkSchedule> list = employee.getWorkSchedule();

        //tylko daty przed rozpoczeciem i po zakonczeniu urlopu
        list = list.stream()
                .filter(x -> ((x.getLocalDate().isAfter(vacationForm.getEndDate())) &&
                        (x.getLocalDate().isBefore(vacationForm.getStartDate()))))
                .collect(Collectors.toList());


        employee.setWorkSchedule(list);
        employeeRepository.save(employee);
*/
