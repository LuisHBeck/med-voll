package med.voll.api.domain.repositories;

import med.voll.api.domain.models.Doctor;
import med.voll.api.domain.models.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByIsActiveTrue(Pageable pagination);

    @Query("""
            select d from Doctor d
            where 
            d.isActive = true
            and
            d.specialty = :specialty
            and d.id not in(
                select a.doctor.id from Appointment a
                where
                a.date = :date 
            )
            order by rand()
            limit 1 
            """)
    Doctor chooseRandomDoctorAvailable(Specialty specialty, LocalDateTime date);
}
