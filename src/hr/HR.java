/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class HR {

    Connection conexion;
    
    public HR() throws ExcepcionHR {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
        } catch (ClassNotFoundException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(-1,ex.getMessage(),"Error general del sistema. Consulte con el administrador.",null);
            throw excepcionHR;
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),"Error general del sistema. Consulte con el administrador.",null);
            throw excepcionHR;
        }
    }
    
    /**
     * Inserta una region a la base de datos.
     * @author David Fernandez Garcia
     * @param region Contiene la region a insertar en la base de datos.
     * @return Cantidad de regiones que se han insertado en la base de datos.
     * @throws ExcepcionHR ExcepcionHR con toda la informacion del errror producido.
     */
    public int insertarRegion(Region region) throws ExcepcionHR{
        String dml = "";
        int registrosAfectados = 0;
        try {        
            dml = "insert into regions(region_id,region_name) values(?,?)";
            PreparedStatement sentencia = conexion.prepareStatement(dml);
            sentencia.setInt(1, region.getRegionId());
            sentencia.setString(2, region.getRegionName());
            
            registrosAfectados = sentencia.executeUpdate();

            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 1400:  
                    excepcionHR.setMensajeErrorUsuario("El identificador del pais no puede ser nulo");
                     break;
                case 1:     
                    excepcionHR.setMensajeErrorUsuario("El identificador del pais no puede repetirse.");
                    break;
                default:    
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador.");
                    break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    public int borrarRegion(int regionId) {
        return 0;
    }
    
    public int modificarRegion(int regionId, Region region) {
        return 0;
    }
    
    public Region leerRegion(int regionId) {
        return null;
    }
    
    public ArrayList<Region> leerRegions() {
        return null;
    }
    
    public int insertarCountry(Country country){
        return -1;
    }
    
    /**
     * Elimina un país de la base de datos
     * @author Ignacio Fontecha Hernández
     * @param countryId contiene el identificador del país a eliminar
     * @return cantidad de paises eliminados en la base de datos
     * @throws ExcepcionHR con toda la información acerca del error que se ha producido
     */
    public int borrarCountry (String countryId) throws ExcepcionHR{
        String llamada = "";
        int registrosAfectados=0;
        try {
            llamada = "call BORRAR_COUNTRY(?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            
            sentenciaLlamable.setString(1, countryId);
            registrosAfectados=sentenciaLlamable.executeUpdate();
            
            sentenciaLlamable.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),"Error general del sistema. Consulte con el administrador.",null);
            switch (ex.getErrorCode()) {
                case 2292:  excepcionHR.setMensajeErrorUsuario("No se puede borrar porque tiene localidades asociadas");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                            break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;    }
    
    /**
     * Modifica un pais de la base de datos
     * @author David Fernandez Garcia
     * @param countryId contiene el identificador del pais a modificar
     * @param country contiene la nueva informacion del pais a modificar
     * @return cantidad de paises modificados en la base de datos
     * @throws ExcepcionHR con toda la informacion del errror producido
     */
    public int modificarCountry(String countryId,Country country) throws ExcepcionHR{
        String llamada = "";
        int registrosAfectados = 0;
        try {
            llamada = "call modificarCountry(?,?,?,?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            
            sentenciaLlamable.setString(1, countryId);
            sentenciaLlamable.setString(2, country.getCountryId());
            sentenciaLlamable.setString(3, country.getCountryName());
            sentenciaLlamable.setInt(4, country.getRegion().getRegionId());
            
            registrosAfectados = sentenciaLlamable.executeUpdate();
            
            sentenciaLlamable.close();
            conexion.close();
            
        } catch (SQLException ex) {      
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,llamada);
            switch(ex.getErrorCode()){            
                case 2292: 
                        excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador, tiene localidades asociadas.");
                    break;
                case 2291:
                        excepcionHR.setMensajeErrorUsuario("La region no existe");
                    break;
                case 1407:
                        excepcionHR.setMensajeErrorUsuario("El identificador del pais no puede ser nulo");
                    break;
                case 1:
                        excepcionHR.setMensajeErrorUsuario("El identificador de pais no puede repetirse");
                    break;
                default:    
                        excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                    break;
            }         
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    public Country leerCountry(String countryId){
        return null;
    }
    
    public ArrayList<Country> leerCountrys(){
        return null;
    }
    
    /**
     * Inserta una localización enla base de datos
     *
     * @author Carlos Labrador Amieva
     * @param location contiene todos los datos de la localización a añadir.
     * @return cantidad de localizaciones insertadas en la base de datos
     * @throws ExcepcionHR con toda la información acerca del error que se ha
     * producido
     */
    public int insertarLocation(Location location) throws ExcepcionHR {
        String llamada = "";
        int registrosAfectados = 0;
        try {
            llamada = "INSERT INTO LOCATIONS(LOCATION_ID,STREET_ADDRESS,POSTAL_CODE,CITY,STATE_PROVINCE,COUNTRY_ID) VALUES (?,?,?,?,?,?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);

            sentenciaLlamable.setInt(1, location.getLocationId());
            sentenciaLlamable.setString(2, location.getStreetAddress());
            sentenciaLlamable.setString(3, location.getPostalCode());
            sentenciaLlamable.setString(4, location.getCity());
            sentenciaLlamable.setString(5, location.getStateProvince());
            sentenciaLlamable.setString(6, location.getCountry().getCountryId());
            registrosAfectados = sentenciaLlamable.executeUpdate();

            sentenciaLlamable.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), "Error general del sistema. Consulte con el administrador.", null);
            switch (ex.getErrorCode()) {
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("El pais seleccionado no existe");
                    break;
                case 1400:
                    excepcionHR.setMensajeErrorUsuario("Error: los siguientes datos son obligatorios:\nIdentificador de localidad.\nCiudad");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("Error: el código de localidad no puede repetirse");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    /**
     * Elimina una localidad de la base de datos
     * @author David Fernandez Garcia
     * @param locationId identificador de la localidad a eliminar de la base de datos.
     * @return Cantidad de localidades borradas en la base de datos
     * @throws ExcepcionHR con toda la informacion del errror producido.
     */
    public int borrarLocation(int locationId) throws ExcepcionHR{
        String dml = "";
        int registrosAfectados = 0;
        try {
            dml = "DELETE FROM LOCATIONS WHERE LOCATION_ID=?";    
            PreparedStatement sentencia= conexion.prepareStatement(dml);
            
            sentencia.setInt(1, locationId);          
            registrosAfectados = sentencia.executeUpdate();

            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 2292:  
                    excepcionHR.setMensajeErrorUsuario("La localidad a borrar, tiene departamentos asociados.");
                    break;
                default:    
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                    break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    public int modificarLocation (int locationId, Location location){
        return -1;
    }
    
    /**
     * Lee los datos de una localidad determinada
     * @param locationId Identificador de la localidad a buscar
     * @return Localidad con los datos obtenidos
     * @throws ExcepcionHR con toda la informacion del errror producido.
     */
    public Location leerLocation(int locationId) throws ExcepcionHR{
        Location location = new Location();
        location.setCountry(new Country());
        try {                
            String dql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID=?";
            PreparedStatement sentencia= conexion.prepareStatement(dql);
            sentencia.setInt(1, locationId);  
            sentencia.executeQuery();
            
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                location.setLocationId(resultado.getInt("LOCATION_ID"));
                location.setStreetAddress(resultado.getString("STREET_ADDRESS"));
                location.setPostalCode(resultado.getString("POSTAL_CODE"));
                location.setCity(resultado.getString("CITY"));
                location.setStateProvince(resultado.getString("STATE_PROVINCE"));
                location.getCountry().setCountryId(resultado.getString("COUNTRY_ID"));
            }
            
            resultado.close();          
            
            sentencia.close();
            conexion.close();   
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),"Error general del sistema. Consulte con el administrador.",null);
            throw excepcionHR;
        }  
        return location;
    }
    
    /**
     * Lee todas las localidades de la base de datos.
     * @return Una lista con todas las localidades de la base de datos
     * @throws ExcepcionHR con toda la informacion del errror producido.
     */
    public ArrayList<Location> leerLocations() throws ExcepcionHR{
        ArrayList<Location> locations = new ArrayList();
        try {      
            Statement sentencia = conexion.createStatement();
            String dql = "SELECT * FROM LOCATIONS";           
            ResultSet resultado = sentencia.executeQuery(dql); 
            while(resultado.next()){
                Location location = new Location();
                location.setCountry(new Country());
                location.setLocationId(resultado.getInt("LOCATION_ID"));
                location.setStreetAddress(resultado.getString("STREET_ADDRESS"));
                location.setPostalCode(resultado.getString("POSTAL_CODE"));
                location.setCity(resultado.getString("CITY"));
                location.setStateProvince(resultado.getString("STATE_PROVINCE"));
                location.getCountry().setCountryId(resultado.getString("COUNTRY_ID"));
                locations.add(location);
            }          
            
            resultado.close();                  
            sentencia.close();
            conexion.close();              
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),"Error general del sistema. Consulte con el administrador.",null);
            throw excepcionHR;
        }         
        return locations;
    }
    
    public int insertarDepartment(Department department){
        return -1;
    }
    
    public int borrarDepartment (int departmentId){
        return -1;
    }
    
    /**
     * Modifica un departamento de la base de datos
     * @author Ignacio Fontecha Hernández
     * @param departmentId contiene el identificador del departamento a modificar
     * @param department contiene la nuevos datos del departamento a modificar
     * @return cantidad de departamentos modificados en la base de datos
     * @throws ExcepcionHR con toda la información acerca del error que se ha producido
     */
    public int modificarDepartment (int departmentId, Department department) throws ExcepcionHR {
        int registrosAfectados = 0;
        String dml = "";
        try {
            Statement sentencia = conexion.createStatement();

            dml = "update DEPARTMENTS set "
                    + "DEPARTMENT_ID = " + department.getDepartmentId() + ","
                    + "DEPARTMENT_NAME = '" + department.getDepartmentName() + "',"
                    + "MANAGER_ID = " + department.getManager().getEmployeeId() + ","
                    + "LOCATION_ID = " + department.getLocation().getLocationId() 
                    + "where DEPARTMENT_ID = " + departmentId;
            registrosAfectados = sentencia.executeUpdate(dml);
            
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 2291:  excepcionHR.setMensajeErrorUsuario("La localidad elegida no existe o el jefe de departamento no es un empleado de la empresa");
                            break;
                case 1407:  excepcionHR.setMensajeErrorUsuario("El identificador y el nombre de departmento son obligatorios");
                            break;
                case 2292:  excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador de departmento ya que tiene empleados asociadeos o datos históricos asociados");
                            break;
                case 1:     excepcionHR.setMensajeErrorUsuario("El identificador de departmento ya existe");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                            break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    public Department leerDepartment(int departmentId){
        return null;
    }
    
    public ArrayList<Department> leerDepartments(){
        return null;
    }

    /**
     * Inserta un empleado en la base de datos
     * @author Ignacio Fontecha Hernández
     * @param employee contiene el empleado a insertar en la base de datos
     * @return cantidad de empleados insertados en la base de datos
     * @throws ExcepcionHR con toda la información acerca del error que se ha producido
     */
    public int insertarEmployee(Employee employee) throws ExcepcionHR{
        String dml="";
        int registrosAfectados=0;
        try {
            dml = "INSERT INTO EMPLOYEES"
                    + "(EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            
            
            
            sentenciaPreparada.setInt(1, employee.getEmployeeId());
            sentenciaPreparada.setString(2, employee.getFirstName());
            sentenciaPreparada.setString(3, employee.getLastName());
            sentenciaPreparada.setString(4, employee.getEmail());
            sentenciaPreparada.setString(5, employee.getPhoneNumber());
            sentenciaPreparada.setDate(6, employee.getHireDate());
            sentenciaPreparada.setString(7, employee.getJob().getJobId());
            sentenciaPreparada.setDouble(8, employee.getSalary());
            sentenciaPreparada.setDouble(9, employee.getCommissionPct());
            sentenciaPreparada.setInt(10, employee.getManager().getEmployeeId());
            sentenciaPreparada.setInt(11, employee.getDepartment().getDepartmentId());
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 2291:  excepcionHR.setMensajeErrorUsuario("El departamento, el trabajo o el jefe no existen.");
                            break;
                case 1400:  excepcionHR.setMensajeErrorUsuario("El email, la fecha de contratación, el apellido y el trabajo son obligatorios.");
                            break;
                case 2290:  excepcionHR.setMensajeErrorUsuario("El salario tiene que ser mayor que 0.");
                            break;
                case 1:     excepcionHR.setMensajeErrorUsuario("El identificador de usuario y el email no pueden repetirse.");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                            break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    public int borrarEmployee (int employeeId){
        return -1;
    }
    
    public int modificarEmployee (int employeeId, Employee employee){
        return -1;
    }
    
    public Employee leerEmployee(int employeeId){
        return null;
    }
    
    public ArrayList<Employee> leerEmployees(){
        return null;
    }
    
    public int insertarJob(Job job){
        return -1;
    }
    
    public int borrarJob (String jobId){
        return -1;
    }
    
    public int modificarJob (String jobId, Job job){
        return -1;
    }
    
    public Job leerJob(String jobId){
        return null;
    }
    
    /**
     * Devuelve un ArrayList con todos los trabajos.
     *
     * @author Carlos Labrador
     * @return ArrayList de Jobs con todos los trabajos en la base de datos.
     * @throws ExcepcionHR con toda la información acerca del error que se ha
     * producido
     */
    public ArrayList<Job> leerJobs() throws ExcepcionHR {
         Job j = null;
        String llamada = "";
        ArrayList<Job> trabajos = new ArrayList();
        try {
            llamada = "select * from JOBS";
            Statement sentencia = conexion.createStatement();
            ResultSet rs = sentencia.executeQuery(llamada);
            while (rs.next()) {
                
                j=new Job(rs.getString("JOB_ID"), rs.getString("JOB_TITLE"), rs.getInt("MIN_SALARY"), rs.getInt("MAX_SALARY"));
                trabajos.add(j);
            }
            rs.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), "Error general del sistema. Consulte con el administrador.", null);
            switch (ex.getErrorCode()) {
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            throw excepcionHR;
        }
        return trabajos;
        
    }

    public int insertarJobHistory(JobHistory jobHistory){
        return -1;
    }
    
    public int borrarJobHistory (int employeeId, Date startDate){
        return -1;
    }
    
    public int modificarJobHistory (int employeeId, Date startDate, JobHistory jobHistory){
        return -1;
    }
    
    public JobHistory leerJobHistory(int employeeId, Date startDate){
        return null;
    }
    
    public ArrayList<JobHistory> leerJobHistorys(){
        return null;
    }
}
