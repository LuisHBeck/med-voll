package med.voll.api.service;

import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.domain.models.Appointment;
import med.voll.api.domain.models.Doctor;
import med.voll.api.domain.repositories.AppointmentRepository;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.domain.repositories.PatientRepository;
import med.voll.api.infra.exception.IdValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentScheduleService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public void schedule(AppointmentScheduleData data) {
        if (data.doctorId() != null && !doctorRepository
                .existsById(data.doctorId())) throw new IdValidationException("Invalid doctor id");
        if (!patientRepository.existsById(data.patientId())) throw new IdValidationException("Invalid patient id");

        var doctor = chooseDoctor(data);
        var patient = patientRepository.getReferenceById(data.patientId());

        var appointment = new Appointment(null, doctor, patient, data.date());
        appointmentRepository.save(appointment);
    }

    private Doctor chooseDoctor(AppointmentScheduleData data) {
        if(data.doctorId() != null) return doctorRepository.getReferenceById(data.doctorId());

        if (data.specialty() == null) throw new IdValidationException("Specialty field is required!");

        return doctorRepository.chooseRandomDoctorAvailable(data.specialty(), data.date());
    }
}
