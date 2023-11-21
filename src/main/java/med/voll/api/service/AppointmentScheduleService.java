package med.voll.api.service;

import med.voll.api.domain.dtos.appointment.AppointmentDetailingData;
import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.domain.models.Appointment;
import med.voll.api.domain.models.Doctor;
import med.voll.api.domain.repositories.AppointmentRepository;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.domain.repositories.PatientRepository;
import med.voll.api.infra.exception.ValidException;
import med.voll.api.service.validation.schedule.AppointmentSchedulingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentScheduleService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentSchedulingValidator> validators;


    public AppointmentDetailingData schedule(AppointmentScheduleData data) {
        if (data.doctorId() != null && !doctorRepository
                .existsById(data.doctorId())) throw new ValidException("Invalid doctor id");
        if (!patientRepository.existsById(data.patientId())) throw new ValidException("Invalid patient id");

        // EXECUTING ALL VALIDATORS THAT IMPLEMENTS APPOINTMENT SCHEDULING VALIDATORS INTERFACE
        validators.forEach(v -> v.validate(data));

        var doctor = chooseDoctor(data);
        if(doctor == null) throw new ValidException("No doctor available on this date");
        var patient = patientRepository.getReferenceById(data.patientId());

        var appointment = new Appointment(doctor, patient, data.date());
        appointmentRepository.save(appointment);

        return new AppointmentDetailingData(appointment);
    }

    private Doctor chooseDoctor(AppointmentScheduleData data) {
        if(data.doctorId() != null) return doctorRepository.getReferenceById(data.doctorId());

        if (data.specialty() == null) throw new ValidException("Specialty field is required!");

        return doctorRepository.chooseRandomDoctorAvailable(data.specialty(), data.date());
    }
}
