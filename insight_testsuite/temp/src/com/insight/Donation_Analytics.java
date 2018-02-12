package com.insight;


import java.io.*;
import java.util.*;

public class Donation_Analytics {

    public static void main (String[] args) {

        // specify the files path and name for two input files and one output file:

        String percentileFile = "input/percentile.txt";
        String itcontFile = "input/itcont.txt";
        String repeatedDonorFile = "output/repeat_donors.txt";

        if (args.length == 3) {
             percentileFile = args[0];
             itcontFile = args[1];
             repeatedDonorFile = args[2];
        }


        // First, read the percentile value from percentile.txt:
        // initialize the percentile value:
        int percentile = 0;

        try {
            // Using Scanner to read integer from input file:
            Scanner s = new Scanner(new File(percentileFile));
            percentile = s.nextInt();  // save percentile value:
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // test: System.out.println("Percentile is : " + percentile);


        // Second, read the donation info. from itcont.txt:
        try {
            // create bufferedreader and bufferedwriter:
            BufferedReader in = new BufferedReader(new FileReader(itcontFile));
            BufferedWriter out = new BufferedWriter(new FileWriter(repeatedDonorFile));
            String s; // store line by line

            // initialize HashMap of donor and recipient:
            // DonorMap key: Donor (.name, .zipcode)
            // DonorMap value: Recipient (.id, .year, .zipcode]
            Map<Donor, Recipient> donorMap = new HashMap<Donor, Recipient>();


            // save donation information of the repeated donor into RecipientMap
            // RecipientMap key: Recipient (.id, .year, .zipcode)
            // RecipientMap value: AmountList (.list, .sum, .numbers]
            Map<Recipient, DonateAmount> recipientMap  = new HashMap<Recipient, DonateAmount>();

            // The methodology to find the percentile amt:
            // maintain the ascending order of ArrayList of amt
            // for each new input, use binary search to find its position in order
            // time complexity: insert O(logn) and shift current list O(n)

            // test: ArrayList<String> tmpOut = new ArrayList<>();

            while ((s = in.readLine()) != null) {
                String[] donation = s.split("\\|");  // "|" is metacharacter in regex, use "\\" to escape it

                // Each line of Donation has 21 columns
                //      Recipient ID: donation[0] (not empty)
                //      DonorName: donation[7] (not empty)
                //      Zipcode: donation[10] (not empty and length == 5 || 9)
                //      Year: donation[13] (not empty and length == 8)
                //      Amout: donation[14] (not empty)
                //      Other_ID: donation[15] (must be empty)

                // now clean up the data:

                if (donation.length != 21 || donation[0].isEmpty() || donation[7].isEmpty() || donation[10].isEmpty() ||
                        donation[13].isEmpty() || donation[14].isEmpty() || !donation[15].isEmpty() ||
                        Integer.parseInt(donation[13].substring(4,8)) > 2018 || donation[13].length() != 8 ||
                        donation[10].length() < 5 || donation[10].length() > 9) {

                    continue;
                }

                String recipientId = donation[0];
                String donorName = donation[7];
                String zipcode = donation[10].substring(0,5);
                String year = donation[13].substring(4,8);
                int amount = Integer.parseInt(donation[14]);

                // test: System.out.println(recipientId + "|" + donorName + "|" + zipcode + "|" + year + "|" + amount);


                // create object:
                Donor donor = new Donor(donorName, zipcode);
                Recipient recipient = new Recipient(recipientId, zipcode, year);
                DonateAmount donateAmount = new DonateAmount(new ArrayList<Integer>(Arrays.asList(amount)));
             

                // check if the donor is repeated or not
                // if it is repeated donor, then save this donation to recipient hashMap
                if (!donorMap.containsKey(donor) || (donorMap.containsKey(donor) && donorMap.get(donor).getYear().equals(year))) {
                    donorMap.put(donor, recipient);
                } else {
                    if (!recipientMap.containsKey(recipient)) {
                        recipientMap.put(recipient, donateAmount);
                    } else {
                        // The methodology to find the percentile amt:
                        // maintain the ascending order of ArrayList of amt
                        // for each new input, use binary search to find its position in order
                        // time complexity: insert O(logn) and shift current list O(n)
                        recipientMap.get(recipient).addNew(amount);
                    }

                    // write output to output file:
                    // test: tmpOut.add(recipient.getId() + "|" + recipient.getZipcode() + "|" +recipient.getYear() + "|" + recipientMap.get(recipient).getPercentile(percentile) + "|" + recipientMap.get(recipient).getTotalAmount() + "|" + recipientMap.get(recipient).getTotalNumber());
                    out.write(recipient.getId() + "|" + recipient.getZipcode() + "|" +recipient.getYear() + "|" + recipientMap.get(recipient).getPercentile(percentile) + "|" + recipientMap.get(recipient).getTotalAmount() + "|" + recipientMap.get(recipient).getTotalNumber());
                    out.newLine();
                }
            }

            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

