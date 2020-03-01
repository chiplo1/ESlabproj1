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

public interface PlaneRepository {

    public List<Plane> getAllPlanes();

    public Plane getPlane(int id);

    public void updatePlane(Plane plane, int id);

    public void deletePlane(int id);

    public void addPlane(Plane plane);
}