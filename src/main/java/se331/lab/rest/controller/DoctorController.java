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
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.service.DoctorService;
import se331.lab.rest.service.PatientService;
import se331.lab.rest.service.UserService;
import se331.lab.rest.util.CommentForm;
import se331.lab.rest.util.LabMapper;

@Controller
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    UserService userService;

    @Autowired
    PatientService patientService;
    @GetMapping("doctors")
    public ResponseEntity<?> getDoctorLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "title", required = false) String title) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Doctor> pageOutput;
        if (title == null) {
            pageOutput = doctorService.getDoctors(perPage, page);
        } else {
            pageOutput = doctorService.getDoctors(title, PageRequest.of(page - 1, perPage));
        }
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getDoctorDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);

    }

    @GetMapping("doctors/{id}")
    public ResponseEntity<?> getDoctor(@PathVariable("id") Long id) {

        Doctor output = doctorService.getDoctor(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getDoctorDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
    @PostMapping("plists/{id}/comment")
    public ResponseEntity<?> postComment(@RequestBody CommentForm commentForm){
        Patient comment_to = patientService.getPatient(commentForm.getComment_to());
        Doctor comment_by = doctorService.getDoctor(commentForm.getComment_by());
        Patient output = doctorService.giveComment(commentForm.getContent(),comment_by,comment_to);
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDto(output));
    }
}