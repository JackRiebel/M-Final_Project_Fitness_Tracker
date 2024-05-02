package m.fitness.tracker;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Tracks fitness goals for multiple users, allowing for setting, updating,
 * and querying the status of physical activity goals.
 */
public class FitnessTracker {
    private Map<String, Map<Integer, Goal>> userGoals = new HashMap<>();

    /**
     * Sets a new fitness goal for a user.
     * 
     * @param userName the username of the user
     * @param goalType the type of goal (e.g., Pushups, Pullups, Squats, Run)
     * @param targetValue the target value to achieve
     * @param deadline the date by which the goal should be achieved
     * @return true if the goal is successfully set, false otherwise
     */
    public boolean setFitnessGoal(String userName, GoalType goalType, double targetValue, Date deadline) {
        if (!userGoals.containsKey(userName)) {
            userGoals.put(userName, new HashMap<>());
        }

        Map<Integer, Goal> goals = userGoals.get(userName);
        int nextGoalId = goals.size() + 1;

        if (!validateGoalInput(goalType, targetValue, deadline)) {
            return false;  // Validation failed
        }

        Goal goal = new Goal(nextGoalId, goalType, targetValue, deadline);
        goals.put(goal.getGoalId(), goal);
        return true;
    }

    /**
     * Validates the inputs for setting a goal.
     *
     * @param goalType the type of goal
     * @param targetValue the target value for the goal
     * @param deadline the deadline for the goal
     * @return true if the inputs are valid, false otherwise
     */
    private boolean validateGoalInput(GoalType goalType, double targetValue, Date deadline) {
        return targetValue > 0 && deadline.after(new Date());
    }
    
    /**
     * Sends a notification message to the console.
     *
     * @param message the message to be sent as a notification
     */
    private void sendNotification(String message) {
        // In a real application, this could be a call to an external notification service.
        // For this example, we'll simply print the message to the console.
        System.out.println("Notification: " + message);
    }
    
    /**
     * Saves the state of a goal to persistent storage.
     *
     * @param userName the username of the user
     * @param goal the goal object to save
     * @return true if the goal is successfully saved, false otherwise
     */
    private boolean saveGoal(String userName, Goal goal) {
        // This is a placeholder for actual persistence logic.
        // You might implement saving to a database or file system here.
        System.out.println("Saving goal: " + goal.getGoalId() + " for user: " + userName);
        return true; // Assume success for this example
    }

    /**
     * Updates the progress of a specific goal for a user and saves the updated goal.
     *
     * @param userName the username of the user
     * @param goalId the identifier of the goal
     * @param currentValue the current progress value
     * @return true if the update and save were successful, false otherwise
     */
    public boolean updateProgress(String userName, int goalId, double currentValue) {
        Goal goal = loadGoal(userName, goalId);
        if (goal == null) {
            return false; // Goal not found
        }

        goal.setCurrentValue(currentValue);
        if (currentValue >= goal.getTargetValue()) {
            goal.setStatus("Completed");
            sendNotification("Goal completed for user: " + userName);
        }

        return saveGoal(userName, goal);
    }
    
    /**
     * Calculates the progress percentage of a goal.
     *
     * @param targetValue the target value set for the goal
     * @param currentValue the current progress value towards the goal
     * @return the percentage of the goal completed
     */
    private double calculateProgressPercentage(double targetValue, double currentValue) {
        if (targetValue == 0) {
            return 0;  // Avoid division by zero
        }
        return (currentValue / targetValue) * 100;
    }
    
    /**
     * Retrieves a goal object based on user name and goal ID.
     *
     * @param userName the username of the user
     * @param goalId the ID of the goal to retrieve
     * @return the Goal object if found, or null if not found
     */
    private Goal loadGoal(String userName, int goalId) {
        if (userGoals.containsKey(userName) && userGoals.get(userName).containsKey(goalId)) {
            return userGoals.get(userName).get(goalId);
        }
        return null; // Return null if the user or goal ID does not exist
    }

    /**
     * Retrieves the status of a specific goal for a user.
     *
     * @param userName the username of the user
     * @param goalId the identifier of the goal
     * @return a descriptive string of the goal's status or a message if not found
     */
    public String getGoalStatus(String userName, int goalId) {
        Goal goal = loadGoal(userName, goalId);
        if (goal == null) {
            return "Goal not found";
        }

        double progressPercentage = calculateProgressPercentage(goal.getTargetValue(), goal.getCurrentValue());
        return String.format("Goal ID: %d, Type: %s, Progress: %.2f%%, Status: %s",
                             goalId, goal.getGoalType(), progressPercentage, goal.getStatus());
    }

    /**
     * Represents a fitness goal.
     */
    class Goal {
        private int goalId;
        private GoalType goalType;
        private double targetValue;
        private double currentValue;
        private Date deadline;
        private String status;

        public Goal(int goalId, GoalType goalType, double targetValue, Date deadline) {
            this.goalId = goalId;
            this.goalType = goalType;
            this.targetValue = targetValue;
            this.deadline = deadline;
            this.currentValue = 0;
            this.status = "Active";
        }

        // Getters
        public int getGoalId() { return goalId; }
        public GoalType getGoalType() { return goalType; }
        public double getTargetValue() { return targetValue; }
        public double getCurrentValue() { return currentValue; }
        public Date getDeadline() { return deadline; }
        public String getStatus() { return status; }

        // Setters
        public void setGoalId(int goalId) { this.goalId = goalId; }
        public void setGoalType(GoalType goalType) { this.goalType = goalType; }
        public void setTargetValue(double targetValue) {
            if (targetValue > 0) { // Basic validation to ensure target value is positive
                this.targetValue = targetValue;
            }
        }
        public void setCurrentValue(double currentValue) {
            if (currentValue >= 0) { // Basic validation to ensure current value is non-negative
                this.currentValue = currentValue;
            }
        }
        public void setDeadline(Date deadline) {
            if (deadline != null && deadline.after(new Date())) { // Ensure the deadline is in the future
                this.deadline = deadline;
            }
        }
        public void setStatus(String status) {
            if (status != null && !status.isEmpty()) { // Basic validation to ensure status is not null or empty
                this.status = status;
            }
        }
    }

    /**
     * Enum for types of fitness goals.
     */
    enum GoalType {
        PUSHUPS, PULLUPS, SQUATS, RUN
    }
}

