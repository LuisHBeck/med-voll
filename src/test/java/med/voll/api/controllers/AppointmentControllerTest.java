package med.voll.api.controllers;

import med.voll.api.domain.dtos.appointment.AppointmentDetailingData;
import med.voll.api.domain.dtos.appointment.AppointmentScheduleData;
import med.voll.api.domain.models.Specialty;
import med.voll.api.service.AppointmentScheduleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentScheduleData> appointmentScheduleDataJson;

    @Autowired
    private JacksonTester<AppointmentDetailingData> appointmentDetailingDataJson;

    @MockBean
    private AppointmentScheduleService scheduleService;


    @Test
    @DisplayName("Should return HTTP 400")
    @WithMockUser
    void scheduleScenario1() throws Exception {
        var response = mvc
                .perform(post("/appointments"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return HTTP 200")
    @WithMockUser
    void scheduleScenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;
        var appointmentDetailingData = new AppointmentDetailingData(null, 1l, 1l, date);

        when(scheduleService.schedule(any())).thenReturn(appointmentDetailingData);

        var response = mvc
                .perform(
                        post("/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(appointmentScheduleDataJson
                                        .write(new AppointmentScheduleData(1l, specialty, 1l, date))
                                        .getJson()
                                )
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = appointmentDetailingDataJson.write(appointmentDetailingData)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}