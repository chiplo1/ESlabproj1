/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author guilherme
 */
@Component
public class PlaneRepositoryImpl implements PlaneRepository {
    private List<Plane> planeList;

    public PlaneRepositoryImpl() {
        planeList = new ArrayList<Plane>();
        planeList.add(new Plane(1, "Jane"));
        planeList.add(new Plane(2, "Jack"));
        planeList.add(new Plane(3, "George"));
    }

    public List<Plane> getAllPlanes() {
        return planeList;
    }

    public Plane getPlane(int id) {
        for (Plane pl : planeList) {
            if (pl.getId() == id) {
                return pl;
            }
        }
        throw new PlaneNotFound();
    }

    public void updatePlane(Plane plane, int id) {
        for (Plane pl : planeList) {
            if (pl.getId() == id) {
                pl.setId(plane.getId());
                pl.setFirstName(plane.getFirstName());
                return;
            }
        }
        throw new PlaneNotFound();
    }

    public void deletePlane(int id) {
        for (Plane pl : planeList) {
            if (pl.getId() == id) {
                planeList.remove(pl);
                return;
            }
        }
        throw new PlaneNotFound();
    }

    public void addPlane(Plane plane) {
        for (Plane pl : planeList) {
            if (pl.getId() == plane.getId()) {
                throw new PlaneAlreadyExists();
            }
        }
        planeList.add(plane);
    }

}
