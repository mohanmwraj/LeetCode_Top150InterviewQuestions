package _Arrays;
import java.util.*;
/*
https://leetcode.com/problems/insert-delete-getrandom-o1/description/

 */
public class InsertDeleteGetRandom {
    /*
        * Approach: Hash Map + Array List
        *
        * Hashmap provides Insert and Delete in average constant time, although has problems with GetRandom.
        * The idea of GetRandom is to choose a random index and then retrieve an element with that index.
        * There are no indexes in the hashmap, and hence to get a true random value, one has first to convert hashmap keys in a list, which would take linear time.
        * The solution here is to build a list of keys aside and use this list to compute GetRandom in constant time.
        *
        * Array List has indexes and could provide Insert and GetRandom in average constant time, though has problems with Delete.
        * Deleting a value at an arbitrary index takes linear time. The solution here is to always delete the last value:
        *   * Swap the element to delete with the last one.
        *   * Pop the last element out.
        *
        * Both ways converge into the same combination of data structures:
        *   * Hashmap element -> its index.
        *   Array List of elements.
     */

    Map<Integer, Integer> dict;
    List<Integer> list;
    Random rand;

    public InsertDeleteGetRandom() {
        dict = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    public boolean insert(int val){
        if(dict.containsKey(val)) return false;

        dict.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

    public boolean remove(int val){
        if(!dict.containsKey(val)) return false;

        int lastElement = list.getLast();
        int idx = dict.get(val);
        list.set(idx, lastElement);
        dict.put(lastElement, idx);

        list.removeLast();
        dict.remove(val);
        return true;
    }

    public int getRandom(){
        return list.get(rand.nextInt(list.size()));
    }

    /*
        Time Complexity: O(1)
        Space Complexity: O(N)
     */
}
