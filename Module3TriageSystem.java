import java.util.*;

/**
 * Hospital Emergency Room Triage System
 * Module 3: Emergency Queue and Doctor Assignment
 *
 * Educational console demonstration only.
 * Priority must be set/confirmed by authorized clinical staff.
 */
public class Module3TriageSystem {

    // Smaller number means higher queue priority.
    // 1 = Critical, 2 = High, 3 = Medium, 4 = Low.
    static class Patient {
        String id;
        String name;
        int priority;
        String department;
        long arrivalOrder;
        String status = "Waiting";
        String assignedDoctor = "Not assigned";

        Patient(String id, String name, int priority,
                String department, long arrivalOrder) {
            this.id = id;
            this.name = name;
            this.priority = priority;
            this.department = department;
            this.arrivalOrder = arrivalOrder;
        }

        String priorityName() {
            switch (priority) {
                case 1: return "Critical";
                case 2: return "High";
                case 3: return "Medium";
                case 4: return "Low";
                default: return "Unknown";
            }
        }
    }

    static class Doctor {
        String id;
        String name;
        String department;
        boolean available = true;

        Doctor(String id, String name, String department) {
            this.id = id;
            this.name = name;
            this.department = department;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Patient> patients = new ArrayList<>();
    private static final List<Doctor> doctors = new ArrayList<>();
    private static long nextArrivalOrder = 1;

    public static void main(String[] args) {
        // Example doctors for testing
        doctors.add(new Doctor("D101", "Dr. Ravi", "General"));
        doctors.add(new Doctor("D102", "Dr. Anitha", "Cardiology"));
        doctors.add(new Doctor("D103", "Dr. Kumar", "Orthopedics"));

        int choice;
        do {
            System.out.println("\n=== HOSPITAL ER TRIAGE SYSTEM ===");
            System.out.println("MODULE 3: EMERGENCY QUEUE AND DOCTOR ASSIGNMENT");
            System.out.println("1. Add patient to queue");
            System.out.println("2. Add doctor");
            System.out.println("3. View waiting queue");
            System.out.println("4. Assign doctor to highest-priority matching patient");
            System.out.println("5. View doctors");
            System.out.println("6. Exit");
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    addDoctor();
                    break;
                case 3:
                    displayQueue();
                    break;
                case 4:
                    assignDoctor();
                    break;
                case 5:
                    displayDoctors();
                    break;
                case 6:
                    System.out.println("Exiting Module 3. Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 to 6.");
            }
        } while (choice != 6);
        scanner.close();
    }

    private static void addPatient() {
        System.out.print("Enter patient ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty() || findPatient(id) != null) {
            System.out.println("Patient ID is empty or already exists.");
            return;
        }

        System.out.print("Enter patient name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Patient name cannot be empty.");
            return;
        }

        System.out.println("Priority (confirmed by authorized staff):");
        System.out.println("1. Critical  2. High  3. Medium  4. Low");
        int priority = readInt("Enter priority (1-4): ");
        if (priority < 1 || priority > 4) {
            System.out.println("Invalid priority. Patient was not added.");
            return;
        }

        System.out.print("Enter required department (e.g., General): ");
        String department = scanner.nextLine().trim();
        if (department.isEmpty()) {
            System.out.println("Department cannot be empty.");
            return;
        }

        Patient patient = new Patient(
            id, name, priority, department, nextArrivalOrder++
        );
        patients.add(patient);
        sortQueue();
        System.out.println("Patient added to the waiting queue successfully.");
    }

    private static void addDoctor() {
        System.out.print("Enter doctor ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty() || findDoctor(id) != null) {
            System.out.println("Doctor ID is empty or already exists.");
            return;
        }

        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter doctor's department: ");
        String department = scanner.nextLine().trim();

        if (name.isEmpty() || department.isEmpty()) {
            System.out.println("Name and department are required.");
            return;
        }

        doctors.add(new Doctor(id, name, department));
        System.out.println("Doctor added successfully.");
    }

    private static void displayQueue() {
        sortQueue();
        System.out.println("\n--- PATIENT QUEUE ---");
        boolean found = false;
        for (Patient p : patients) {
            if ("Waiting".equals(p.status)) {
                found = true;
                System.out.printf(
                    "ID: %s | Name: %s | Priority: %s | Department: %s | Status: %s%n",
                    p.id, p.name, p.priorityName(), p.department, p.status
                );
            }
        }
        if (!found) {
            System.out.println("No patients are waiting.");
        }
    }

    private static void assignDoctor() {
        sortQueue();
        Patient selectedPatient = null;
        Doctor selectedDoctor = null;

        // Queue is sorted by priority, then by arrival order.
        for (Patient p : patients) {
            if (!"Waiting".equals(p.status)) {
                continue;
            }
            for (Doctor d : doctors) {
                if (d.available &&
                    d.department.equalsIgnoreCase(p.department)) {
                    selectedPatient = p;
                    selectedDoctor = d;
                    break;
                }
            }
            if (selectedPatient != null) {
                break;
            }
        }

        if (selectedPatient == null) {
            System.out.println(
                "No waiting patient has a matching available doctor."
            );
            return;
        }

        selectedPatient.assignedDoctor =
            selectedDoctor.name + " (" + selectedDoctor.id + ")";
        selectedPatient.status = "Assigned";
        selectedDoctor.available = false;

        System.out.println("Doctor assigned successfully.");
        System.out.println("Patient: " + selectedPatient.name);
        System.out.println("Doctor: " + selectedPatient.assignedDoctor);
        System.out.println("Department: " + selectedPatient.department);
        System.out.println("Status: " + selectedPatient.status);
    }

    private static void displayDoctors() {
        System.out.println("\n--- DOCTOR LIST ---");
        if (doctors.isEmpty()) {
            System.out.println("No doctors registered.");
            return;
        }
        for (Doctor d : doctors) {
            System.out.printf(
                "ID: %s | Name: %s | Department: %s | Availability: %s%n",
                d.id, d.name, d.department,
                d.available ? "Available" : "Assigned"
            );
        }
    }

    private static void sortQueue() {
        patients.sort(
            Comparator.comparingInt((Patient p) -> p.priority)
                      .thenComparingLong(p -> p.arrivalOrder)
        );
    }

    private static Patient findPatient(String id) {
        for (Patient p : patients) {
            if (p.id.equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    private static Doctor findDoctor(String id) {
        for (Doctor d : doctors) {
            if (d.id.equalsIgnoreCase(id)) {
                return d;
            }
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
