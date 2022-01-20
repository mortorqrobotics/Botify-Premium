package org.team1515.botifypremium.Utils;

import java.util.NavigableMap;
import java.util.TreeMap;

public class ShooterDist {
    public static NavigableMap<Integer, Double> map = new TreeMap<Integer, Double>();

    static {
        map.put(0, 200.0); // Between 0-2
        map.put(3, 400.0); // Between 3-4
        map.put(5, 600.0); // Between 4-7
        map.put(8, 800.0); // Between 8-9
        map.put(10, 1000.0); // Between 10-12
    }   
}
