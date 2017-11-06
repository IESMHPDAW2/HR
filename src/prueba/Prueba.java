package prueba;

import hr.Country;
import hr.Department;
import hr.Employee;
import hr.ExcepcionHR;
import hr.HR;
import hr.Job;
import hr.Location;
import hr.Region;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
        leerJobs();
        insertarLocation();
        borrarLocation();


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

    public static void insertarLocation() {
        HR hr;
        try {
            hr = new HR();
            Country c = new Country();
            c.setCountryId("CU");
            Location l = new Location(1701, "el alto quijas", "39590", "quijas", "cantabria", c);
            hr.insertarLocation(l);
        } catch (ExcepcionHR ex) {
            System.out.println(ex.getMensajeErrorBD());
        }
    }

    public static void leerJobs() {
        HR hr;
        try {
            hr = new HR();

            ArrayList resultado = hr.leerJobs();
            Iterator it = resultado.iterator();
            System.out.println("Número de Trabajos: " + resultado.size());
            while (it.hasNext()) {
                Job j = (Job) it.next();
                System.out.print("---- TRABAJO: ----\n" + j.getJobId() + "\n"
                        + j.getJobTitle() + "\n"
                        + "Salario minimo: " + j.getMinSalary() + "\n"
                        + "Salario máximo: " + j.getMaxSalary() + "\n");
            }
        } catch (ExcepcionHR ex) {
            System.out.println(ex.getMensajeErrorBD());
        }
    }
    
    public static void insertarRegion(){
        try {
            HR hr = new HR();
            Region r = new Region();
            r.setRegionId(6);
            r.setRegionName("Torrelavega");
            hr.insertarRegion(r);
        } catch (ExcepcionHR ex) {
            System.out.println(ex.getMensajeErrorUsuario());
        }
       
    }
    
    public static void borrarLocation(){
        try {
            HR hr = new HR();
            hr.borrarLocation(1700);
        } catch (ExcepcionHR ex) {
            System.out.println(ex.getMensajeErrorUsuario());
        }
    }

    public static void modificarCountry(){
        try {
            HR h = new HR();          
            Region r = new Region();
            r.setRegionId(4);
                     
            Country c = new Country("DD","DAVID",r);
            h.modificarCountry("DV", c);

        } catch (ExcepcionHR ex) {
            System.out.println(ex.getMensajeErrorUsuario());
        }      
    }
    
    public static void leerLocation(){
        try { 
            HR hr = new HR();
            Location l = new Location();
            l = hr.leerLocation(1000);
            System.out.println(l.getLocationId()+" - "+l.getStreetAddress()+" - "+l.getPostalCode()
            +" - "+l.getCity()+" - "+l.getStateProvince()+" - "+l.getCountry().getCountryId());
        } catch (ExcepcionHR ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
    public static void leerLocations(){
        try {
            HR hr = new HR();
            ArrayList<Location> locations = new ArrayList<>();
            locations = hr.leerLocations();
            
            for(int i=0;i<locations.size();i++){
                System.out.println(locations.get(i).getLocationId()+" - "+locations.get(i).getStreetAddress()+
                " - "+locations.get(i).getPostalCode()+" - "+locations.get(i).getCity()+
                " - "+locations.get(i).getStateProvince()+" - "+locations.get(i).getCountry().getCountryId());
            }
        } catch (ExcepcionHR ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
