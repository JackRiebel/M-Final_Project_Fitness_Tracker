package m.vehicle;

public class SUV extends Vehicle {
    public SUV(String model, String make, long modelYear, double price, VehicleColor color,
               FuelType fuelType, double mileage, double mass, int cylinders,
               double gasTankCapacity, StartMechanism startType) {
        super(model, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
    }
    // SUV-specific methods and overrides...
}

