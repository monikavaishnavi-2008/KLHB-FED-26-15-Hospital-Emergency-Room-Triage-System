import java.util.*;
import java.util.stream.*;

class Patient {
    String name;
    int severity;

    Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }
}

public class HospitalTriage {
    public static void main(String[] args) {

        List<Patient> patients = new ArrayList<>();

        patients.add(new Patient("Ravi", 3));
        patients.add(new Patient("Sita", 5));
        patients.add(new Patient("Ram", 2));
        patients.add(new Patient("Priya", 4));

        System.out.println("All Patients:");
        patients.forEach(p ->
            System.out.println(p.name + " - Severity: " + p.severity));

        System.out.println("\nHigh Priority Patients:");
        patients.stream()
            .filter(p -> p.severity >= 4)
            .forEach(p -> System.out.println(p.name));

        System.out.println("\nSorted Patients:");
        patients.stream()
            .sorted((p1, p2) ->
                Integer.compare(p2.severity, p1.severity))
            .forEach(p ->
                System.out.println(p.name + " - " + p.severity));
    }
}