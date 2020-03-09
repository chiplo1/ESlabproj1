/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

/**
 *
 * @author guilherme
 */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/plane")
public interface PlaneRepository extends JpaRepository<Plane, String>{

    public List<Plane> getAllPlanes();

    public Plane getPlane(String icao24);

    public void updatePlane(Plane plane, String icao24);

    public void deletePlane(String icao24);

    public Plane addPlane(Plane plane);
    
    public boolean exists(String icao24);
}