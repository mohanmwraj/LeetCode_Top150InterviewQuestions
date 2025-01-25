package heap;

import SlidingWindow.minimumWindowSubstring;

import java.util.*;

/*
    https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 */

class Pair<K, V> {

    private final K element0;
    private final V element1;

    public <K, V> Pair<K, V> createPair(K element0, V element1) {
        return new Pair<K, V>(element0, element1);
    }

    public Pair(K element0, V element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    public K getKey() {
        return element0;
    }

    public V getValue() {
        return element1;
    }

}
public class findKPairswithSmallestSums {
    /*
        * Approach: Using heap
        *
        * use set to find whether index is visited or not.
        * use priority queue to fetch laregest sum.
        *
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (a, b) -> (a[0] - b[0])
        );
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        List<List<Integer>> answer = new ArrayList<>();
        queue.add(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add(new Pair<>(0, 0));

        while(k-- > 0){
            int[] top = queue.poll();
            int x = top[1];
            int y = top[2];

            answer.add(List.of(nums1[x], nums2[y]));
            if(x + 1 < m && !visited.contains(new Pair<>(x + 1, y))){
                queue.add(new int[]{nums1[x + 1] + nums2[y], x + 1, y});
                visited.add(new Pair<>(x + 1, y));
            }

            if(y + 1 < n && !visited.contains(new Pair<>(x, y + 1))){
                queue.add(new int[]{nums1[x] + nums2[y + 1], x , y + 1});
                visited.add(new Pair<>(x, y + 1));
            }
        }

        return answer;
    }
    /*
        Time complexity: O(min(k⋅logk,m⋅n⋅log(m⋅n)))
        Space complexity: O(min(k,m⋅n))
     */
}
