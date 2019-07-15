package com.tts.TransitApp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tts.TransitApp.model.Bus;
import com.tts.TransitApp.service.BusComparator;

import lombok.Data;

@Data
public class BusRequest {
    public String address;
    public String city;

    public List<Bus> getNearbyBuses(BusRequest request){
        List<Bus> allBuses = this.getBuses();
        Location personLocation = this.getCoordinates(request.address + " " + request.city);
        List<Bus> nearbyBuses = new ArrayList<>();
        for(Bus bus : allBuses) {
            Location busLocation = new Location();
            busLocation.lat = bus.LATITUDE;
            busLocation.lng = bus.LONGITUDE;
            double latDistance = Double.parseDouble(busLocation.lat) - Double.parseDouble(personLocation.lat);
            double lngDistance = Double.parseDouble(busLocation.lng) - Double.parseDouble(personLocation.lng);
            if (Math.abs(latDistance) <= 0.02 && Math.abs(lngDistance) <= 0.02) {
                double distance = getDistance(busLocation, personLocation);
                if (distance <= 1) {
                    bus.distance = (double) Math.round(distance * 100) / 100;
                    nearbyBuses.add(bus);
                }
            }
        }
        Collections.sort(nearbyBuses, new BusComparator());
        return nearbyBuses;
    }

	private double getDistance(Location busLocation, Location personLocation) {
		// TODO Auto-generated method stub
		return 0;
	}

	private Location getCoordinates(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Bus> getBuses() {
		// TODO Auto-generated method stub
		return null;
	}
}
    