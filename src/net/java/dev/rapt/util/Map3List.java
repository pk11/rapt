package net.java.dev.rapt.util;

import java.util.*;


/**
A Map with 3 nested keys, and whose value is a list. Use this like a database
table with three keys.
<p>
The various keys() methods and the values() method can be used in nested foreach loops
to loop through all the values.
<p>
The 4 arg {@see #put(Object,Object,Object,Object)} method creates any nested maps that are required, and the List of values if those
keys haven't already been used.
*/

public class Map3List<K1,K2,K3,V> extends HashMap<K1, Map<K2, Map<K3, List<V>>>> {

    /** Construct a Map3List with the specified size at the top level. */
    public Map3List(int sz) {
        super(sz);
    }

    /** put a value in the map, indexed by its three keys */
    public void put(K1 k1, K2 k2, K3 k3, V value) {
        if(! containsKey(k1)) {
            put(k1, new HashMap<K2, Map<K3, List<V>>>(10));
	    }
	    Map<K2, Map<K3, List<V>>> m2 = get(k1);
	    if(! m2.containsKey(k2)) {
	        m2.put(k2, new HashMap<K3,List<V>>(10));
        }
        Map<K3, List<V>> m3 = m2.get(k2);
        if(! m3.containsKey(k3)) {
            m3.put(k3, new LinkedList<V>());
        }
        List<V> list = m3.get(k3);
        list.add(value);
    }

    /** Iterate through all the values put in the Map3List with these keys */
    public Iterable<V> values(K1 k1, K2 k2, K3 k3) {
        return get(k1).get(k2).get(k3);
    }

    /** Iterate through all the top level keys */
    public Iterable<K1> keys() {
        return keySet();
    }

    /** Iterate through the second level keys for the specified first level key */
    public Iterable<K2> keys(K1 k1) {
        return get(k1).keySet();
    }

    /** Iterate through the third level keys for the specified first and second level keys */
    public Iterable<K3> keys (K1 k1, K2 k2) {
        return get(k1).get(k2).keySet();
    }
}
