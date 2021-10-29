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
import se331.lab.rest.security.entity.User;
import se331.lab.rest.service.UserService;
import se331.lab.rest.util.LabMapper;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("users")
    public ResponseEntity<?> getUserLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "title", required = false) String title) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<User> pageOutput;
        if (title == null) {
            pageOutput = userService.getUsers(perPage, page);
        } else {
            pageOutput = userService.getUsers(title, PageRequest.of(page - 1, perPage));
        }
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getUserDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);

    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {

        User output = userService.getUser(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PostMapping("set-role/{id}/doctors")
    public ResponseEntity<?> SetRoleToDoctor(
            @PathVariable("id") Long id
    ){
        User user = userService.getUser(id);
        Doctor output = userService.setRoleToDoctor(user);
        return ResponseEntity.ok(LabMapper.INSTANCE.getDoctorDto(output));
    }

    @PostMapping("set-role/{id}/plists")
    public ResponseEntity<?> SetRoleToPatient(
            @PathVariable("id") Long id
    ){
        User user = userService.getUser(id);
        Patient output = userService.setRoleToPatient(user) ;
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDto(output));
    }
}
