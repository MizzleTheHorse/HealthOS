package dk.sdu.mmmi.dm.healthos.domain;

/**
 *
 * @author Oliver Nordestgaard | olnor18
 */
public class Admission {
    private int id;
    private int patient_id;
    private int bed_id;
    private int assigned_employee_id;
    private int room_id;

    public Admission(int id, int patient_id, int room_id, int bed_id, int assigned_employee_id) {
        this.id = id;
        this.patient_id = patient_id;
        this.room_id = room_id;
        this.bed_id = bed_id;
        this.assigned_employee_id = assigned_employee_id;
    }

    public Admission(int patient_id, int room_id, int bed_id, int assigned_employee_id) {
        this.patient_id = patient_id;
        this.room_id = room_id;
        this.bed_id = bed_id;
        this.assigned_employee_id = assigned_employee_id;
    }
    public int getId() {
        return id;
    }
    public int getPatient_id() {
        return patient_id;
    }
    public int getBed_id() {
        return bed_id;
    }
    public int getAssigned_employee_id() {
        return assigned_employee_id;
    }

    public int getRoomid() {
        return room_id;
    }

    @Override
    public String toString() {
        return id + ", " + patient_id + ", " + room_id + ", " + assigned_employee_id + ".";
    }

}
