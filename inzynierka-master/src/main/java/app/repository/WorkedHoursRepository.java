package app.repository;

import app.model.User;
import app.model.WorkedHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkedHoursRepository extends JpaRepository<WorkedHours, Long> {
    List<WorkedHours> findAllByUser(User user);

}
