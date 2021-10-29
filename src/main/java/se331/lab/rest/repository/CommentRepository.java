package se331.lab.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
