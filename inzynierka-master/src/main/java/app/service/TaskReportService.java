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

@Service
public class TaskReportService {

    private TaskReportRepository taskReportRepository;
    private TaskRepository taskRepository;


    @Autowired
    public void setTaskReportRepository(TaskReportRepository taskReportRepository) {
        this.taskReportRepository = taskReportRepository;
    }

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public void saveTaskReport(TaskReport taskReport) {
        Task task = taskRepository.findById(taskReport.getTask().getId())
                .orElseThrow(() -> new NoSuchElementException("Not found"));
        taskReport.setTask(task);
        taskReport.setFinishDate(new Date());
        taskReportRepository.save(taskReport);
    }

    public List<TaskReport> findTaskReports() {
        return taskReportRepository.findAll();
    }
}