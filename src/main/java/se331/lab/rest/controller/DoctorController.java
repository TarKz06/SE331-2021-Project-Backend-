package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se331.lab.rest.service.DoctorService;
import se331.lab.rest.util.LabMapper;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @GetMapping("/doctors")
    ResponseEntity<?> getDoctors(){
        return ResponseEntity.ok(LabMapper.INSTANCE.getDoctorDTO(doctorService.getAllDoctor()));
    }
}
