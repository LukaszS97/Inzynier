package app.repository;

import app.model.TaskReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskReportRepository extends JpaRepository<TaskReport,Long> {
}
