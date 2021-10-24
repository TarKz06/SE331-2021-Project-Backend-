package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Admin;

public interface AdminDao {
    Integer getAdminSize();
    Page<Admin> getAdmins(Integer pageSize, Integer page);
    Admin getAdmin(Long id);

    Admin save(Admin admin);
    Page<Admin> getAdmin(String name, Pageable page);
}
