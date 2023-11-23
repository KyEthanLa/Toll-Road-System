// this is a class named Truck which extends the Vehicle class.
public class Truck extends Vehicle {
    private int numTrailers;

    public Truck(String registration, String make, int numTrailers) {
        super(registration, make);
        this.numTrailers = numTrailers;
    }

    public int getNumTrailers() {
        return numTrailers;
    }

    @Override
    public int calculateBasicTripCost() {
        if (numTrailers == 1) {
            // return a basic trip cost of 1250 if the truck has 1 trailer.
            return 1250;
        } else {
            // return a basic trip cost of 1500 for trucks with more than 1 trailer.
            return 1500;
        }
    }

    @Override
    public String toString() {
        return "Truck: registration = " + getRegistration() + ", make = " + getMake() +
                ", numTrailers = " + numTrailers;
    }

    /*

        public static void main(String[] args) {

            Truck truck = new Truck("abc123", "volvo", 2);
            System.out.println(truck);
        }

        OUTPUT ---> Truck: registration = abc123, make = volvo, numTrailers = 2

     */
}




