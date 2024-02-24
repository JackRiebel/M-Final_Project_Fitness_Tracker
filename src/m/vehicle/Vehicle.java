package m.vehicle;


public abstract class Vehicle {
    protected String model;
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

    public Vehicle(String model, String make, long modelYear, double price, VehicleColor color,
                   FuelType fuelType, double mileage, double mass, int cylinders,
                   double gasTankCapacity, StartMechanism startType) {
        this.model = model;
        this.make = make;
        this.modelYear = modelYear;
        this.price = price;
        this.color = color;
        this.fuelType = fuelType;
        this.mileage = mileage;
        this.mass = mass;
        this.cylinders = cylinders;
        this.gasTankCapacity = gasTankCapacity;
        this.startType = startType;
    }

	public String toCsvString() {
        return model + "," + make + "," + modelYear + "," + price + "," + color + ","
               + fuelType + "," + mileage + "," + mass + "," + cylinders + ","
               + gasTankCapacity + "," + startType;
    }
	
	@Override
	public String toString() {
	    return "Vehicle:" +
	            "type:'" + this.getClass().getSimpleName() + '\'' +
	            ", model:'" + model + '\'' +
	            ", make:'" + make + '\'' +
	            ", modelYear:" + modelYear +
	            ", price:" + price +
	            ", color:" + color +
	            ", fuelType:" + fuelType +
	            ", mileage:" + mileage +
	            ", mass:" + mass +
	            ", cylinders:" + cylinders +
	            ", gasTankCapacity:" + gasTankCapacity +
	            ", startType:" + startType;
	}

}
