package edu.tum.ase.project.repository;

import edu.tum.ase.project.model.SourceFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 8 * Note that Spring provides a variety of Repository abstractions, JpaRepository is
 technology-specific
 9 * see https://docs.spring.io/spring-data/jdbc/docs/1.0.11.RELEASE/reference/html/#
 repositories.core-concepts
 10 */
@Repository
public interface SourceFileRepository extends JpaRepository<SourceFile, String> {
    SourceFile findByName(String name);
}
