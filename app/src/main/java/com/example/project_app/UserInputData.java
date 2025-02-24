package com.example.project_app;

public class UserInputData {
    private static int income;
    private static int savingsPercentage;
    private static int food;
    private static int transport;
    private static int household;
    private static int phoneInternet;
    private static int others;

    public static void setIncome(int value) { income = value; }
    public static int getIncome() { return income; }

    public static void setSavingsPercentage(int value) { savingsPercentage = value; }
    public static int getSavingsPercentage() { return savingsPercentage; }

    public static void setFood(int value) { food = value; }
    public static int getFood() { return food; }

    public static void setTransport(int value) { transport = value; }
    public static int getTransport() { return transport; }

    public static void setHousehold(int value) { household = value; }
    public static int getHousehold() { return household; }

    public static void setPhoneInternet(int value) { phoneInternet = value; }
    public static int getPhoneInternet() { return phoneInternet; }

    public static void setOthers(int value) { others = value; }
    public static int getOthers() { return others; }

    public static void reset() {
        income = 0;
        savingsPercentage = 0;
        food = 0;
        transport = 0;
        household = 0;
        phoneInternet = 0;
        others = 0;
    }
}
