package com.example.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class CityTest {

    @Test
    public void build() {
        City city = City.build("Montreal");
        Assert.assertEquals("MONTREAL", city.getName());
    }

    @Test
    public void buildWithNeighbours() {
        City city = City.build("Montreal");
        city.addNearby(City.build("Laval"))
                .addNearby(City.build("Lachine"));

        Set<City> nearby = city.getNearby();
        Assert.assertEquals(2, nearby.size());
        Assert.assertTrue(nearby.contains(City.build("Laval")));
    }


    @Test
    public void addNearby() {
        City city = City.build("Montreal");
        city.addNearby(City.build("Laval"))
                .addNearby(City.build("Lachine"));

        Assert.assertEquals(2, city.getNearby().size());
    }

    @Test
    public void addNearbyDuplicates() {
        City city = City.build("Montreal");
        city.addNearby(City.build("Laval"))
                .addNearby(City.build("LAVAL"))
                .addNearby(City.build("  LaVal"))
                .addNearby(City.build("  LaVaL "));

        Assert.assertEquals(1, city.getNearby().size());
    }


}