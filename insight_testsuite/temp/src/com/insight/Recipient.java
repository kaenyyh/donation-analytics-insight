package com.insight;

/*
 *	This class define Recipient object with fields of Recipient name, zipcode and year 
 *	To use Recipient object as key of HashMap, equals() and hashcode() are overrided
 */

public class Recipient {
	// fields:
	private String id;
    private String zipcode;
    private String year;

    public Recipient(String id, String zipcode, String year) {
    	// Constructor:
        this.id = id;
        this.zipcode = zipcode;
        this.year = year;
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

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if ((obj instanceof Recipient)) {
            Recipient another = (Recipient) obj;
            return this.id.equals(another.id) && this.zipcode.equals(another.zipcode) && this.year.equals(another.year);
        }
        return false;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.zipcode != null ? this.zipcode.hashCode() : 0);
        hash = 67 * hash + (this.year != null ? this.year.hashCode() : 0);
        return hash;
    }
}
