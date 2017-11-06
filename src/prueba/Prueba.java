package prueba;

import hr.Department;
import hr.Employee;
import hr.ExcepcionHR;
import hr.HR;
import hr.Job;
import hr.Location;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Prueba {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        insertarEmployee();
//        modificarDepartment();
        borrarCountry();


    }
    
    public static void insertarEmployee() {
        Employee manager = new Employee();
        manager.setEmployeeId(100);
        Department department = new Department();
        department.setDepartmentId(69);
        Job job = new Job();
        job.setJobId("IT_PROG");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = null;
        try {
            fechaUtil = sdf.parse("2017-10-19");
        } catch (ParseException ex) {
           System.out.println(ex);
        }

        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        Employee e = new Employee(549,null,"Ruiz","fghgfhgf","696121212",fechaSql, job,4500,0,manager,department);
        try {
            HR hr = new HR();
            hr.insertarEmployee(e);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }
    
    public static void modificarDepartment() {
        Employee e = new Employee();
        e.setEmployeeId(114);
        Location l = new Location();
        l.setLocationId(1700);
        Department d = new Department(30,"Compras",e,l);
        try {
            HR hr = new HR();
            hr.modificarDepartment(30, d);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }

    public static void borrarCountry() {
        try {
            HR hr = new HR();
            int x = hr.borrarCountry("US");
            System.out.println("Registros eliminados: " + x);
        } catch (ExcepcionHR ex) {
            System.out.println(ex.getMensajeErrorBD());
            System.out.println(ex.getMensajeErrorUsuario());
        }
    }
}
