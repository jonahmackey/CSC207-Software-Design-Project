package usecases;

import entities.Facility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FacilityMap {
    private final HashMap<String, Facility> facilityMap = new HashMap<>();

    /**
     * create a Hashmap of the facility list from the array so we can get the facility from the key which is the
     * name of the facility
     * @param facilityList passed in to create a hashmap of the same information
     */
    public FacilityMap(List<List<String>> facilityList){
        for (List<String> strings : facilityList) {
            Facility facility = new Facility(strings);
            facilityMap.put(facility.getFacilityInfo().get(0), facility);
        }
    }

    /**
     * get the facility from the facility map
     * @param name the name of the facility so it could be found in the hashmap
     * @return a facility object
     */
    public Facility getFacility(String name){
        return facilityMap.get(name);
    }

}
