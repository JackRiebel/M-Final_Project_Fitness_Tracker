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
            String line;
            while ((line = br.readLine()) != null) {
                String[] vehicleData = line.split(",");

                // Check the type of vehicle and create appropriate object
                Vehicle vehicle;
                switch (vehicleData[0].toLowerCase()) { // Assuming first column is vehicle type
                    case "truck":
                        vehicle = new Truck(/* pass parameters extracted from vehicleData */);
                        break;
                    case "car":
                        vehicle = new Car(/* pass parameters */);
                        break;
                    case "suv":
                        vehicle = new SUV(/* pass parameters */);
                        break;
                    case "motorbike":
                        vehicle = new MotorBike(/* pass parameters */);
                        break;
                    default:
                        continue; // Skip unknown vehicle types
                }

                vehicleList.add(vehicle);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
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

