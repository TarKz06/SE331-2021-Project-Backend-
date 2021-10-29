package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.lab.rest.entity.*;
import se331.lab.rest.security.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);
    @Mapping(target = "authorities", expression = "java(patient.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    PatientDTO getPatientDto(Patient patient);
    List<PatientDTO> getPatientDto(List<Patient> patients);

    UserDTO getUserDTO(User user);
    List<UserDTO> getUserDTO(List<User> user);

    @Mapping(target = "authorities", expression = "java(doctor.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    DoctorDTO getDoctorDto(Doctor doctor);
    List<DoctorDTO> getDoctorDto(List<Doctor> doctors);

    @Mapping(target = "authorities", expression = "java(admin.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    AdminDTO getAdminDto(Admin admin);
    List<AdminDTO> getAdminDto(List<Admin> admin);


}
