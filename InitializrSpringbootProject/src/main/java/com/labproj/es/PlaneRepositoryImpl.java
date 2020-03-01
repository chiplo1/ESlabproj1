/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import com.labproj.exceptions.PlaneAlreadyExists;
import com.labproj.exceptions.PlaneNotFound;
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
    }

    public List<Plane> getAllPlanes() {
        return planeList;
    }

    public Plane getPlane(String icao24) {
        for (Plane pl : planeList) {
            if (pl.getIcao24() == icao24) {
                return pl;
            }
        }
        throw new PlaneNotFound();
    }

    public void updatePlane(Plane plane, String icao24) {
        for (Plane pl : planeList) {
            if (pl.getIcao24() == icao24) {
                pl.setIcao24(plane.getIcao24());
                pl.setCallsign(plane.getCallsign());
                return;
            }
        }
        throw new PlaneNotFound();
    }

    public void deletePlane(String icao24) {
        for (Plane pl : planeList) {
            if (pl.getIcao24() == icao24) {
                planeList.remove(pl);
                return;
            }
        }
        throw new PlaneNotFound();
    }

    public void addPlane(Plane plane) {
        for (Plane pl : planeList) {
            if (pl.getIcao24() == plane.getIcao24()) {
                throw new PlaneAlreadyExists();
            }
        }
        planeList.add(plane);
    }

}
