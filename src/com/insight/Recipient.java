package com.insight;


public class Recipient {
    private String id;
    private String zipcode;
    private String year;

/*    public Recipient() {

    }*/

    public Recipient(String id, String zipcode, String year) {
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
/*        if (this == obj) {
            return true;
        }*/
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
