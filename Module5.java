
import java.util.Scanner;

class Patient {
    int id;
    String name;
    int age;
    String symptoms;
    int priority;
    String doctor;
    String treatment;
    String status;
    boolean admitted;

    Patient(int id, String name, int age, String symptoms, int priority) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.symptoms = symptoms;
        this.priority = priority;
        this.doctor = "Not Assigned";
        this.treatment = "Not Started";
        this.status = "Waiting";
        this.admitted = false;
    }

    void display() {
        System.out.println("\nPatient ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Symptoms: " + symptoms);
        System.out.println("Priority: " + getPriorityName());
        System.out.println("Doctor: " + doctor);
        System.out.println("Treatment: " + treatment);
        System.out.println("Status: " + status);
    }

    String getPriorityName() {
        switch (priority) {
            case 1:
                return "Critical";
            case 2:
                return "High";
            case 3:
                return "Medium";
            default:
                return "Low";
        }
    }
}

public class Module5 {

    static Scanner sc = new Scanner(System.in);
    static Patient[] patients = new Patient[100];
    static int count = 0;

    static int totalBeds = 20;
    static int occupiedBeds = 0;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== HOSPITAL EMERGENCY ROOM TRIAGE SYSTEM =====");
            System.out.println("1. Patient Registration");
            System.out.println("2. Patient Triage");
            System.out.println("3. Doctor Assignment and Queue");
            System.out.println("4. Treatment and Bed Management");
            System.out.println("5. Hospital Reports");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    registerPatient();
                    break;

                case 2:
                    performTriage();
                    break;

                case 3:
                    manageQueue();
                    break;

                case 4:
                    manageTreatment();
                    break;

                case 5:
                    generateReport();
                    break;

                case 6:
                    System.out.println("Thank you for using the system.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);

        sc.close();
    }

    // MODULE 1: Patient Registration

    static void registerPatient() {

        if (count == patients.length) {
            System.out.println("Patient storage is full.");
            return;
        }

        System.out.print("Enter Patient ID: ");
        int id = readInt();

        if (findPatient(id) != null) {
            System.out.println("Patient ID already exists.");
            return;
        }

        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = readInt();

        System.out.print("Enter Symptoms: ");
        String symptoms = sc.nextLine();

        System.out.println("Select initial priority:");
        System.out.println("1. Critical");
        System.out.println("2. High");
        System.out.println("3. Medium");
        System.out.println("4. Low");
        System.out.print("Enter priority: ");

        int priority = readInt();

        if (age < 0 || age > 130 ||
                priority < 1 || priority > 4) {
            System.out.println("Invalid age or priority.");
            return;
        }

        patients[count] =
                new Patient(id, name, age, symptoms, priority);

        count++;

        System.out.println("Patient registered successfully.");

        if (priority == 1) {
            System.out.println("ALERT: Critical case requires immediate clinical assessment.");
        }
    }

    // MODULE 2: Patient Triage

    static void performTriage() {

        System.out.print("Enter Patient ID: ");
        int id = readInt();

        Patient p = findPatient(id);

        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("Current Priority: " + p.getPriorityName());

        System.out.println("1. Critical");
        System.out.println("2. High");
        System.out.println("3. Medium");
        System.out.println("4. Low");

        System.out.print("Enter updated priority: ");
        int priority = readInt();

        if (priority < 1 || priority > 4) {
            System.out.println("Invalid priority.");
            return;
        }

        p.priority = priority;

        System.out.println("Triage priority updated.");

        if (priority == 1) {
            System.out.println("ALERT: Immediate clinical assessment required.");
        }
    }

    // MODULE 3: Doctor Assignment and Queue Management

    static void manageQueue() {

        System.out.println("\n--- Patient Queue ---");

        if (count == 0) {
            System.out.println("No patients registered.");
            return;
        }

        // Display patients in priority order.
        for (int priority = 1; priority <= 4; priority++) {

            for (int i = 0; i < count; i++) {

                Patient p = patients[i];

                if (p.priority == priority &&
                        !p.status.equals("Completed")) {

                    System.out.println(
                            "ID: " + p.id +
                            " | Name: " + p.name +
                            " | Priority: " + p.getPriorityName() +
                            " | Status: " + p.status);
                }
            }
        }

        System.out.print("\nEnter Patient ID for doctor assignment: ");
        int id = readInt();

        Patient p = findPatient(id);

        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter Doctor Name: ");
        p.doctor = sc.nextLine();

        System.out.println("Doctor assigned successfully.");
    }

    // MODULE 4: Treatment and Bed Management

    static void manageTreatment() {

        System.out.println("\n1. Update Treatment");
        System.out.println("2. Admit Patient");
        System.out.println("3. Discharge Patient");
        System.out.println("4. Check Bed Availability");

        System.out.print("Enter choice: ");
        int choice = readInt();

        if (choice == 4) {
            System.out.println("Total Beds: " + totalBeds);
            System.out.println("Occupied Beds: " + occupiedBeds);
            System.out.println("Available Beds: "
                    + (totalBeds - occupiedBeds));
            return;
        }

        System.out.print("Enter Patient ID: ");
        int id = readInt();

        Patient p = findPatient(id);

        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        switch (choice) {

            case 1:
                System.out.print("Enter Treatment Details: ");
                p.treatment = sc.nextLine();

                System.out.println("1. Waiting");
                System.out.println("2. In Progress");
                System.out.println("3. Completed");

                System.out.print("Enter Treatment Status: ");
                int statusChoice = readInt();

                if (statusChoice == 1) {
                    p.status = "Waiting";
                } else if (statusChoice == 2) {
                    p.status = "In Progress";
                } else if (statusChoice == 3) {
                    p.status = "Completed";
                } else {
                    System.out.println("Invalid status.");
                    return;
                }

                System.out.println("Treatment updated successfully.");
                break;

            case 2:
                if (p.admitted) {
                    System.out.println("Patient already has a bed.");
                } else if (occupiedBeds < totalBeds) {
                    p.admitted = true;
                    occupiedBeds++;
                    System.out.println("Patient admitted successfully.");
                } else {
                    System.out.println("No beds available.");
                }
                break;

            case 3:
                if (p.admitted) {
                    p.admitted = false;
                    occupiedBeds--;
                    System.out.println("Patient discharged from bed.");
                } else {
                    System.out.println("Patient has no assigned bed.");
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    // MODULE 5: Reports and Hospital Administration

    static void generateReport() {

        System.out.println("\n--- Hospital Emergency Report ---");

        int critical = 0;
        int waiting = 0;
        int inProgress = 0;
        int completed = 0;

        System.out.println("Total Registered Patients: " + count);

        for (int i = 0; i < count; i++) {

            Patient p = patients[i];

            if (p.priority == 1) {
                critical++;
            }

            if (p.status.equals("Waiting")) {
                waiting++;
            } else if (p.status.equals("In Progress")) {
                inProgress++;
            } else if (p.status.equals("Completed")) {
                completed++;
            }
        }

        System.out.println("Critical Patients: " + critical);
        System.out.println("Waiting Patients: " + waiting);
        System.out.println("Treatment In Progress: " + inProgress);
        System.out.println("Completed Treatments: " + completed);

        System.out.println("Total Beds: " + totalBeds);
        System.out.println("Occupied Beds: " + occupiedBeds);
        System.out.println("Available Beds: "
                + (totalBeds - occupiedBeds));

        System.out.println("\n--- Patient Records ---");

        for (int i = 0; i < count; i++) {
            patients[i].display();
        }
    }

    // Search for a patient using patient ID.

    static Patient findPatient(int id) {

        for (int i = 0; i < count; i++) {

            if (patients[i].id == id) {
                return patients[i];
            }
        }

        return null;
    }

    // Read an integer safely.

    static int readInt() {

        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }

        int value = sc.nextInt();
        sc.nextLine();

        return value;
    }
}
