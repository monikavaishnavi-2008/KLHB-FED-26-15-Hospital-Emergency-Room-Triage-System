import java.util.Scanner;

public class TriageAssessment {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Patient details
        System.out.println("===== HOSPITAL EMERGENCY ROOM TRIAGE SYSTEM =====");

        System.out.print("Enter Patient ID: ");
        String patientId = sc.nextLine();

        System.out.print("Enter Patient Name: ");
        String patientName = sc.nextLine();

        // Vital signs
        System.out.print("Enter Temperature (°C): ");
        double temperature = sc.nextDouble();

        System.out.print("Enter Heart Rate (bpm): ");
        int heartRate = sc.nextInt();

        System.out.print("Enter Oxygen Level (SpO2 %): ");
        int oxygenLevel = sc.nextInt();

        System.out.print("Enter Systolic Blood Pressure: ");
        int bloodPressure = sc.nextInt();

        sc.nextLine(); // consume newline

        System.out.print("Enter Main Symptom: ");
        String symptom = sc.nextLine();

        // Triage assessment
        int priorityScore = 0;

        // Check oxygen level
        if (oxygenLevel < 90) {
            priorityScore += 3;
        } else if (oxygenLevel < 94) {
            priorityScore += 2;
        }

        // Check heart rate
        if (heartRate > 120 || heartRate < 50) {
            priorityScore += 2;
        }

        // Check blood pressure
        if (bloodPressure < 90 || bloodPressure > 180) {
            priorityScore += 2;
        }

        // Check temperature
        if (temperature >= 39) {
            priorityScore += 1;
        }

        // Determine priority
        String priority;
        String action;

        if (priorityScore >= 5) {
            priority = "CRITICAL";
            action = "Immediate medical attention required.";
        } 
        else if (priorityScore >= 3) {
            priority = "URGENT";
            action = "Treatment required soon.";
        } 
        else {
            priority = "NON-URGENT";
            action = "Patient can wait for treatment.";
        }

        // Display result
        System.out.println("\n========== TRIAGE RESULT ==========");
        System.out.println("Patient ID       : " + patientId);
        System.out.println("Patient Name     : " + patientName);
        System.out.println("Main Symptom     : " + symptom);
        System.out.println("Temperature      : " + temperature + " °C");
        System.out.println("Heart Rate       : " + heartRate + " bpm");
        System.out.println("Oxygen Level     : " + oxygenLevel + " %");
        System.out.println("Blood Pressure   : " + bloodPressure);
        System.out.println("Priority Score   : " + priorityScore);
        System.out.println("Priority Level   : " + priority);
        System.out.println("Action           : " + action);
        System.out.println("===================================");

        sc.close();
    }
}