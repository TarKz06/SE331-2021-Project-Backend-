package se331.lab.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
