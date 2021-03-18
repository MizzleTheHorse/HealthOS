package dk.sdu.mmmi.dm.healthos.persistance;

import dk.sdu.mmmi.dm.healthos.domain.Employee;
import dk.sdu.mmmi.dm.healthos.domain.Patient;
import dk.sdu.mmmi.dm.healthos.domain.Admission;
import dk.sdu.mmmi.dm.healthos.domain.Bed;
import dk.sdu.mmmi.dm.healthos.domain.IPersistanceHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oliver Nordestgaard | olnor18
 */
public class PersistanceHandler implements IPersistanceHandler{
    private static PersistanceHandler instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "HealthDB";
    private String username = "postgres";
    private String password = "ida";
    private Connection connection = null;
    
    private PersistanceHandler(){
        initializePostgresqlDatabase();
    }

    public static PersistanceHandler getInstance(){
        if (instance == null) {
            instance = new PersistanceHandler();
        }
        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) System.exit(-1);
        }
    }

    @Override
    public List<Employee> getEmployees() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees");
            ResultSet sqlReturnValues = stmt.executeQuery();
            List<Employee> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()){
                returnValue.add(new Employee(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getInt(3), sqlReturnValues.getInt(4), sqlReturnValues.getInt(5), sqlReturnValues.getInt(6)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployee(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()){
               return null;
            }
            return new Employee(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getInt(3), sqlReturnValues.getInt(4), sqlReturnValues.getInt(5), sqlReturnValues.getInt(6));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }    
    }

    /*
    Implement all of the following. Beware that the model classes are as of yet not properly implemented
    */
    //Template for inserting:
    /*
    try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users (name,cpr) VALUES (?,?)");
            insertStatement.setString(1, "john doe");
            insertStatement.setString(2, "1111111111");
            insertStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

     */



    @Override
    public boolean createEmployee(Employee employee) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO employees (name, phone, position_id, department_id, room_id) VALUES (?,?,?,?,?)");
            insertStatement.setString(1, employee.getName());
            insertStatement.setInt(2, employee.getPhone());
            insertStatement.setInt(3, employee.getPosition_id());
            insertStatement.setInt(4, employee.getDepartment_id());
            insertStatement.setInt(5, employee.getRoom_id());
            insertStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("An error inserting into database occurred");
            return false;
        }

        return true;
    }

    @Override
    public List<Patient> getPatients() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM patients");
            ResultSet sqlReturnValues = stmt.executeQuery();
            List<Patient> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()){
                returnValue.add(new Patient(sqlReturnValues.getInt(1), sqlReturnValues.getString(2),
                        sqlReturnValues.getString(3), sqlReturnValues.getString(4)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Patient getPatient(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM patients WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()){
                return null;
            }
            return new Patient(sqlReturnValues.getInt(1), sqlReturnValues.getString(2),
                    sqlReturnValues.getString(3), sqlReturnValues.getString(4));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createPatient(Patient patient) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO patients (name, phone, cpr_number) VALUES (?,?,?)");
            insertStatement.setString(1, patient.getName());
            insertStatement.setString(2, patient.getPhone());
            insertStatement.setString(3, patient.getCPR());
            if (patient.getCPR().length() > 11) {
                System.out.println("CPR Too long, insertion aborted");
                return false;
            } else {
                insertStatement.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("An error inserting into database occurred");
            return false;
        }
        return true;
    }

    @Override
    public List<Bed> getBeds() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM beds");
            ResultSet sqlReturnValues = stmt.executeQuery();
            List<Bed> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()){
                returnValue.add(new Bed(sqlReturnValues.getString(1)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Bed getBed(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM beds WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()){
                return null;
            }
            return new Bed(sqlReturnValues.getInt(1), sqlReturnValues.getString(2));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createBed(Bed bed) {
        //make HealthOS support this action in the presentation layer too.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Admission> getAdmissions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Admission getAdmission(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean createAdmission(Admission admission) {
        //make HealthOS support this action in the presentation layer too.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAdmission(int id) {
        //make HealthOS support this action in the presentation layer too.
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
