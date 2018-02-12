package com.insight;

/*
 *	This class define donor object with fields of donor name and donor zipcode
 *	To use donor object as key of HashMap, equals() and hashcode() are overrided
 */

public class Donor {
	// fields:
    private String name;
    private String zipcode;

	public Donor(String name, String zipcode) {
        // Constructor:
		this.name = name;
        this.zipcode = zipcode;
	}

	// override equals() 
	@Override
    public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
        if ((obj instanceof Donor)) {
            Donor another = (Donor) obj;
            return this.name.equals(another.name) && this.zipcode.equals(another.zipcode);
        }
        return false;

    }

	// override hashcode()
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 67 * hash + (this.zipcode != null ? this.zipcode.hashCode() : 0);
        return hash;
    }

    
    public String getName() {
        return name;
    }

    
    public String getZipcode() {
        return zipcode;
    }

}
