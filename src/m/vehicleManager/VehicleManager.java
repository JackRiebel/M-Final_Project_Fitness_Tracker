package m.vehicleManager;

import m.vehicle.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class VehicleManager {
    private String vehicleFilePath = "src/vehicleList.csv";
    private ArrayList<Vehicle> vehicleList = new ArrayList<>();

public boolean initializeStock() {
    try (BufferedReader br = new BufferedReader(new FileReader(vehicleFilePath))) {
        String line = br.readLine(); // Read the header line to skip it
        while ((line = br.readLine()) != null) {
            // Debug: Print the line read from the CSV
            //System.out.println("CSV Line: " + line);

            // Split line with comma as it's the delimiter in the CSV
            String[] vehicleData = line.split(",");
            if (vehicleData.length < 12) {
                // Debug: Print a warning when skipping a line
                //System.out.println("Skipping line due to insufficient data fields.");
                continue;
            }

            String type = vehicleData[0];
            String model = vehicleData[1];
            String make = vehicleData[2];
            long modelYear = Long.parseLong(vehicleData[3].trim());
            double price = Double.parseDouble(vehicleData[4].trim());
            VehicleColor color = VehicleColor.valueOf(vehicleData[5].trim());
            FuelType fuelType = FuelType.valueOf(vehicleData[6].trim());
            double mileage = Double.parseDouble(vehicleData[7].trim());
            double mass = Double.parseDouble(vehicleData[8].trim());
            int cylinders = Integer.parseInt(vehicleData[9].trim());
            double gasTankCapacity = Double.parseDouble(vehicleData[10].trim());
            StartMechanism startType = StartMechanism.valueOf(vehicleData[11].trim());

            Vehicle vehicle = null;
            switch (type.toLowerCase()) {
                case "truck":
                    vehicle = new Truck(model, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
                    break;
                case "motorbike":
                    vehicle = new MotorBike(model, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
                    break;
                case "car":
                    vehicle = new Car(model, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
                    break;
                case "suv":
                    vehicle = new SUV(model, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
                    break;
            }

            if (vehicle != null) {
                vehicleList.add(vehicle);
                // Debug: Print a message when a vehicle is added
                //System.out.println("Adding vehicle to list: " + vehicle);
            } 
        }
        return true;
    } catch (IOException e) {
        System.err.println("Error reading the file: " + e.getMessage());
        return false;
    } catch (NumberFormatException e) {
        System.err.println("Error parsing numeric data: " + e.getMessage());
        return false;
    } catch (IllegalArgumentException e) {
        System.err.println("Error parsing enum data: " + e.getMessage());
        return false;
    }
}

    
public void displayAllTruckInformation() {
    boolean found = false;
    for (Vehicle v : vehicleList) {
        if (v instanceof Truck) {
           displayVehicleInformation(v);
           found = true;
        }
    }
    if (!found) {
        System.out.println("No trucks found in the inventory.");
    }
}

public void displayAllSUVInformation() {
    boolean found = false;
    for (Vehicle v : vehicleList) {
        if (v instanceof SUV) {
            displayVehicleInformation(v);
            found = true;
        }
    }
    if (!found) {
        System.out.println("No SUVs found in the inventory.");
    }
}

public void displayAllMotorBikeInformation() {
    boolean found = false;
    for (Vehicle v : vehicleList) {
        if (v instanceof MotorBike) {
            displayVehicleInformation(v);
            found = true;
        }
    }
    if (!found) {
        System.out.println("No motorbikes found in the inventory.");
    }
}

public void displayVehicleInformation(Vehicle v) {
    if (vehicleList.contains(v)) {
    // Need toString method
        System.out.println(v.toString());
    } else {
        System.out.println("Vehicle not found in the inventory.");
    }
}

public void displayAllVehicleInformation() {
    if (vehicleList.isEmpty()) {
        System.out.println("The vehicle list is empty.");
    } else {
        for (Vehicle v : vehicleList) {
            displayVehicleInformation(v);
        }
    }
}

public boolean removeVehicle(Vehicle vehicle) {
    return vehicleList.remove(vehicle);
}

public boolean addVehicle(Vehicle vehicle) {
    return vehicleList.add(vehicle);
}

private boolean isVehicleType(Vehicle v, Class<?> clazz) {
    return clazz.isInstance(v);
}

public int getNumberOfVehiclesByType(Class<?> clazz) {
    int count = 0;
    for (Vehicle v : vehicleList) {
        if (isVehicleType(v, clazz)) {
            count++;
        }
    }
    return count;
}

public boolean saveVehicleList() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(vehicleFilePath))) {
        for (Vehicle v : vehicleList) {
        	bw.write(v.toCsvString()); // Assuming a method toCsvString() that converts vehicle data to CSV format
            bw.newLine();
        }
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

}

