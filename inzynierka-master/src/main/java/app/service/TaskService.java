package app.service;

import app.model.Task;
import app.model.User;
import app.repository.TaskRepository;
import app.repository.UserRepository;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void saveTask(Task task) {
        User user = userRepository.findByEmail(task.getUser().getEmail());
        task.setUser(user);
        taskRepository.save(task);
    }

    /*
    public Task getUserTask() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();


    }

     */
}
