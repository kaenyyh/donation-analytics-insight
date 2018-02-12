## Donation Analytics

### 1. Project Summary:

This repo is to solve donation analytics problem from InsightDataScience.
The output files contains information of recipient who received donations from repeated donors in particular year.

### 2. Work Environment

This java program works in JDK 1.8 environment.


### 3. Methodology

**3.1** For each new donation information read from input file, we need to check if the donor has repeated donation in previous year. To have `O(1)` time search, HashMap is used here. To guarantee the unique of donor identity, the HashMap `key` is self-defined object `Donor`, which has two fields: `.name` and `.zipcode`. In order to target on the particular recipient, the HashMap `value` is also a self-defined object `Recipient`, which has three fields: `.id`, `.year` and `.zipcode`; 

		HashMap of Donor: `< Donor (.name, .zipcode), Recipient (.id, .year, .zipcode) >`

**3.2** If the donor is identified as a repeated donor, who has donation in any previous year, we then need to find all of the donation from repeated donors for the same recipient in a particular year. HashMap is used here to have `O(1)` time complexity for search. HashMap `Key` is the same object as `Value` in HashMap of Donor; The HashMap `Value` is object, including all received donate amount, with its sum and size.

		HashMap of Recipient: `< Recipient (.id, .year, .zipcode), DonateAmount (.amountList, .totalAmount, .totalNumber) >`

**3.3** Percentile:  

In order to find the corresponding donate amount for required `percentile`, we need to keep the record of all donation from repeated donor to the same recipient. Thus, an ArrayList is used for this purpose as the `.amountList` field in `DonateAmount`. For each new input amount, using `Binary Search` to find its corresponding index to be inserted to maintain the order.

**3.4** Time Complexity of ArrayList and Binary Search: 

the idea is to maintain the ascending order of amount for streaming data, with least Time complexity. 
For each new input amount, `Binary Search` uses Time `O(logN)` to find insertion index; Then after insertion, the right side of ArrayList will need to shift, the worst case is `O(N)` and Amortized time is `O(logN)`. The total amortized time complexity is `O(logN + logN) ~ O(logN)`
.

**3.5** The reason to maintain fields of `.totalAmount` and `.totalNumber` is to avoid calculate them by iterating ArrayList for every new input.


### 4. Explanation of Java codes:

There are five java files:

**4.1** `donationanalytics.java`: it is the main java file to read in the donation file, create data structure and processing data by calling other class, write the results to output file. 

**4.2** `Donor.java`: create Donor object with fields of `.name` and `.zipcode`; Override the `equals()` and `hashcode()`;

**4.3** `Recipient.java`: create Recipient object with fields of `.id`, `.year` and `.zipcode`; Override the `equals()` and `hashcode()`;

**4.4** `DonateAmount.java`: create DonateAmount object with fields of ArrayList `.amoutList`, `.totalAmount` and `.totalNumber`; For each new repeated donation, `totalAmount + new Amount`, and `totalNumber + 1`.

**4.5** `BinarySearch.java`: Standard binary search method to find the index to maintain ascending order of ArrayList.


### 5. Discussion

**5.1** Binary Search + Insertion or Append + Sort

