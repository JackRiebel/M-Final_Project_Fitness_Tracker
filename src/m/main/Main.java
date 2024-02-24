package m.main;

import m.vehicleManager.*;

public class Main {
    public static void main(String[] args) {
        // Instantiate VehicleManager
        VehicleManager vehicleManager = new VehicleManager();

        // Read vehicle data from the vehicleList.csv file and initialize objects.
        boolean initializationSuccessful = vehicleManager.initializeStock();
        if (!initializationSuccessful) {
            System.out.println("Failed to initialize stock from the CSV file.");
            return; // Exit if initialization fails
        }

        // Display all vehicle information.
        System.out.println("Displaying all vehicle information:");
        vehicleManager.displayAllVehicleInformation();

        // Display all car information.
        //System.out.println("Displaying all car information:");
        //vehicleManager.displayAllCarInformation();

        // Display all truck information.
        System.out.println("Displaying all truck information:");
        vehicleManager.displayAllTruckInformation();

        // Display all SUV information.
        System.out.println("Displaying all SUV information:");
        vehicleManager.displayAllSUVInformation();

        // Display all motorbike information.
        System.out.println("Displaying all motorbike information:");
        vehicleManager.displayAllMotorBikeInformation();
        
        // Other operations that can be performed in main:

        // Other operations like add, remove, save vehicle list, etc. can be performed here as well
        // Vehicle newVehicle = ... // Create a new vehicle object
        // vehicleManager.addVehicle(newVehicle);

        // Example: Saving the vehicle list to CSV
        // vehicleManager.saveVehicleList();
    }
}
