// car class inherits from the vehicle class
public class Car extends Vehicle {

    // private instance to hold the number of seats in the car
    private int numberOfSeats;

    // constructor to initialize registration, make, and numberOfSeats values for Car instances
    public Car(String registration, String make, int numberOfSeats) {
        super(registration, make); // superclass which calls constructor of vehicle
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    // override the calculateBasicTripCost method defined in the Vehicle class.
    @Override
    public int calculateBasicTripCost() {
        if (numberOfSeats < 6) {
            // return a basic trip cost of 500 if the car has less than 6 seats.
            return 500;
        } else {
            // return a basic trip cost of 600 if the car has 6 or more seats.
            return 600;
        }
    }

    // returns a string representation of the object class
    @Override
    public String toString() {
        return "Car: " + "registration = " + getRegistration() + ", make = " + getMake() +
                ", numberOfSeats = " + numberOfSeats;
    }

    /*

        public static void main(String[] args) {

            Car car = new Car("abc123", "toyota", 5);
            System.out.println(car);
        }

        // OUTPUT ---> Car: registration = abc123, make = toyota, numberOfSeats = 5

    */
}







