package m.vehicle;

public abstract class Vehicle {
    protected String brand;
    protected String make;
    protected long modelYear;
    protected double price;
    protected VehicleColor color;
    protected FuelType fuelType;
    protected double mileage;
    protected double mass;
    protected int cylinders;
    protected double gasTankCapacity;
    protected StartMechanism startType;

    // Constructors, getters, setters, and other methods...

    public String toCsvString() {
        return brand + "," + make + "," + modelYear + "," + price + "," + color + ","
               + fuelType + "," + mileage + "," + mass + "," + cylinders + ","
               + gasTankCapacity + "," + startType;
    }
}
