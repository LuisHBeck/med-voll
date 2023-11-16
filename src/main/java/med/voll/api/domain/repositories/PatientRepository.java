package med.voll.api.domain.repositories;

import med.voll.api.domain.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByIsActiveTrue(Pageable pagination);

    @Query("""
            select p.isActive
            from Patient p
            where 
            p.id = :id
            """)
    Boolean findIsActiveById(Long id);
}
