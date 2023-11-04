package med.voll.api.Repositories;

import med.voll.api.JPAs.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
