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
    
    public int insertarRegion(Region region) {
        return 0;
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
    
    public int modificarCountry (String countryId, Country country){
        return -1;
    }
    
    public Country leerCountry(String countryId){
        return null;
    }
    
    public ArrayList<Country> leerCountrys(){
        return null;
    }
    
    public int insertarLocation(Location location){
        return -1;
    }
    
    public int borrarLocation (int locationId){
        return -1;
    }
    
    public int modificarLocation (int locationId, Location location){
        return -1;
    }
    
    public Location leerLocation(int locationId){
        return null;
    }
    
    public ArrayList<Location> leerLocations(){
        return null;
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
    
    public ArrayList<Job> leerJobs(){
        return null;
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
