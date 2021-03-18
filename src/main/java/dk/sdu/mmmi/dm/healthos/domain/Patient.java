package dk.sdu.mmmi.dm.healthos.domain;

/**
 *
 * @author Oliver Nordestgaard | olnor18
 */
public class Patient {
    private int id;
    private String name;
    private String phone;
    private String CPR;

    public Patient(int id, String name, String phone, String CPR) {
        this.id = id;
        this.name = name;
        this.CPR = CPR;
        this.phone = phone;
    }

    public Patient(String name, String phone, String CPR) {
        this.name = name;
        this.CPR = CPR;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Id: " + id + ". Name: " + name + ". Phone:  " + CPR;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }


    public String getCPR() {
        return this.CPR;
    }
}
