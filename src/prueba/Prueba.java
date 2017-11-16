package prueba;

import hr.Country;
import hr.Department;
import hr.Employee;
import hr.ExcepcionHR;
import hr.HR;
import hr.Job;
import hr.JobHistory;
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
//    /**
//     * @param args the command line arguments
//     */
    public static void main(String[] args) throws ParseException {
        try {
            HR hr = new HR();
           modificarJobHistory();
            
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }
    

//        insertarEmployee();
//        modificarDepartment();
//        borrarCountry();
//        leerJobs();
//        insertarLocation();
//        borrarLocation();
////        borrarJobHistory();
//        leerJob();
//
//        Department d = new Department (1000, "nombreDepartamento",new Employee(100,null,null,null,null,null,null,0,0,null,null), new Location(1000,null,null,null,null,null));
//        insertDepartment(d);
//        selectDepartment(1000);
//        selectDepartments();
//        borrarDepartment(1000);
//        Job j = new Job("NOM","nombre",1,2);
//        insertJob(j);
//
//    }
//    
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
            System.out.println(ex.getMensajeErrorSistema());
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
            System.out.println(ex.getMensajeErrorSistema());
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
            System.out.println(ex.getMensajeErrorSistema());
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
    
    public static void borrarJobHistory() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = sdf.parse("2017-11-06");
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        try {
            HR hr = new HR();
            hr.borrarJobHistory(200, fechaSql);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
       }
    
}
    public static void borrarRegions() {
        try { 
            HR hr=new HR();
            hr.borrarRegion(1);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }
    
    public static void leerJob(){
        try {
            HR hr = new HR();
            hr.leerJob("IT_PROG");
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }
    public static void leerjobhistiry() throws ExcepcionHR, ParseException {

        HR hr = new HR();
        Date a;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = sdf.parse("2001-01-13");
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());

        System.out.println(hr.leerJobHistory(60, fechaSql));
    }

    public static void leerjobhistiryS() throws ExcepcionHR {
        HR hr = new HR();
        ArrayList<JobHistory> miArray = new ArrayList();
        miArray = hr.leerJobHistorys();
        for (JobHistory o : miArray) {

            System.out.println(o);

        }

    }
    public static void leerRegion(){
        try{
            HR hr=new HR();
            Region region = hr.leerRegion(1);
            System.out.println(region);
        }catch(ExcepcionHR ex) {
               System.out.println(ex);
        }
    }
    
    public static void LeerRegiones(){
        try{
            HR hr=new HR();
            ArrayList<Region> regiones=hr.leerRegions();
            
            for(Region e: regiones){
                System.out.println(e);
            }
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }
    
    public static void BorrarEmpleado(){
        try{
            HR hr=new HR();
            hr.borrarEmployee(101);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
        
    }
    
    public static void ActualizarRegion(){
        try {
            HR hr=new HR();
            Region rg=new Region(24, "kkk");
            hr.modificarRegion(24, rg);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }
    public static void leerEmployee(){
        try {
            HR hr=new HR();
            Employee e=hr.leerEmployee(101);
            System.out.println(e.toString());
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }
    
    public static void leerEmployees(){
        try {
            HR hr=new HR();
            ArrayList<Employee> lista=hr.leerEmployees();
            for (Employee e : lista) {
                Department d=e.getDepartment();
                System.out.println(e.toString());
            }
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }
    
    public static void modificarLocations(){
        Country c=new Country();
        c.setCountryId("US");
        Location l=new Location(6656,"hola","39500","","cantabria",c);
        try {
            HR hr=new HR();
            hr.modificarLocation(1700, l);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }
    
    public static void modificarJobs(){
        Job j=new Job("ADIOS","dd" , 0, 1000);
        try {
            HR hr=new HR();
            hr.modificarJob("HOLA", j);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }
    
    public static void borrarJobs(){
        try {
            HR hr=new HR();
            hr.borrarJob("ADIOS");
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    }
    public static void leerCountrys(){
        try {   
            HR hr = new HR();
            ArrayList lista = hr.leerCountrys();
            Iterator it = lista.iterator();
            System.out.println("Número de paises: " + lista.size());
            while(it.hasNext()){
                Country c = (Country) it.next();
                Region r = c.getRegion();
                System.out.print("ID del país: " + c.getCountryId() + "\n"
                        + "Nombre del país: " + c.getCountryName() + "\n"
                        + "ID de la región: " + r.getRegionId() + "\n");
            }
            
        } catch (ExcepcionHR ex) {
            System.out.println(ex.getMensajeErrorSistema());
            System.out.println(ex.getMensajeErrorUsuario());
        }
    }
    public static void modificarJobHistory() throws ParseException{
      Employee empleado= new Employee();
          empleado.setEmployeeId(104);
      Department department= new Department();
          department.setDepartmentId(90);
      Job trabajo= new Job();
          trabajo.setJobId("AD_VP");
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
          java.util.Date fechaUtil = sdf.parse("25/01/17");
          java.sql.Date parametroStarDate = new java.sql.Date(fechaUtil.getTime());

          java.util.Date fechasUtil1 = sdf.parse("18/01/17");
          java.sql.Date startDate = new java.sql.Date(fechasUtil1.getTime());

          java.util.Date fechaeUtil2 = sdf.parse("14/07/14");
          java.sql.Date endDate = new java.sql.Date(fechaeUtil2.getTime());
      JobHistory jh= new JobHistory(empleado,startDate,endDate,trabajo,department);


        try {
            HR hr = new HR();
            hr.modificarJobHistory(104,parametroStarDate,jh);
            
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
    
    }


   
    public static void insertarJobHistory() throws ParseException{
        try {
            HR hr=new HR();
            Employee empleado= new Employee();
              empleado.setEmployeeId(104);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
              java.util.Date fechaUtil = sdf.parse("25/01/17");
              java.sql.Date parametroStarDate = new java.sql.Date(fechaUtil.getTime());
              java.util.Date fechaeUtil2 = sdf.parse("24/07/17");
              java.sql.Date endDate = new java.sql.Date(fechaeUtil2.getTime());
            Job trabajo= new Job();
              trabajo.setJobId("AC_ACCOUNT");
            Department department= new Department();
              department.setDepartmentId(90);
            JobHistory jh= new JobHistory(empleado,parametroStarDate,endDate,trabajo,department);
              hr.insertarJobHistory(jh);
        } catch (ExcepcionHR ex) {
           System.out.println(ex.getMensajeErrorUsuario()+ ex.getMensajeErrorSistema());
        }
    
    }
    public static int insertDepartment(Department d){
        int i=-1;
        try {
            HR hr = new HR();
            i=hr.insertarDepartment(d);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
        return i;
    }
    
    public static int insertJob(Job j){
        int i=-1;
        try {
            HR hr = new HR();
            i=hr.insertarJob(j);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
        return i;
    }
    
        public static int borrarDepartment(int id){
        int i=-1;
        try {
            HR hr = new HR();
            i=hr.borrarDepartment(id);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
        return i;
    }
    
    public static Department selectDepartment(int id){
        Department d=null;
        try {
            HR hr = new HR();
            d=hr.leerDepartment(id);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
        return d;
    }
    
    public static ArrayList<Department> selectDepartments(){
        ArrayList<Department> a = new ArrayList<Department>();
        try {
            HR hr = new HR();
            a=hr.leerDepartments();
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }
        return a;

    }
}