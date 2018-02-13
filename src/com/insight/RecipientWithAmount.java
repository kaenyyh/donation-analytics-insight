package com.insight;

/*
 *	This class define Recipient object with fields of Recipient name, zipcode and year 
 *	To use Recipient object as key of HashMap, equals() and hashcode() are overrided
 */

public class RecipientWithAmount {
	// fields:
	private String id;
    private String zipcode;
    private String year;
    private int amount;

    public RecipientWithAmount(String id, String zipcode, String year, int amount) {
    	// Constructor:
        this.id = id;
        this.zipcode = zipcode;
        this.year = year;
        this.amount = amount;
    }

    
    public String getId() {
        return this.id;
    }

    
    public String getZipcode() {
        return this.zipcode;
    }
    
    
    public String getYear() {
        return this.year;
    }

    public int getAmount() {
    	return this.amount;
    }
}