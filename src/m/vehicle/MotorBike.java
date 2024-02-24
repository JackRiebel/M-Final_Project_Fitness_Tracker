package m.vehicle;

public class MotorBike extends Vehicle {
    public MotorBike(String model, String make, long modelYear, double price, VehicleColor color,
                     FuelType fuelType, double mileage, double mass, int cylinders,
                     double gasTankCapacity, StartMechanism startType) {
        super(model, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
    }
    // MotorBike-specific methods and overrides...
}

