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
 * Clase que encapsula la funcionalidad necesaria para realizar todas las 
 * operaciones de alta, baja, modificacion y consulta de todas las tablas de la
 * base de datos HR
 * @author Ignacio Fontecha Hernández
 * @version 1.0
 */
public class HR {

    Connection conexion;
    
    /**
     * Constructor vacío
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
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
     * Inserta una región en la base de datos.
     * @author David Fernandez Garcia
     * @param region Región a insertar
     * @return Cantidad de regiones que se han insertado
     * @throws ExcepcionHR si se produce cualquier excepcion
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
    
    /**
     * Elimina una region de la base de datos
     * @author Ricardo Pérez Barreda
     * @param regionId Identificador de la región a eliminar
     * @return Cantidad de regiones eliminadas
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int borrarRegion(int regionId) throws ExcepcionHR {
        String dml = null;
        int registrosAfectados=0;
        try {
            
            dml = "delete from REGIONS where REGION_ID=?";
            try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml)) {
                sentenciaPreparada.setString(1, regionId+"");
                registrosAfectados=sentenciaPreparada.executeUpdate();
                sentenciaPreparada.close();
            }
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),"Error general del sistema. Consulte con el administrador.",null);
            switch (ex.getErrorCode()) {
                case 2292:  excepcionHR.setMensajeErrorUsuario("No se puede eliminar esta region porque tiene paises asociados");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                            break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;    
    }
    
    /**
     * Modifica la region que se le pase por parametro de la base de datos
     * @author Jonathan León Lorenzo
     * @param regionId contiene el identificador de la region a modificar
     * @param region contiene el identificador y nombre de la nueva region
     * @return Numero de registros afectados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int modificarRegion(int regionId, Region region) throws ExcepcionHR {
        String dml = null;
        int registrosAfectados=0;
        try {

            Statement sentencia = conexion.createStatement();

            dml = "update regions set REGION_ID="+region.getRegionId()+ ", REGION_NAME='"+region.getRegionName()+"' where REGION_ID="+regionId;
            registrosAfectados = sentencia.executeUpdate(dml);

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), "Error general del sistema. Consulte con el administrador.", null);
            switch (ex.getErrorCode()) {
                
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("No se puede modificar el codigo del continente mientras tenga paises asociados.");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("No se puede modificar el código del continente porque el nuevo código está siendo utilizado por otro continente.");
                    break;
                
                default:
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador" + ex);
                    break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    /**
     * Muestra la region que se le pase por parametro de la base de datos
     * @author Jonathan León Lorenzo
     * @param regionId contiene el identificador de la region a buscar
     * @return un objeto de tipo Region con el pais a mostrar.
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public Region leerRegion(int regionId) throws ExcepcionHR {
        String llamada = "";
        Region r=null;
        try {
            llamada = "select * from REGIONS where REGION_ID="+regionId;
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado1 = sentencia.executeQuery(llamada);
            while (resultado1.next()) {
                r=new Region(resultado1.getInt("REGION_ID"),resultado1.getString("REGION_NAME"));
            }
            resultado1.close();
            
            
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),"Error general del sistema. Consulte con el administrador.",null);
            switch (ex.getErrorCode()) {
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                            break;
            }
            throw excepcionHR;
        }
        return r;
    }
    
    
    /**
     * Muestra las regiones de la base de datos
     * @author Jonathan León Lorenzo
     * @return un ArrayList de tipo Region con los paises a mostrar.
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public ArrayList<Region> leerRegions() throws ExcepcionHR {
        String llamada = "";
        Region r=null;
        ArrayList<Region> a= new ArrayList();
        try {
            llamada = "select * from REGIONS";
            
            Statement sentencia = conexion.createStatement();
            
            ResultSet resultado1 = sentencia.executeQuery(llamada);
            while (resultado1.next()) {
                r=new Region(resultado1.getInt("REGION_ID"),resultado1.getString("REGION_NAME"));
                a.add(r);
            }
            resultado1.close();
            
            
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),"Error general del sistema. Consulte con el administrador.",null);
            switch (ex.getErrorCode()) {
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                            break;
            }
            throw excepcionHR;
        }
        return a;
    }
    
    /**
     * Inserta un país en la base de datos
     * @author Ricardo Pérez Barreda
     * @param country País a insertar
     * @return cantidad de paises insertados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int insertarCountry(Country country) throws ExcepcionHR{
        String dml="";
        int registrosAfectados=0;
        try {
            dml = "INSERT INTO COUNTRIES"
                    + "(COUNTRY_ID,COUNTRY_NAME,REGION_ID) "
                    + "VALUES (?,?,?)";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            
            
            
            sentenciaPreparada.setString(1, country.getCountryId());
            sentenciaPreparada.setString(2, country.getCountryName());
            sentenciaPreparada.setInt(3, country.getRegion().getRegionId());
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 2291:  excepcionHR.setMensajeErrorUsuario("La región introducida no existe.");
                            break;
                case 1400:  excepcionHR.setMensajeErrorUsuario("El pais tiene que tener un código.");
                            break;
                case 1:     excepcionHR.setMensajeErrorUsuario("No puede haber mas de un pais con el mismo código.");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                            break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    /**
     * Elimina un país de la base de datos
     * @author Ignacio Fontecha Hernández
     * @param countryId Identificador del país a eliminar
     * @return cantidad de paises eliminados
     * @throws ExcepcionHR si se produce cualquier excepcion
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
     * Modifica un país de la base de datos
     * @author David Fernandez Garcia
     * @param countryId Identificador del país a modificar
     * @param country contiene la nueva informacion del pais a modificar
     * @return cantidad de paises modificados
     * @throws ExcepcionHR si se produce cualquier excepcion
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
    
    /**
     * Consulta un país de la base de datos
     * @author Ricardo Pérez Barreda
     * @param countryId Identificador del pais a consultar
     * @return País a consultar
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public Country leerCountry(String countryId) throws ExcepcionHR{
        String dml="";
        Country country = null;

         try {
            dml = ("Select * from COUNTRIES where COUNTRY_ID = '"+countryId+"'") ;
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dml); 
            
            while (resultado.next()) {
                Region r = new Region(resultado.getInt("REGION_ID"),null);
                country = new Country(resultado.getString("COUNTRY_ID"),resultado.getString("COUNTRY_NAME"),r);
            }
            resultado.close();
         }
            catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(),null,dml);
            throw excepcionHR;
            }
        return country;
    }
    
    /**
     * Leer los paises de la base de datos
     * @author Daniel Portilla López
     * @return devuvelve una lista con paises que hay en la base de datos
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public ArrayList<Country> leerCountrys() throws ExcepcionHR{
        Country c = null;
        Region r = null;
        ArrayList<Country> lista=new ArrayList();
        String llamada = "";   
        try{
            llamada = "select * from COUNTRIES";
            Statement sentencia = conexion.createStatement();
            ResultSet rs = sentencia.executeQuery(llamada);
            while(rs.next()){
                r = new Region();
                r.setRegionId(rs.getInt("REGION_ID"));
                c = new Country(rs.getString("COUNTRY_ID"),rs.getString("COUNTRY_NAME"),r);
                lista.add(c);
            }
            rs.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),"Error general del sistema. Consulte con el administrador.",null);
            switch (ex.getErrorCode()) {
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                            break;
            }
            throw excepcionHR;
        }
        return lista;
    }
    
    /**
     * Inserta una localidad en la base de datos
     * @author Carlos Labrador Amieva
     * @param location Localidad a insertar
     * @return cantidad de localidades insertadas
     * @throws ExcepcionHR si se produce cualquier excepcion
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
     * @param locationId Identificador de la localidad a eliminar
     * @return Cantidad de localidades eliminadas
     * @throws ExcepcionHR si se produce cualquier excepcion
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
    
    /**
     * Modifica una localidad de la base de datos
     * @author Alberto Martínez - Pilar Sánchez
     * @param locationId  contiene el identificador de la localidad a modificar
     * @param location contiene los nuevos datos de la localidad a modificar
     * @return cantidad de localidades modificadas en la base de datos
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int modificarLocation(int locationId,Location location) throws ExcepcionHR{
        int registrosAfectados = 0;
        String dml = "";
        try {
            Statement sentencia = conexion.createStatement();

            dml = "update LOCATIONS set LOCATION_ID = ? , STREET_ADDRESS = ? , POSTAL_CODE = ? , CITY = ? ,STATE_PROVINCE = ? , COUNTRY_ID = ? where LOCATION_ID = ?";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            
            sentenciaPreparada.setInt(1, location.getLocationId());
            sentenciaPreparada.setString(2, location.getStreetAddress());
            sentenciaPreparada.setString(3, location.getPostalCode());
            sentenciaPreparada.setString(4, location.getCity());
            sentenciaPreparada.setString(5, location.getStateProvince());
            sentenciaPreparada.setString(6, location.getCountry().getCountryId());
            sentenciaPreparada.setInt(7, locationId);
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 2291:  excepcionHR.setMensajeErrorUsuario("El pais no existe");
                            break;
                case 1407:  excepcionHR.setMensajeErrorUsuario("El identificador y el nombre de la ciudad son obligatorios");
                            break;
                case 2292:  excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador de localidad ya que tiene departamentos asociados");
                            break;
                case 1:     excepcionHR.setMensajeErrorUsuario("El identificador de localidad ya existe");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                            break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    /**
     * Lee los datos de una localidad determinada
     * @param locationId Identificador de la localidad a buscar
     * @return Localidad con los datos obtenidos
     * @throws ExcepcionHR si se produce cualquier excepcion
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
     * @throws ExcepcionHR si se produce cualquier excepcion
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
    
    /**
     * Inserta un departamento en la base de datos
     * @param department Departmento a insertar
     * @return Cantidad de departamentos insertados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int insertarDepartment(Department department) throws ExcepcionHR {
        String dml = "";
        int registrosAfectados = -1;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            Statement sentencia = conexion.createStatement();
            dml = "insert into DEPARTMENTS (DEPARTMENT_ID, DEPARTMENT_NAME,MANAGER_ID,LOCATION_ID)"
                    + " values(?,?,?,?)";

            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setInt(1, department.getDepartmentId());
            sentenciaPreparada.setString(2, department.getDepartmentName());
            sentenciaPreparada.setInt(3, department.getManager().getEmployeeId());
            sentenciaPreparada.setInt(4, department.getLocation().getLocationId());
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(0, ex.getMessage(), "Error - Clase no Encontrada: " + ex.getMessage(), dml);
            throw excepcionHR;
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                
                case 1400:
                    excepcionHR.setMensajeErrorUsuario("Error: los siguientes datos son obligatorios: Nombre del Departamento");
                    break;
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("Error: La localidad no existe o el empleado jefe no existe.");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("Error: El ID del departamento no puede repetirse");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                    break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Elimina un departamento de la base de datos
     * @param departmentId Identificador del departamento a eliminar
     * @return Cantidad de departamentos eliminados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int borrarDepartment(int departmentId) throws ExcepcionHR {
        String dml = null;
        int registrosAfectados=-1;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            Statement sentencia = conexion.createStatement();

            dml = "delete from departments where DEPARTMENT_ID="+departmentId;
            registrosAfectados = sentencia.executeUpdate(dml);
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(0, ex.getMessage(), "Error - Clase no Encontrada: " + ex.getMessage(), dml);
            throw excepcionHR;
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2292:  excepcionHR.setMensajeErrorUsuario("Error: No se puede eliminar el departamento ya que tiene empleados o registros historicos");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                            break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    /**
     * Modifica un departamento de la base de datos
     * @author Ignacio Fontecha Hernández
     * @param departmentId contiene el identificador del departamento a modificar
     * @param department contiene la nuevos datos del departamento a modificar
     * @return cantidad de departamentos modificados en la base de datos
     * @throws ExcepcionHR si se produce cualquier excepcion
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
    
    /**
     * Consulta un departamento de la base de datos
     * @param departmentId identificador del departmento a consultar
     * @return Departmento a consultar
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public Department leerDepartment(int departmentId) throws ExcepcionHR {
        Department d = new Department();
        String dml = "";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            Statement sentencia = conexion.createStatement();
            dml = "select * from DEPARTMENTS where DEPARTMENT_ID = ?";

            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setInt(1, departmentId);
            ResultSet resultado=sentenciaPreparada.executeQuery();
            d.setDepartmentId(resultado.getInt("DEPARTMENT_ID"));
            d.setDepartmentName(resultado.getString("DEPARTMENT_NAME"));
            Location l= new Location();
            l.setLocationId(resultado.getInt("LOCATION_ID"));
            d.setLocation(l);
            Employee e = new Employee();
            e.setEmployeeId(resultado.getInt("MANAGER_ID"));
            d.setManager(e);
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(0, ex.getMessage(), "Error - Clase no Encontrada: " + ex.getMessage(), dml);
            throw excepcionHR;
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                default:
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                    break;
            }
            throw excepcionHR;
        }
        return d;
    }

    /**
     * Metodo que obtiene todos los Department de la base de datos
     * @return todos los Departament de la base de datos
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public ArrayList<Department> leerDepartments() throws ExcepcionHR {
        ArrayList<Department> solucion = new ArrayList<Department>();
        String dml = "";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            Statement sentencia = conexion.createStatement();
            dml = "select * from DEPARTMENTS";
            ResultSet resultados= sentencia.executeQuery(dml);
            while(resultados.next()){
                Department d = new Department();
                d.setDepartmentId(resultados.getInt("DEPARTMENT_ID"));
                d.setDepartmentName(resultados.getString("DEPARTMENT_NAME"));
                Location l= new Location();
                l.setLocationId(resultados.getInt("LOCATION_ID"));
                d.setLocation(l);
                Employee e = new Employee();
                e.setEmployeeId(resultados.getInt("MANAGER_ID"));
                d.setManager(e);
                solucion.add(d);
            }
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(0, ex.getMessage(), "Error - Clase no Encontrada: " + ex.getMessage(), dml);
            throw excepcionHR;
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                default:
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                    break;
            }
            throw excepcionHR;
        }
        return solucion;
    }

    /**
     * Inserta un empleado en la base de datos
     * @author Ignacio Fontecha Hernández
     * @param employee Empleado a insertar
     * @return Cantidad de empleados insertados
     * @throws ExcepcionHR si se produce cualquier excepcion
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
    
    /**
     * Elimina un empleado de la base de datos
     * @author Jonathan León Lorenzo
     * @param employeeId Identificador del empleado a eliminar
     * @return cantidad de empleados eliminados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int borrarEmployee (int employeeId) throws ExcepcionHR{
        String llamada = "";
        int registrosAfectados=0;
        try {
            llamada = "call BORRAR_EMPLOYEE(?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            
            sentenciaLlamable.setInt(1, employeeId);
            registrosAfectados=sentenciaLlamable.executeUpdate();
            
            sentenciaLlamable.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),"Error general del sistema. Consulte con el administrador.",null);
            switch (ex.getErrorCode()) {
                case 2292:  excepcionHR.setMensajeErrorUsuario("No se puede borrar porque tiene un historial asociado");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                            break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;  
    }
    
    /**
     * Modifica un empleado de la base de datos
     * @author Ricardo Pérez Barreda
     * @param employeeId contiene el identificador del empleado a modificar
     * @param employee contiene la nuevos datos del empleado a modificar
     * @return cantidad de empleados modificados en la base de datos
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int modificarEmployee (int employeeId, Employee employee) throws ExcepcionHR{
        int registrosAfectados = 0;
        String dml = "";
        try {
            Statement sentencia = conexion.createStatement();

            dml = "update EMPLOYEES set "
                    + "EMPLOYEE_ID = "      + employee.getEmployeeId() + ","
                    + "FIRST_NAME = '"      + employee.getFirstName() + "',"
                    + "LAST_NAME = '"       + employee.getLastName() + "',"
                    + "EMAIL = '"           + employee.getEmail() + "',"
                    + "PHONE_NUMBER = '"    + employee.getPhoneNumber() + "',"
                    + "HIRE_DATE = '"        + employee.getHireDate() + "',"
                    + "JOB_ID = '"          + employee.getJob().getJobId() + "',"
                    + "SALARY = "           + employee.getSalary() + ","
                    + "COMMISSION_PCT = "   + employee.getCommissionPct() + ","
                    + "MANAGER_ID = "       + employee.getManager().getEmployeeId() + ","
                    + "DEPARTMENT_ID = "    + employee.getDepartment().getDepartmentId() + " " 
                    + "where EMPLOYEE_ID = "+ employeeId ;
            registrosAfectados = sentencia.executeUpdate(dml);
            
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 2291:  excepcionHR.setMensajeErrorUsuario("El jefe de empleado, el trabajo o el departamento seleccionado no existen");
                            break;
                case 1407:  excepcionHR.setMensajeErrorUsuario("Los campos: Identificador de empleado, Email, Fecha de contratación, "
                        + "Apellido y Trabajo son obligatorios");
                            break;
                case 2290:  excepcionHR.setMensajeErrorUsuario("El salario no puede ser menor o igual que 0");
                            break;
                case 1:     excepcionHR.setMensajeErrorUsuario("Tanto el Email como el Identificador de empleado no pueden repetirse");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                            break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    /**
     * Consulta un empleado de la base de datos
     * @author Alberto Martínez - Pilar Sánchez
     * @param employeeId  Identificador del empleado a consultar
     * @return Empleado a consultar
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public Employee leerEmployee(int employeeId) throws ExcepcionHR{
        String dql="";
        Employee e=new Employee();
        try {
            Job j=new Job();
            Employee m=new Employee();
            Department d=new Department();
            dql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID= ?";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dql);
            sentenciaPreparada.setInt(1, employeeId);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            while(resultado.next()){
                e.setEmployeeId(resultado.getInt(1));
                e.setFirstName(resultado.getString(2));
                e.setLastName(resultado.getString(3));
                e.setEmail(resultado.getString(4));
                e.setPhoneNumber(resultado.getString(5));
                e.setHireDate(resultado.getDate(6));
                j.setJobId(resultado.getString(7));
                e.setJob(j);
                e.setSalary(resultado.getDouble(8));
                e.setCommissionPct(resultado.getDouble(9));
                m.setEmployeeId(resultado.getInt(10));
                e.setManager(m);
                d.setDepartmentId(resultado.getInt(11));
                e.setDepartment(d);
            }
            resultado.close();
            
            sentenciaPreparada.close();
            conexion.close();
         
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,dql);
            throw excepcionHR;
        }
        return e;
    }
    
    /**
     * Lee todos los empleados de la base de datos
     * @author Alberto Martínez - Pilar Sánchez
     * @return Lista con todos los empleados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public ArrayList<Employee> leerEmployees() throws ExcepcionHR{
        ArrayList<Employee> misEmployees=new ArrayList<Employee>();
        int registrosAfectados = 0;
        String dml = "";
        try {
            Statement sentencia = conexion.createStatement();

            String dql1 = "select * from EMPLOYEES";
            ResultSet resultado1 = sentencia.executeQuery(dql1);
            while (resultado1.next()) {
           
                //Employee
                Employee empleado = new Employee();
                empleado.setEmployeeId(resultado1.getInt("EMPLOYEE_ID"));
                empleado.setFirstName(resultado1.getString("FIRST_NAME"));
                empleado.setLastName(resultado1.getString("LAST_NAME"));
                empleado.setEmail(resultado1.getString("EMAIL"));
                empleado.setPhoneNumber(resultado1.getString("PHONE_NUMBER"));
                empleado.setHireDate(resultado1.getDate("HIRE_DATE"));
                
                Job miJob = new Job();
                miJob.setJobId(resultado1.getString("JOB_ID"));
                empleado.setJob(miJob);
                empleado.setSalary(resultado1.getDouble("SALARY"));
                empleado.setCommissionPct(resultado1.getDouble("COMMISSION_PCT"));
                
                Employee miEmpleado = new Employee();
                empleado.setEmployeeId(resultado1.getInt("EMPLOYEE_ID"));
                empleado.setManager(empleado);
                
                Department miDepartment = new Department();
                miDepartment.setDepartmentId(resultado1.getInt("DEPARTMENT_ID"));
                empleado.setJob(miJob);
                empleado.setDepartment(miDepartment);

                
                
                misEmployees.add(empleado);
              

            }
            
            resultado1.close();

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
               
         
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            throw excepcionHR;
        }
        return misEmployees; 
    } 
    
    /**
     * Inserta un trabajo en la base de datos
     * @author Ignacio Fontecha Hernández
     * @param job Trabajo a insertar
     * @return Cantidad de trabajos insertados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int insertarJob(Job job){
        return -1;
    }
    
    /**
     * Elimina un trabajo de la base de datos
     * @author Alberto Martínez - Pilar Sánchez
     * @param jobId Identificador del trabajo a eliminar
     * @return cantidad de trabajos eliminados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */   
    public int borrarJob(String jobId) throws ExcepcionHR{
        String llamada = null;
        int registrosAfectados=0;
        try {
            llamada = "call BORRAR_JOB(?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            
            sentenciaLlamable.setString(1, jobId);
            registrosAfectados=sentenciaLlamable.executeUpdate();
            
            sentenciaLlamable.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,llamada);
             switch (ex.getErrorCode()) {
                case 2292:  System.out.printf("No se puede borrar el trabajo porque tiene empleados o historiales de trabajo adheridos.");
                            break;
                default:    System.out.println("Error en el sistema. Consulta con el administrador");
                            break;
            }
             throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    /**
     * Modifica un trabajo de la base de datos
     * @author Pilar Sánchez - Alberto Martínez
     * @param jobId contiene el identificador del trabajo que queremos modificar
     * @param job contiene los nuevos datos del trabajo a modificar
     * @return cantidad de trabajos modifcados en la base de datos
     * @throws ExcepcionHR si se produce cualquier excepcion
     */ 
    public int modificarJob(String jobId,Job job) throws ExcepcionHR{
        int registrosAfectados = 0;
        String dml = "";
        try {
            Statement sentencia = conexion.createStatement();

            dml = "update JOBS set JOB_ID = ? , JOB_TITLE = ? , MIN_SALARY = ? , MAX_SALARY = ? where JOB_ID = ?";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            
            sentenciaPreparada.setString(1, job.getJobId());
            sentenciaPreparada.setString(2, job.getJobTitle());
            sentenciaPreparada.setInt(3, job.getMinSalary());
            sentenciaPreparada.setInt(4, job.getMaxSalary());
            sentenciaPreparada.setString(5, jobId);
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 1407:  excepcionHR.setMensajeErrorUsuario("El nombre del trabajo es obligatorio");
                            break;
                case 2292:  excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador de trabajo ya que tiene empleados o historiales asociados");
                            break;
                case 1:     excepcionHR.setMensajeErrorUsuario("El identificador de empleo ya existe");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                            break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    /**
     * Consulta un trabajo de la base de datos
     * @author Adela Verdeja
     * @param jobId Identificado del trabajo a consultar
     * @return Trabajo a consultar
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public Job leerJob(String jobId) {
        Job j = new Job();
        try {
            String sql = "select * from jobs where job_id = ? ";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, jobId);
            ResultSet resultado1 = sentencia.executeQuery();
            while (resultado1.next()) {
                j.setJobId(resultado1.getString("job_id"));
                j.setJobTitle(resultado1.getString("job_title"));
                j.setMinSalary(resultado1.getInt("min_salary"));
                j.setMaxSalary(resultado1.getInt("max_salary"));
                System.out.println(j);
                
            }
            resultado1.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return j;
    }
    
    /**
     * Devuelve un ArrayList con todos los trabajos.
     *
     * @author Carlos Labrador
     * @return ArrayList de Jobs con todos los trabajos en la base de datos.
     * @throws ExcepcionHR si se produce cualquier excepcion
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

 /**
     * Inserta un dato histórico de trabajo y/o departamento de un empleado
     * en la base de datos
     * @author Rubén Argumosa Roiz.
     * @param jobHistory dato histórico de trabajo y/o departamento de un empleado a insertar
     * @return cantidad de datos históricos de trabajo y/o departamento de un empleado insertados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int insertarJobHistory(JobHistory jobHistory) throws ExcepcionHR{
        String dml="";
        int registrosAfectados=0;
        try {
            dml = "INSERT JOB_HISTORY"
                    + "(EMPLOYEE_ID,START_DATE,END_DATE,JOB_ID,DEPARTMENT_ID) "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            
            
            
            sentenciaPreparada.setInt(1, jobHistory.getEmpleado().getEmployeeId());
            sentenciaPreparada.setDate(2, jobHistory.getStartDate());
            sentenciaPreparada.setDate(3, jobHistory.getEndDate());
            sentenciaPreparada.setString(4, jobHistory.getJob().getJobId());
            sentenciaPreparada.setInt(5, jobHistory.getDepartment().getDepartmentId());
            
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 2291:  excepcionHR.setMensajeErrorUsuario("Error: se ha producido uno de los siguientes errores:El departamento seleccionado no existe,El empleado no existe,El trabajo escogido no existe");
                            break;
                case 1400:  excepcionHR.setMensajeErrorUsuario("Identificador de empleado, fecha de entrada,y fecha de salida son obligatorios.");
                            break;
                case 2290: excepcionHR.setMensajeErrorUsuario("Error: la fecha de entrada no pude ser mayor a la de salida");
                            break;
                case 1:     excepcionHR.setMensajeErrorUsuario("El identificador de empleado y el email no pueden repetirse.");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error: el identificador de empleado no puede repetirse, en el mismo dia.");
                            break;
            }
            throw excepcionHR;
        }
       
            
        return registrosAfectados;
        
    }
    
    /**
     * Borra un ewgistro de la tabla JobHistory de la base de datos
     *
     * @author Adela Verdeja
     * @param employeeId contienne el identificado del registro de jobHistory a
     * modificar
     * @param startDate contiene la fecha a borrar del registro de JobHistory
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int BorrarJobHistory(int employeeId, java.sql.Date startDate) throws ExcepcionHR {
        String llamada = "";
        int registrosAfectados;
        try {
            llamada = "call BORRAR_JOB_HISTORY(?,?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            sentenciaLlamable.setInt(1, employeeId);
            sentenciaLlamable.setDate(2, startDate);
            sentenciaLlamable.executeUpdate();
            registrosAfectados = sentenciaLlamable.executeUpdate();
            sentenciaLlamable.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(-1, ex.getMessage(), "Error general del sistema. Consulte con el administrador", llamada);
            switch (ex.getErrorCode()) {
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
   /**
     * Modifica un historial de trabajo en la base de datos.
     * @author Rubén Argumosa Roiz.
     * @param jobHistory  contiene el registro de empleado a insertar en la base de datos.
     * @param employeeId contiene el identificador de empleado a modificar.
     * @param startDate contiene la fecha de registro a modificar.
     * @return cantidad de historial de trabajo insertados en la base de datos
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int modificarJobHistory (int employeeId, java.sql.Date startDate, JobHistory jobHistory) throws ExcepcionHR{
        
        String llamada = "";
        int registrosAfectados=0;
        try {
            llamada = "call MODIFICAR_JOBHISTORY(?,?,?,?,?,?,?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            
            sentenciaLlamable.setInt(1, jobHistory.getEmpleado().getEmployeeId());
            sentenciaLlamable.setDate(2, jobHistory.getStartDate());
            sentenciaLlamable.setDate(3,jobHistory.getEndDate() );
            sentenciaLlamable.setString(4, jobHistory.getJob().getJobId());
            sentenciaLlamable.setInt(5, jobHistory.getDepartment().getDepartmentId());
            sentenciaLlamable.setInt(6, employeeId);
            sentenciaLlamable.setDate(7,startDate);
            registrosAfectados=sentenciaLlamable.executeUpdate();
            
            sentenciaLlamable.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR=new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),"Error general del sistema. Consulte con el administrador.",null);
            switch (ex.getErrorCode()) {
                case 2291:  excepcionHR.setMensajeErrorUsuario("Error: se ha producido uno de los siguientes errores:El departamento seleccionado no existe,El empleado no existe,El trabajo escogido no existe");
                            break;
                case 1407:  excepcionHR.setMensajeErrorUsuario("Identificador de empleado, fecha de entrada,y fecha de salida son obligatorios.");
                            break;
                case 2290: excepcionHR.setMensajeErrorUsuario("Error: la fecha de entrada no pude ser mayor a la de salida");
                            break;
                case 1:     excepcionHR.setMensajeErrorUsuario("El identificador de empleado y el email no pueden repetirse.");
                            break;
                default:    excepcionHR.setMensajeErrorUsuario("Error: inesperado, consulte con el administrador.");
                            break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    /**
     * Mustra la informacion de un registro job_history
     * @author Rodrigo Corsini
     * @param employeeId contiene el identificador del empleado a buscar
     * @param startDate la fecha de inicio
     * @return datos del job_history
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public JobHistory leerJobHistory(int employeeId, Date startDate) throws ExcepcionHR {
        JobHistory j = new JobHistory();

        String dql1 = "";

        try {
            Statement sentencia = conexion.createStatement();

            dql1 = "select * from job_history where EMPLOYEE_ID=" + employeeId + " and " + "START_DATE=" + startDate;
            ResultSet resultado1 = sentencia.executeQuery(dql1);
            while (resultado1.next()) {
                //Employee
                Employee e = new Employee();
                e.setEmployeeId(employeeId);
                j.setEmpleado(e);

                //Start--End--> Date
                j.setStartDate(startDate);
                j.setEndDate(resultado1.getDate("END_DATE"));
                //Job
                Job r = new Job();
                r.setJobId(resultado1.getString("JOB_ID"));
                j.setJob(r);

                //Department
                Department a = new Department();
                a.setDepartmentId(resultado1.getInt("DEPARTMENT_ID"));
                j.setJob(r);

            }

            resultado1.close();

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dql1);
            switch (ex.getErrorCode()) {

                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            throw excepcionHR;
        }
        return j;
    }

    /**
     * Mustra la informacion de todos los registros de job_history
     * @author Rodrigo Corsini
     * @return ArrayList con todos los job_history
     * @throws ExcepcionHR si se produce cualquier excepcion
     */

    public ArrayList<JobHistory> leerJobHistorys() throws ExcepcionHR {

        ArrayList<JobHistory> misJobs = new ArrayList<JobHistory>();
        int registrosAfectados = 0;
        String dql1 = "";
        try {
            Statement sentencia = conexion.createStatement();

             dql1 = "select * from job_history";
            ResultSet resultado1 = sentencia.executeQuery(dql1);
            while (resultado1.next()) {
                JobHistory j1 = new JobHistory();
                //Employee
                Employee e = new Employee();
                e.setEmployeeId(resultado1.getInt("EMPLOYEE_ID"));
                j1.setEmpleado(e);

                //Start--End--> Date
                j1.setStartDate(resultado1.getDate("START_DATE"));
                j1.setEndDate(resultado1.getDate("END_DATE"));
                //Job
                Job r = new Job();
                r.setJobId(resultado1.getString("JOB_ID"));
                j1.setJob(r);

                //Department
                Department a = new Department();
                a.setDepartmentId(resultado1.getInt("DEPARTMENT_ID"));
                j1.setJob(r);

                //LLENAR ARRAYLIST
                misJobs.add(j1);

            }

            resultado1.close();

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dql1);
            switch (ex.getErrorCode()) {

                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            throw excepcionHR;
        }
        return misJobs;

    }
}
