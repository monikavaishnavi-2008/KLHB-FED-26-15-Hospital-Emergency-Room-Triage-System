import java.util.Scanner;
class Patient {

    String patientId;

    String name;

    int age;

    String gender;

    String contact;

    String symptoms;

    String vitalSigns;

    String medicalHistory;

    String allergies;


    void displayDetails() {

        System.out.println("\n--- Patient Details ---");

        System.out.println("Patient ID: " + patientId);

        System.out.println("Name: " + name);

        System.out.println("Age: " + age);

        System.out.println("Gender: " + gender);

        System.out.println("Contact: " + contact);

        System.out.println("Symptoms: " + symptoms);

        System.out.println("Vital Signs: " + vitalSigns);

        System.out.println("Medical History: " + medicalHistory);

        System.out.println("Allergies: " + allergies);

    }

}


public class PatientRegistration {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Patient p = new Patient();


        System.out.println("HOSPITAL EMERGENCY ROOM TRIAGE SYSTEM");

        System.out.println("PATIENT REGISTRATION");


        System.out.print("Enter Patient ID: ");

        p.patientId = sc.nextLine();


        System.out.print("Enter Name: ");

        p.name = sc.nextLine();


        System.out.print("Enter Age: ");

        p.age = sc.nextInt();

        sc.nextLine();


        System.out.print("Enter Gender: ");

        p.gender = sc.nextLine();


        System.out.print("Enter Contact Number: ");

        p.contact = sc.nextLine();


        System.out.print("Enter Symptoms: ");

        p.symptoms = sc.nextLine();


        System.out.print("Enter Vital Signs: ");

        p.vitalSigns = sc.nextLine();


        System.out.print("Enter Medical History: ");

        p.medicalHistory = sc.nextLine();


        System.out.print("Enter Allergies: ");

        p.allergies = sc.nextLine();


        p.displayDetails();


        sc.close();

    }

}