import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TollRoadMain {

    // reads customer data from "customerData.txt" and creates customer accounts
    // returns the initialized TollRoad object.
    public static TollRoad initialiseTollRoadFromFile() {
        TollRoad tollRoad = new TollRoad();
        try {
            File file = new File("customerData.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // each line is split using a hashtag
                String[] customers = line.split("#"); // split customers using '#'

                for (String customerData : customers) { //another loop is used here to split the values inside each line
                    String[] values = customerData.split(","); // split customer data using ','

                    String vehicleType = values[0];
                    String regNum = values[1];
                    String firstName = values[2];
                    String lastName = values[3];
                    String make = values[4];
                    String vehicleInfo = values[5];
                    double startingBalance = Double.parseDouble(values[6]);
                    String discountType = values[7];

                    // vehicle object created
                    // object based on vehicle type and vehicle info
                    /*
                    vehicle objects are created and associated with the customerAccount object so it is needed during
                    the creation of the tollroad object, the purpose of it is to keep track of vehicles owned by each
                    customer
                     */
                    Vehicle vehicle;
                    switch (vehicleType) {
                        case "Car":
                            int numSeats = Integer.parseInt(vehicleInfo);
                            vehicle = new Car(regNum, make, numSeats);
                            break;
                        case "Van":
                            int payload = Integer.parseInt(vehicleInfo);
                            vehicle = new Van(regNum, make, payload);
                            break;
                        case "Truck":
                            int numTrailers = Integer.parseInt(vehicleInfo);
                            vehicle = new Truck(regNum, make, numTrailers);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
                    }

                    // create CustomerAccount object and set discount based on discount type
                    CustomerAccount customer = new CustomerAccount(firstName, lastName, (int) startingBalance, vehicle);
                    if (discountType.equals("STAFF")) {
                        customer.activateStaffDiscount();
                    } else if (discountType.equals("FRIENDS_AND_FAMILY")) {
                        customer.activateFriendsAndFamilyDiscount();
                    }

                    tollRoad.addCustomer(customer);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return tollRoad;
    }

    /*
        this method is a seperate method that takes toll road as its argument, it is designed to interact with tollroad,
        and update its state.
        simulates transactions by reading data from file
     */
    public static void simulateFromFile(TollRoad road) {
        try (BufferedReader br = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            // same method was used here as the last method as the format of both the txt files was all on one line
            while ((line = br.readLine()) != null) {
                String[] transactions = line.split("\\$"); // split transactions using '$'

                for (String transactionData : transactions) {
                    String[] parts = transactionData.split(","); // split transaction data using ','
                    // this area of code follows the same patten and performs differently based on index 0
                    // if index 0 is addfunds then this try catch block will run
                    // many exceptions are used based on the customers problem it will depend on what the file says
                    if (parts[0].equals("addFunds")) {
                        String regNum = parts[1];
                        int amount = Integer.parseInt(parts[2].replaceAll("[^0-9]", ""));//keep digits
                        try {
                            road.findCustomer(regNum).addFunds(amount);
                            System.out.println(regNum + ": " + amount + " added successfully");
                        } catch (CustomerNotFoundException e) {
                            System.out.println(regNum + ": addFunds failed. CustomerAccount does not exist");
                        }
                        // if it is make trip this will run
                    } else if (parts[0].equals("makeTrip")) {
                        String regNum = parts[1];
                        try {
                            road.chargeCustomer(regNum);
                            System.out.println(regNum + ": Trip completed successfully");
                        } catch (CustomerNotFoundException e) {
                            System.out.println(regNum + ": makeTrip failed. CustomerAccount does not exist");
                        } catch (InsufficientAccountBalanceException e) {
                            System.out.println(regNum + ": makeTrip failed. Insufficient funds");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* this main method was used to display the state of the customer accounts before simulation and show the accounts
       after the simulation was complete, this was just a piece of testing evidence to show my understanding of the
       assignment
     */
    /*
        public static void main(String[] args) {
        // Initialize the TollRoad from the file
        TollRoad road = initialiseTollRoadFromFile();

        System.out.println("\nCustomer Accounts after Initialization:");
        System.out.println(road);

        simulateFromFile(road);

        System.out.println("\nCustomer Accounts after Simulation:");
        System.out.println(road);

        int moneyMade = road.getMoneyMade();
        System.out.println("\nTOTAL MONEY MADE DURING SIMULATION: " + moneyMade);
    }
    */

    // this is the main method that should be used when running
    public static void main(String[] args) {
        TollRoad road = initialiseTollRoadFromFile();
        simulateFromFile(road);
        int moneyMade = road.getMoneyMade();
        System.out.println("\nTOTAL MONEY MADE DURING SIMULATION: " + moneyMade);
    }
}








