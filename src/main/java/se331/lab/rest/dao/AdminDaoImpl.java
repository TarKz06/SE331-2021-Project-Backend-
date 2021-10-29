package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Admin;
import se331.lab.rest.repository.AdminRepository;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.UserRepository;

public class AdminDaoImpl implements AdminDao {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public Integer getAdminSize() {
        return Math.toIntExact(adminRepository.count());
    }

    @Override
    public Page<Admin> getAdmins(Integer pageSize, Integer page) {
        return adminRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Admin getAdmin(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Page<Admin> getAdmin(String name, Pageable page) {
        return null;
    }
}
