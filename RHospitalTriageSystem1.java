import java.util.Scanner;

public class RHospitalTriageSystem1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input: Patient details
        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Patient Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Body Temperature (C): ");
        double temperature = sc.nextDouble();

        System.out.print("Enter Heart Rate (bpm): ");
        int heartRate = sc.nextInt();

        // Expressions and decision-making
        String priority;

        if (temperature >= 39.5 || temperature <= 35.0
                || heartRate >= 130 || heartRate <= 40) {
            priority = "Level 1 - Critical";
        } 
        else if (temperature >= 38.5 || heartRate >= 110
                || heartRate <= 50) {
            priority = "Level 2 - Urgent";
        } 
        else if (temperature >= 37.5 || heartRate >= 100) {
            priority = "Level 3 - Moderate";
        } 
        else {
            priority = "Level 4 - Low Priority";
        }

        // Output: Patient report
        System.out.println("\n--- HOSPITAL TRIAGE REPORT ---");
        System.out.println("Patient ID: " + patientId);
        System.out.println("Patient Name: " + name);
        System.out.println("Patient Age: " + age);
        System.out.println("Temperature: " + temperature + " C");
        System.out.println("Heart Rate: " + heartRate + " bpm");
        System.out.println("Triage Priority: " + priority);

        sc.close();
    }
}