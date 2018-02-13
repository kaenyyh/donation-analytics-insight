## Donation Analytics

### 1. Project Summary:

This repo is to solve donation analytics problem from InsightDataScience.
The output files contains information of recipient who received donations from repeated donors in particular year.

### 2. Work Environment

This java program works in JDK 1.8 environment.


### 3. Methodology

**3.1** For each new donation information read from input file, we need to check if the donor has repeated donation in previous year. To have `O(1)` time search, HashMap is used here. To guarantee the unique of donor identity, the HashMap `key` is self-defined object `Donor`, which has two fields: `.name` and `.zipcode`. In order to target on the particular recipient and solve out of order chronologically, the HashMap `value` is also a self-defined object `Recipient`, which has four fields: `.id`, `.year`, `.zipcode` and `.amount`; 

		HashMap of Donor: `< Donor (.name, .zipcode), RecipientWithAmount (.id, .year, .zipcode, .amount) >`

**3.2** If the donor is identified as a repeated donor, who has donation in any previous year, we then need to find all of the donation from repeated donors for the same recipient in a particular year. HashMap is used here to have `O(1)` time complexity for search. HashMap `Key` is the same object as `Value` in HashMap of Donor, except the amount field; The HashMap `Value` is object, including all received donate amount, with its sum and size.

		HashMap of Recipient: `< Recipient (.id, .year, .zipcode), DonateAmount (.amountList, .totalAmount, .totalNumber) >`

**3.3** Input out of order chronologically: 

For a new donation, if this donor is already saved in Donor HashMap (mean he/she is a repeated donor), but it was donated in the previous year than the saved donation, two steps will be applied: 1. extract the saved donation recipient information and amount, and save them into Recipient HashMap; 2. save the new donation (but in earlier year) into Donor HashMap.

**3.4** Percentile:  

In order to find the corresponding donate amount for required `percentile`, we need to keep the record of all donation from repeated donor to the same recipient. Thus, an ArrayList is used for this purpose as the `.amountList` field in `DonateAmount`. For each new input amount, using `Binary Search` to find its corresponding index to be inserted to maintain the order.

**3.5** Time Complexity of ArrayList and Binary Search: 

the idea is to maintain the ascending order of amount for streaming data, with least Time complexity. 
For each new input amount, `Binary Search` uses Time `O(logN)` to find insertion index; Then after insertion, the right side of ArrayList will need to shift, the worst case is `O(N)` and Amortized time is `O(logN)`. The total amortized time complexity is `O(logN + logN) ~ O(logN)`. But it will cost extra O(N) space.
.

**3.6** The reason to maintain fields of `.totalAmount` and `.totalNumber` is to avoid calculate them by iterating ArrayList for every new input.


### 4. Explanation of Java codes:

There are five java files:

**4.1** `donationanalytics.java`: it is the main java file to read in the donation file, create data structure and processing data by calling other class, write the results to output file. 

**4.2** `Donor.java`: create Donor object with fields of `.name` and `.zipcode`; Override the `equals()` and `hashcode()`;

**4.3** `Recipient.java`: create Recipient object with fields of `.id`, `.year` and `.zipcode`; Override the `equals()` and `hashcode()`;

**4.4** `DonateAmount.java`: create DonateAmount object with fields of ArrayList `.amoutList`, `.totalAmount` and `.totalNumber`; For each new repeated donation, `totalAmount + new Amount`, and `totalNumber + 1`.

**4.5** `BinarySearch.java`: Standard binary search method to find the index to maintain ascending order of ArrayList.


### 5. Discussion

**5.1 `Binary Search + Insertion` or `Append + Sort`**
During implementing the `Binary search` and `insertion`, I also considered about the `Append` firstly then `sort`. Here is my thought on their pros and cons:

**Pros**

`Binary Search`: only take O(logN) time to find the insertion index;

`Append`: only take O(1) time to add;

**Cons**

`BS + Insertion`: after insertion, all of the right side of ArrayList will shift one step, need Time O(N) and extra Space O(N) to copy list to new address.

`Append + Sort`: after append, use bubble sort can move it to correct place, by swapping it to all element with larger value. The whole process includes comparison and swap. 


**conclusion**

`Binary Search + insertion` may has a little advantages on time wise, but worse on the space requirement than `Append + sort`. So depends on the tolerance on time or space, we can use the proper one. Here I implement Binary search and insertion with sacrifice on space.

