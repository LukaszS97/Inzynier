package app.service;

import app.model.Task;
import app.model.TaskReport;
import app.model.User;
import app.repository.TaskReportRepository;
import app.repository.TaskRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TaskReportService {

    private TaskReportRepository taskReportRepository;
    private TaskRepository taskRepository;
    private UserRepository userRepository;


    @Autowired
    public void setTaskReportRepository(TaskReportRepository taskReportRepository) {
        this.taskReportRepository = taskReportRepository;
    }

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void saveTaskReport(TaskReport taskReport) {
        Task task = taskRepository.findByNameTask(taskReport.getTask().getNameTask());
        task.setDone(true);
        taskReport.setTask(task);
        taskReport.setFinishDate(new Date());
        taskReportRepository.save(taskReport);
    }

    public List<TaskReport> findTaskReports() {
        return taskReportRepository.findAll();
    }


    public List<TaskReport> findUserTaskReports() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User user = userRepository.findByEmail(name);


        List<TaskReport> taskReportList = taskReportRepository.findAll();
        System.out.println(taskReportList);

        taskReportList = taskReportList.stream()
                .filter(x -> x.getTask().getUser().equals(user))
                .collect(Collectors.toList());

        System.out.println(taskReportList);
        return taskReportList;
    }

}
