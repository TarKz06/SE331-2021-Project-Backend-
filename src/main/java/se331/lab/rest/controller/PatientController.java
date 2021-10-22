package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.service.PatientService;
import se331.lab.rest.util.LabMapper;

@Controller
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("plists")
    public ResponseEntity<?> getPatientLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "title", required = false) String title) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Patient> pageOutput;
        if (title == null) {
            pageOutput = patientService.getPatients(perPage, page);
        } else {
            pageOutput = patientService.getPatients(title, PageRequest.of(page - 1, perPage));
        }
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getPatientDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);

    }

    @GetMapping("plists/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") Long id) {

        Patient output = patientService.getPatient(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PostMapping("/plists")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        Patient output = patientService.save(patient);
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDto(output));


    }
}
