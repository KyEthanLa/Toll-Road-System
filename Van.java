// this is a class named Van which extends the Vehicle class.
public class Van extends Vehicle {

    private int payload;

    public Van(String registration, String make, int payload) {
        super(registration, make);
        this.payload = payload;
    }

    public int getPayload() {
        return payload;
    }

    @Override
    public int calculateBasicTripCost() {
        if (payload <= 600) {
            // return a basic trip cost of 500 if the payload capacity is less than or equal to 600.
            return 500;
        } else if (payload <= 800) {
            // return a basic trip cost of 750 if the payload capacity is less than or equal to 800.
            return 750;
        } else {
            // return a basic trip cost of 1000 for payload capacities greater than 800.
            return 1000;
        }
    }

    @Override
    public String toString() {
        return "Van: registration = " + getRegistration() + ", make = " + getMake() + ", payload = " + payload;
    }

    /*

        public static void main(String[] args) {

            Van van = new Van("abc123", "ford", 700);
            System.out.println(van);
        }

        OUTPUT ---> Van: registration = abc123, make = ford, payload = 700

     */
}


