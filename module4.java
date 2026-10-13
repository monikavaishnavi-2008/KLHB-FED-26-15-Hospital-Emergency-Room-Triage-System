
import java.util.Scanner;

class Treatment {
    int patientId;
    String patientName;
    String treatmentDetails;
    String treatmentStatus;

    Treatment(int id, String name) {
        patientId = id;
        patientName = name;
        treatmentDetails = "Not assigned";
        treatmentStatus = "Waiting";
    }

    void updateTreatment(String details, String status) {
        treatmentDetails = details;
        treatmentStatus = status;
    }

    void displayTreatment() {
        System.out.println("\nPatient ID: " + patientId);
        System.out.println("Patient Name: " + patientName);
        System.out.println("Treatment Details: " + treatmentDetails);
        System.out.println("Treatment Status: " + treatmentStatus);
    }
}

public class Module4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Treatment[] patients = new Treatment[50];
        int count = 0;

        int totalBeds = 20;
        int occupiedBeds = 0;

        int choice;

        do {
            System.out.println("\n--- Module 4: Treatment and Resource Management ---");
            System.out.println("1. Add Patient for Treatment");
            System.out.println("2. Update Treatment Status");
            System.out.println("3. View Patient Treatment");
            System.out.println("4. Admit Patient to Bed");
            System.out.println("5. Discharge Patient from Bed");
            System.out.println("6. Check Bed Availability");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    if (count < patients.length) {
                        System.out.print("Enter Patient ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Patient Name: ");
                        String name = sc.nextLine();

                        patients[count] = new Treatment(id, name);
                        count++;

                        System.out.println("Patient added successfully.");
                    } else {
                        System.out.println("Patient storage is full.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Patient ID: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    Treatment updatePatient = findPatient(patients, count, updateId);

                    if (updatePatient != null) {
                        System.out.print("Enter Treatment Details: ");
                        String details = sc.nextLine();

                        System.out.print("Enter Treatment Status (Waiting/In Progress/Completed): ");
                        String status = sc.nextLine();

                        updatePatient.updateTreatment(details, status);
                        System.out.println("Treatment updated successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Patient ID: ");
                    int viewId = sc.nextInt();

                    Treatment viewPatient = findPatient(patients, count, viewId);

                    if (viewPatient != null) {
                        viewPatient.displayTreatment();
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case 4:
                    if (occupiedBeds < totalBeds) {
                        occupiedBeds++;
                        System.out.println("Patient admitted to a bed successfully.");
                        System.out.println("Bed Number: " + occupiedBeds);
                    } else {
                        System.out.println("No beds available.");
                    }
                    break;

                case 5:
                    if (occupiedBeds > 0) {
                        occupiedBeds--;
                        System.out.println("Patient discharged successfully.");
                        System.out.println("Bed released.");
                    } else {
                        System.out.println("No occupied beds available.");
                    }
                    break;

                case 6:
                    System.out.println("Total Beds: " + totalBeds);
                    System.out.println("Occupied Beds: " + occupiedBeds);
                    System.out.println("Available Beds: " + (totalBeds - occupiedBeds));
                    break;

                case 7:
                    System.out.println("Exiting Module 4.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 7);

        sc.close();
    }

    static Treatment findPatient(Treatment[] patients, int count, int id) {
        for (int i = 0; i < count; i++) {
            if (patients[i].patientId == id) {
                return patients[i];
            }
        }
        return null;
    }
}

