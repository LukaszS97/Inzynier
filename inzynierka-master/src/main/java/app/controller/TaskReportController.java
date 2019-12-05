package app.controller;

import app.model.TaskReport;
import app.service.TaskReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/api/taskReport")
public class TaskReportController {

    private TaskReportService taskReportService;

    @Autowired
    public void setTaskReportService(TaskReportService taskReportService) {
        this.taskReportService = taskReportService;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postTaskReport(@RequestBody TaskReport taskReport){
        taskReportService.saveTaskReport(taskReport);
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskReport> getTaskReports(){
        return taskReportService.findTaskReports();
    }


}
