package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.security.entity.User;

public interface UserService {

    Integer getUserSize();
    Page<User> getUsers(Integer pageSize, Integer page);
    User getUser(Long id);

    User save(User user);
    Page<User> getUsers(String title, Pageable pageable);
}
