package app.repository;

import app.model.VacationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationFormRepository extends JpaRepository<VacationForm,Long> {
}
