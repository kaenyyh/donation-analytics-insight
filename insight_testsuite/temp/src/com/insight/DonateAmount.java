package com.insight;

import java.util.ArrayList;

/*
 * This class define object with sorted arraylist of donation from repeated donor, total amount of donation, and the size;
 * 	1. maintain sorted ArrayList is to have O(1) time to find percentile amount
 * 	2. keep total amount and size as field can have O(1) time to get updates with newInput, 
 * 	   instead of O(N) iterative calculation for each newInput.
 * 	3. Use Binary Search to have O(logN) to insert newInput into sorted ArrayList.
 *     One thing here need to consider is: after insertion, the right side of ArrayList will need to shift.
 *     It will require O(N) in worst case and O(logN) amortize.
 */


public class DonateAmount {
	// Three fields
    private ArrayList<Integer> amountList;
    private int totalAmount;
    private int totalNumber;


    public DonateAmount(ArrayList<Integer> amountList) {
    	// constructor
        this.amountList = amountList;
        this.totalAmount = amountList.get(0);
        this.totalNumber = amountList.size();
    }
    

    public void addNew (int newInput) {
    	// addNew method has two steps: 1. add newInput at the end of ArrayList
    	// 								2. call BinarySearch to insert newInput
    	
        // corner case (empty ArrayList):
        if (amountList.isEmpty()) {
            amountList.add(newInput);
            return;
        }
        // corner case (only one element in ArrayList):
        if (amountList.size() == 1) {
            if (amountList.get(0) < newInput) {
                amountList.add(newInput);
            } else {
                amountList.add(0, newInput);
            }
        }

        // call BinarySearch to find insertion index
        int insertIndex = BinarySearch.findInsertIndex(amountList, newInput);   // System.out.println(insertIndex);
        amountList.add(insertIndex, newInput);

        // numbers of repeated donor + 1 after each new insertion
        totalNumber++;

        // total amount of donation updates after each new insertion
        totalAmount += newInput;
    }
    

    public int getPercentile (int percentile) {
    	// To get the donation amount for given percentile:
        int percentileIndex = (percentile / 100) * totalNumber;

        // round the percentile amount to nearest whole dollar, use Math.rint()
        return (int)Math.rint((double)amountList.get(percentileIndex));
    }

    
    public ArrayList<Integer> getAmountList() {
        return amountList;
    }

    
    public int getTotalAmount() {
        return totalAmount;
    }

    
    public int getTotalNumber() {
        return totalNumber;
    }
}
