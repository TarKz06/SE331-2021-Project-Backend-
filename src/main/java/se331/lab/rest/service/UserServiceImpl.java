package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.UserDao;
import se331.lab.rest.dao.DoctorDao;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.security.entity.User;

import javax.transaction.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public Integer getUserSize() {
        return userDao.getUserSize();
    }

    @Override
    public Page<User> getUsers(Integer pageSize, Integer page) {
        return userDao.getUsers(pageSize, page);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return null;
    }

    @Override
    public Page<User> getUsers(String title, Pageable pageable) {
        return userDao.getUser(title,pageable);
    }

    public Doctor setRoleToDoctor(User user){ return userDao.setRoleToDoctor(user); }

    @Override
    public Patient setRoleToPatient(User user) {
        return userDao.setRoleToPatient(user);
    }
}
