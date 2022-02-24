package com.camunda.demo;

import com.camunda.demo.pojo.Booking;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    ProcessEngine processEngine;

    @PostMapping("/booking")
    String createBooking(@RequestBody Booking booking) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("firstName", booking.getFirstName());
        variables.put("lastname", booking.getLastName());
        variables.put("email", booking.getEmail());
        variables.put("countryFrom", booking.getCountryFrom());
        variables.put("countryTo", booking.getCountryTo());
        variables.put("date", booking.getDate());
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByMessage("BookingRequestMessage", variables);
        return processInstance.getProcessInstanceId();
    }


    @GetMapping(path = {"/booking-confirmation", "/booking-confirmation/{id}"})
    void confirmBooking(@PathVariable(required = true, name = "id") String id) {
        processEngine.getRuntimeService().createMessageCorrelation("ConfirmationRequestMessage").processInstanceId(id).correlate();
    }

}
