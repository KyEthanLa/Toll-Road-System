// comparable interface which indicates that it can be compared to other customerAccount objects
public class CustomerAccount implements Comparable<CustomerAccount> {
    private String firstName;
    private String lastName;
    private int accountBalance;
    private Vehicle vehicle;
    private DiscountType discountType;

    // by default the discount type is set to NONE
    public CustomerAccount(String firstName, String lastName, int accountBalance, Vehicle vehicle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountBalance = accountBalance;
        this.vehicle = vehicle;
        this.discountType = DiscountType.NONE;
    }

    // enum which shows the different options that can be applied to the customer
    enum DiscountType {
        NONE, STAFF, FRIENDS_AND_FAMILY
    }

    public void activateStaffDiscount() {
        this.discountType = DiscountType.STAFF;
    }

    public void activateFriendsAndFamilyDiscount() {
        this.discountType = DiscountType.FRIENDS_AND_FAMILY;
    }


    public void deactivateDiscount() {
        this.discountType = DiscountType.NONE;
    }

    // takes amount as argument and adds it by the amount to the balance
    public void addFunds(int amount) {
        this.accountBalance += amount;
    }

    /* deducts the trip cost from the account balance,
       then applies any discounts that customer may have,
       then rounds to the nearest pence,
       if trip is greater than account balance then throws exception made in another class
    */
    public int makeTrip() throws InsufficientAccountBalanceException {
        int basicTripCost = this.vehicle.calculateBasicTripCost();
        int discountedTripCost = applyDiscount(basicTripCost);
        int roundedTripCost = roundTripCost(discountedTripCost);

        if (roundedTripCost > this.accountBalance) {
            throw new InsufficientAccountBalanceException();
        }

        this.accountBalance -= roundedTripCost;
        return roundedTripCost;
    }

    /* by default, the discount rate is 1.0, then uses switch statements,
       if there is a staff discount then discount is .5%,
       if there is famandfriends then discount is .9

     */

    private int applyDiscount(int cost) {
        double discountRate = 1.0;
        switch (this.discountType) {
            case STAFF:
                discountRate = 0.5;
                break;
            case FRIENDS_AND_FAMILY:
                discountRate = 0.9;
                break;
        }
        return (int) Math.floor(cost * discountRate); // rounds down to the nearest integer
    }

    /*
        divides cost by 100 to remove last digits then multiplies by 100,
        e.g. 7630 / 100 = 76 -> 76 * 100 = 7600
     */

    private int roundTripCost(int cost) {
        return cost / 100 * 100;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }


    // used in first semester
    @Override
    public int compareTo(CustomerAccount otherAccount) {
        String thisRegistrationNumber = this.vehicle.getRegistration();
        String otherRegistrationNumber = otherAccount.getVehicle().getRegistration();

        if (thisRegistrationNumber.compareTo(otherRegistrationNumber) < 0) {
            // return -1 to indicate this account comes before the other account in sorting order
            return -1;
        } else if (thisRegistrationNumber.compareTo(otherRegistrationNumber) > 0) {
            // return 1 to indicate this account comes after the other account in sorting order
            return 1;
        } else {
            // if the registration numbers are equal, return 0 to indicate both accounts are equal in sorting order
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Customer Account: " + "First Name = " + firstName + ", Last Name = " + lastName + ", Account Balance = "
                + accountBalance + ", Vehicle = " + vehicle + ", Discount Type = " + discountType;
    }

    /*

    public static void main(String[] args) {

        Car car = new Car("abc123", "toyota", 5);
        CustomerAccount customer = new CustomerAccount("ethan", "la", 1000, car);
        System.out.println(customer.toString());
        customer.addFunds(500);

       try {
            int tripCost = customer.makeTrip();
            System.out.println("Trip cost: $" + tripCost);
        } catch (InsufficientAccountBalanceException e) {
            System.out.println("Insufficient account balance!");
        }

        // OUTPUT ---> Customer Account: First Name = ethan, Last Name = la, Account Balance = 1000,
                       Vehicle = Car: registration = abc123, make = toyota, numberOfSeats = 5, Discount Type = NONE
        //             Trip cost: $500
    }

     */
}


