package app.service;

import app.model.Task;
import app.repository.TaskRepository;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    /*
    public Task getUserTask() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();


    }

     */
}
