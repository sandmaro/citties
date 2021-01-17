package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

public class Commuter {

    private static final Log LOG = LogFactory.getLog(Commuter.class);

    private Commuter() { }

    /**
     * Find if destination city is reachable from origin. Will visit all the cities
     * on the bucket list which is built by collecting all the neighbours of a visited place
     * @param origin the origin
     * @param destination the destination
     * @return true if cities are connected
     */
    public static boolean commute(City origin, City destination) {



        LOG.info("Origin: " + origin.getName() + ", destination: " + destination.getName());

        if (origin.equals(destination)) return true;

        if (origin.getNearby().contains(destination)) return true;

        /*
         * The origin city was already visited since we have started from it
         */
        Set<City> visited = new HashSet<>(Collections.singleton(origin));

        /*
         * Put all the neighboring cities into a bucket list
         */
        Deque<City> bucketlist = new ArrayDeque<>(origin.getNearby());


        while (!bucketlist.isEmpty()) {


            City city = bucketlist.getLast();

            if (city.equals(destination)) return true;

            // remove the city from the bucket list

            // first time visit?
            if (!visited.contains(city)) {

                visited.add(city);

                // add neighbours to the bucket list and
                // remove already visited cities from the list
                bucketlist.addAll(city.getNearby());
                bucketlist.removeAll(visited);

                LOG.info("Visiting: ["
                        + city.getName()
                        + "] , neighbours: ["
                        + (city.prettyPrint())
                        + "], bucketlist: ["
                        + bucketlist.toString()
                        + "]");
            } else {
                // the city has been visited, so remove it from the bucket list
                bucketlist.removeAll(Collections.singleton(city));
            }
        }

        return false;
    }
}

