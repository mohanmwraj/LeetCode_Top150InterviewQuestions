package binarySearch;
/*
    https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 */
public class medianOfTwoSortedArray {
    /*
        * Approach: Merge Sort
        *
        * For the arrays of even total length,
        * move the pointers n/2 - 1.
        * then return (n/2, n/2+1) / 2.0
        *
        * if it is odd, move the pointer before n/2 -1, and then return n/ 2.
     */
    private int p1 = 0, p2 = 0;

    // Get the smaller value between nums1[p1] and nums2[p2] and move the pointer forwards.

    private int getMin(int[] nums1, int[] nums2) {
        if (p1 < nums1.length && p2 < nums2.length) {
            return nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        } else if (p1 < nums1.length) {
            return nums1[p1++];
        } else if (p2 < nums2.length) {
            return nums2[p2++];
        }
        return -1;
    }

    public double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if ((m + n) % 2 == 0) {
            for (int i = 0; i < (m + n) / 2 - 1; ++i) {
                int tmp = getMin(nums1, nums2);
            }
            return (double) (getMin(nums1, nums2) + getMin(nums1, nums2)) / 2;
        } else {
            for (int i = 0; i < (m + n) / 2; ++i) {
                int tmp = getMin(nums1, nums2);
            }
            return getMin(nums1, nums2);
        }
    }
    /*
        Time Complexity: O(m + n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Binary Search, Recursive
        *
     */
    public double findMedianSortedArrays_2(int[] A, int[] B) {
        int na = A.length, nb = B.length;
        int n = na + nb;
        if ((na + nb) % 2 == 1) {
            return solve(A, B, n / 2, 0, na - 1, 0, nb - 1);
        } else {
            return (
                    (double) (solve(A, B, n / 2, 0, na - 1, 0, nb - 1) +
                            solve(A, B, n / 2 - 1, 0, na - 1, 0, nb - 1)) /
                            2
            );
        }
    }

    public int solve(
            int[] A,
            int[] B,
            int k,
            int aStart,
            int aEnd,
            int bStart,
            int bEnd
    ) {
        // If the segment of on array is empty, it means we have passed all
        // its element, just return the corresponding element in the other array.
        if (aEnd < aStart) {
            return B[k - aStart];
        }
        if (bEnd < bStart) {
            return A[k - bStart];
        }

        // Get the middle indexes and middle values of A and B.
        int aIndex = (aStart + aEnd) / 2, bIndex = (bStart + bEnd) / 2;
        int aValue = A[aIndex], bValue = B[bIndex];

        // If k is in the right half of A + B, remove the smaller left half.
        if (aIndex + bIndex < k) {
            if (aValue > bValue) {
                return solve(A, B, k, aStart, aEnd, bIndex + 1, bEnd);
            } else {
                return solve(A, B, k, aIndex + 1, aEnd, bStart, bEnd);
            }
        }
        // Otherwise, remove the larger right half.
        else {
            if (aValue > bValue) {
                return solve(A, B, k, aStart, aIndex - 1, bStart, bEnd);
            } else {
                return solve(A, B, k, aStart, aEnd, bStart, bIndex - 1);
            }
        }
    }
    /*
        Time Complexity: O(log (mn))
        Space Complexity: O(log m + log n)
     */
    /*
        * Approach: A Better Binary Search
        *
        * Determine the maximum value of the section A_left as maxLeftA = nums1[partitionA - 1].
        * If partitionA - 1 < 0, set it as maxLeftA = float(-inf).
        * Determine the minimum value of the section A_right as minRightA = nums1[partitionA].
        * If partitionA >= m, set it as minRightA = float(inf).
        * Determine the maximum value of the section B_left as maxLeftB = nums2[partitionB - 1].
        * If partitionB - 1 < 0, set it as maxLeftB = float(-inf).
        * Determine the maximum value of the section B_right as minRightB = nums2[partitionB].
        * If partitionB >= n, set it as minRightB = float(inf).
        *
     */
    public double findMedianSortedArrays_3(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays_3(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length;
        int left = 0, right = m;

        while (left <= right) {
            int partitionA = (left + right) / 2;
            int partitionB = (m + n + 1) / 2 - partitionA;

            int maxLeftA = (partitionA == 0)
                    ? Integer.MIN_VALUE
                    : nums1[partitionA - 1];
            int minRightA = (partitionA == m)
                    ? Integer.MAX_VALUE
                    : nums1[partitionA];
            int maxLeftB = (partitionB == 0)
                    ? Integer.MIN_VALUE
                    : nums2[partitionB - 1];
            int minRightB = (partitionB == n)
                    ? Integer.MAX_VALUE
                    : nums2[partitionB];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((m + n) % 2 == 0) {
                    return (
                            (Math.max(maxLeftA, maxLeftB) +
                                    Math.min(minRightA, minRightB)) /
                                    2.0
                    );
                } else {
                    return Math.max(maxLeftA, maxLeftB);
                }
            } else if (maxLeftA > minRightB) {
                right = partitionA - 1;
            } else {
                left = partitionA + 1;
            }
        }
        return 0.0;
    }

    /*
        Time Complexity: O(log (min (m, n))
        Space Complexity: O(1)
     */
    public double median(int[] arr1, int[] arr2) {
        // Size of two given arrays
        int n1 = arr1.length, n2 = arr2.length;

        /* Ensure arr1 is not larger than
        arr2 to simplify implementation*/
        if (n1 > n2) return median(arr2, arr1);

        int n = n1 + n2;

        // Length of left half
        int left = (n1 + n2 + 1) / 2;

        // Apply binary search
        int low = 0, high = n1;
        while (low <= high) {

            // Calculate mid index for arr1
            int mid1 = (low + high) >>> 1;

            // Calculate mid index for arr2
            int mid2 = left - mid1;

            // Calculate l1, l2, r1, and r2
            int l1 = (mid1 > 0) ? arr1[mid1 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? arr1[mid1] : Integer.MAX_VALUE;
            int l2 = (mid2 > 0) ? arr2[mid2 - 1] : Integer.MIN_VALUE;
            int r2 = (mid2 < n2) ? arr2[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                // If condition for finding median
                if (n % 2 == 1) return Math.max(l1, l2);
                else return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            }
            else if (l1 > r2) {
                // Eliminate the right half of arr1
                high = mid1 - 1;
            } else {
                // Eliminate the left half of arr1
                low = mid1 + 1;
            }
        }
        // Dummy statement
        return 0;
    }
}
