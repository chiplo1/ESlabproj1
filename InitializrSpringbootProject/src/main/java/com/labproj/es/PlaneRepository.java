package com.labproj.es;

/**
 *
 * @author guilherme
 */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/planes")
public interface PlaneRepository extends JpaRepository<Plane, String>{
    
}