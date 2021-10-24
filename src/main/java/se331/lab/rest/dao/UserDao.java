package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.security.entity.User;

public interface UserDao {

    Integer getUserSize();
    Page<User> getUsers(Integer pageSize, Integer page);
    User getUser(Long id);

    User save(User user);
    Page<User> getUser(String name, Pageable page);
}
