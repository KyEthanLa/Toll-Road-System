// this declares an abstract class indicating that it can be extended by other classes
public abstract class Vehicle {

    // private instance variables registration and make
    private String registration;
    private String make;

    // constructor to initialize registration and make values
    public Vehicle(String registration, String make) {
        this.registration = registration;
        this.make = make;
    }

    // getters allow access the private attributes
    public String getRegistration() {
        return registration;
    }

    public String getMake() {
        return make;
    }

    // subclasses extending the Vehicle class must provide their own implementation for this method
    public abstract int calculateBasicTripCost();
}
