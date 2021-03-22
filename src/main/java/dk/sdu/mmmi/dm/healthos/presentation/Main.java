package dk.sdu.mmmi.dm.healthos.presentation;

import dk.sdu.mmmi.dm.healthos.domain.*;
import dk.sdu.mmmi.dm.healthos.persistance.PersistanceHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Oliver Nordestgaard | olnor18
 */
public class Main {

    public static void main(String[] args) {

        //Main program
        System.out.println(
            "------------------------------------------\n"
            + "WELCOME TO HealthOS\n"
            + "Please input your command or type \"help\"\n"
            + "------------------------------------------\n"
        );
        boolean running = true;
        IPersistanceHandler persistanceHandler = PersistanceHandler.getInstance();
        try(Scanner s = new Scanner(System.in)){
            while(running) {
                switch(s.nextLine().toLowerCase()) {
                    case "getemployees":
                        System.out.println(persistanceHandler.getEmployees());
                        break;
                    case "getemployee":
                        System.out.println("What is the employee ID?");
                        System.out.println(persistanceHandler.getEmployee(Integer.parseInt(s.nextLine())));
                        break;
                    case "getpatients":
                        for (Patient p : persistanceHandler.getPatients()) {
                            System.out.println(p.toString());
                        }
                        break;
                    case "getpatient":
                        System.out.println("What is the patient ID?");
                        System.out.println(persistanceHandler.getPatient(Integer.parseInt(s.nextLine())));
                        break;
                    case "getbeds":
                        for (Bed b: persistanceHandler.getBeds()) {
                            System.out.println(b.toString());
                        }
                        break;
                    case "getbed":
                        System.out.println("What is the bed ID?");
                        System.out.println(persistanceHandler.getBed(Integer.parseInt(s.nextLine())));
                        break;
                    case "createbed":
                        System.out.println("What is the bed number?");
                        System.out.println(persistanceHandler.createBed(new Bed(s.nextLine())));
                        break;
                    case "getadmissions":
                        for (Admission ad : persistanceHandler.getAdmissions()) {
                            System.out.println(ad.toString());
                        }
                        break;
                    case "getadmission":
                        System.out.println("What is the admission ID?");
                        System.out.println(persistanceHandler.getAdmission(Integer.parseInt(s.nextLine())));
                        break;
                    case "exit":
                        running = false;
                        break;
                    case "createemployee":
                        System.out.println("Name, phone number, position_id, department_id, room_id");
                        System.out.println(persistanceHandler.createEmployee(new Employee(s.nextLine(), Integer.parseInt(s.nextLine()),
                                Integer.parseInt(s.nextLine()), Integer.parseInt(s.nextLine()), Integer.parseInt(s.nextLine()))));
                        break;
                    case "createadmission":
                        System.out.println("patient_id, room_id, bed_id, assigned_employee_id");
                        System.out.println(persistanceHandler.createAdmission(new Admission(Integer.parseInt(s.nextLine()),
                                Integer.parseInt(s.nextLine()), Integer.parseInt(s.nextLine()), Integer.parseInt(s.nextLine()))));
                        break;
                    case "deleteadmission":
                        System.out.println("Enter admission id");
                        System.out.println(persistanceHandler.deleteAdmission(Integer.parseInt(s.nextLine())));
                        break;
                    case "createpatient":
                        System.out.println("Name, Phone, CPR");
                        System.out.println(persistanceHandler.createPatient(new Patient(s.nextLine(), s.nextLine(), s.nextLine())));
                        break;
                    case "help":
                    default:
                        System.out.println(generateHelpString());
                        break;
                }
            }
        }
    }

    private static String generateHelpString() {
        return "Please write one of the following commands:\n"
                + "- getEmployees\n"
                + "- getEmployee\n"
                + "- getPatients\n"
                + "- getPatient\n"
                + "- getBeds\n"
                + "- getBed\n"
                + "- getAdmissions\n"
                + "- getAdmission\n"
                + "- createEmployee\n"
                + "- deleteAdmission\n"
                + "- createAdmission\n"
                + "- exit\n";
    }
}
