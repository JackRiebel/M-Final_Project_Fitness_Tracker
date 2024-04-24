package user;

import java.text.DecimalFormat;

public abstract class AbstractUser {
	protected String userName;
	protected double weight;
	protected double height;
	protected int age;
	protected int pushupGoal;
	protected int situpGoal;
	protected int squatGoal;
	protected double runGoal;
	
	private static final DecimalFormat df = new DecimalFormat("#.##");

	public User(){
	}
	
	///Simple helper methods
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getPushupGoal() {
		return pushupGoal;
	}
	public void setPushupGoal(int pushupGoal) {
		this.pushupGoal = pushupGoal;
	}
	
	public int getSitupGoal() {
		return situpGoal;
	}
	
	public void setSitupGoal(int situpGoal) {
		this.situpGoal = situpGoal;
	}
	
	public int getSquatGoal() {
		return squatGoal;
	}
	
	public void setSquatGoal(int squatGoal) {
		this.squatGoal = squatGoal;
	}
	
	public double getRunGoal() {
		return runGoal;
	}
	
	public void setRunGoal(double runGoal) {
		this.runGoal = runGoal;
	}
	
	
	////Calculation/Conversion Methods
	
	public double showBMI()
	{
		double bmi = this.weight/(this.height*this.height);
		double val = Double.parseDouble(df.format(bmi));
		return val;
	
	}
	
	public void setHeightInchToCentimeter(double inches)
	{
		double centimeter = inches * 2.54;
		this.height = centimeter;
	}
	
	
	public void setHeightCentimeterToInch(double centimeter)
	{
		double inch = centimeter / 2.54;
		this.height = inch;
	}
	
	public void setWeightPoundtoKilogram(double pound)
	{
		double kilogram = pound / 2.2046226218;
		this.weight = kilogram;
	}
	
	public void setWeightKilogramtoPound(double kilogram)
	{
		double pound = kilogram / 0.45359237;
		this.weight = kilogram;
	}
	
	public String bmiRanking(double bmi)
	{
		if(bmi <= 18)
		{
			return "Your bmi is within in the underweight range";
		}
		
		else if(bmi >= 19 && bmi <= 24)
		{
			return "Your bmi is within the healthy/normal range";
		}
		
		else if(bmi >= 25 && bmi <=29)
		{
			return "Your bmi is within the overweight range";
		}
		else if(bmi >= 30 && bmi <= 39)
		{
			return "Your bmi is within the obese range";
		}
		else if(bmi >= 40 && bmi <= 56)
		{
			return "Your bmi is within the extremely obese range";
		}
		else
			return "An invalid bmi has been entered. Please try again.";
			
	}
	
	
	

}
