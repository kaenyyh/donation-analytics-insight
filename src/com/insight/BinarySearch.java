package com.insight;

import java.util.ArrayList;

/*
 * Return the index for newInput insertion to maintaining ArrayList sorted
 * Time complexity:  binary search : O(logN)
 */

public class BinarySearch {

    public static int findInsertIndex (ArrayList<Integer> donateList, int newAmount) {
        // method of binary search
        int leftIndex = 0;
        int rightIndex = donateList.size() - 1;
        
        
        while (leftIndex <= rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;

            if (donateList.get(midIndex) == newAmount) {
                return midIndex;
            } else if (donateList.get(midIndex) < newAmount) {
                leftIndex = midIndex + 1;
            } else {
                rightIndex = midIndex - 1;
            }

        }
        return donateList.get(leftIndex) > newAmount ? leftIndex : rightIndex;
    }
}
