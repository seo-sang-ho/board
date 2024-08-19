package article.projectNum1.write.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import article.projectNum1.write.entity.Write;

@Repository
public interface WriteRepository extends JpaRepository<Write, Long> {
}
