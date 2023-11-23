import java.util.ArrayList;
import java.util.List;

public class TollRoad {
    private List<CustomerAccount> customerAccounts; // list of customerAccount objects
    private int moneyMade; // total money made by the toll road

    public TollRoad() {
        customerAccounts = new ArrayList<>(); // creates the customerAccount as array list
        moneyMade = 0; // sets automatically to 0 money made when beginning
    }

    public List<CustomerAccount> getCustomerAccounts() {
        return customerAccounts; // returns list of customer accounts
    }

    public int getMoneyMade() {
        return moneyMade; // returns the money made
    }

    // takes customer account object as an argument and adds it to the array list
    public void addCustomer(CustomerAccount acc) {
        customerAccounts.add(acc);
    }

    /*
        takes registration number argument and searches for customer in customerAccounts
     */
    // customerRNotFoundException if the customer account is not found.
    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException {
        for (CustomerAccount acc : customerAccounts) {
            if (acc.getVehicle().getRegistration().equals(regNum)) {
                return acc;
            }
        }
        throw new CustomerNotFoundException();
    }


    /*
        takes reg number argument and then calls the findCustomer,
        then uses makeTrip on the customer account and deducts the money from their account,
        money then added to the moneyMade variable
     */
    // insufficientAccountBalanceException if the customer's account balance is not sufficient.
    public void chargeCustomer(String registrationNumber) throws CustomerNotFoundException, InsufficientAccountBalanceException {
        CustomerAccount acc = findCustomer(registrationNumber);
        int tripCost = acc.makeTrip();
        moneyMade += tripCost;
    }

    // toString method to provide a string representation of the TollRoad object.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TollRoad with ").append(customerAccounts.size()).append(" customers:").append("\n");
        for (CustomerAccount acc : customerAccounts) {
            sb.append(acc.toString()).append("\n");
        }
        sb.append("Total money made: ").append(moneyMade);
        return sb.toString();
    }

    /*
    public static void main(String[] args) {

        Car car1 = new Car("abc123", "toyota", 5);
        Car car2 = new Car("def456", "honda", 4);

        CustomerAccount customer1 = new CustomerAccount("ethan", "la", 1000, car1);
        CustomerAccount customer2 = new CustomerAccount("oh", "yes", 1500, car2);

        TollRoad tollRoad = new TollRoad();
        tollRoad.addCustomer(customer1);
        tollRoad.addCustomer(customer2);

        try {
            tollRoad.chargeCustomer("abc123");
            tollRoad.chargeCustomer("def456");
        } catch (CustomerNotFoundException e) {
            System.out.println("Customer not found!");
        } catch (InsufficientAccountBalanceException e) {
            System.out.println("Insufficient account balance!");
        }

        System.out.println(tollRoad.toString());
    }

    Customer Account: First Name = ethan, Last Name = la, Account Balance = 1000, Vehicle = Car: registration = abc123, make = toyota, numberOfSeats = 5, Discount Type = NONE
    Customer Account: First Name = oh, Last Name = yes, Account Balance = 1500, Vehicle = Car: registration = def456, make = honda, numberOfSeats = 4, Discount Type = NONE
     */
}











