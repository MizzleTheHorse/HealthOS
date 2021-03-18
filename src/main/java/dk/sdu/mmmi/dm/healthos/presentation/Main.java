package dk.sdu.mmmi.dm.healthos.presentation;

import dk.sdu.mmmi.dm.healthos.domain.Bed;
import dk.sdu.mmmi.dm.healthos.domain.Employee;
import dk.sdu.mmmi.dm.healthos.domain.IPersistanceHandler;
import dk.sdu.mmmi.dm.healthos.domain.Patient;
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
                    case "getadmissions":
                        System.out.println(persistanceHandler.getAdmissions());
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

                        /*
                        String[] info = null;
                        System.out.println("Name of the employee, phone number, position_id, department_id, room_id, separeted by comma no spaces");
                        info = s.nextLine().split(",");
                        if (info.length > 5) {
                            System.out.println("Invalid data insertion, try again without spacing.");
                            break;
                        }
                        System.out.println(persistanceHandler.createEmployee(new Employee(info[0], Integer.parseInt(info[1]),
                                Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]))));
                        */
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
                + "- exit\n"
                + "- exit\n"
                + "- exit\n";
    }
}
