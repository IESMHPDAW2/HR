 package hr;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

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
            ExcepcionHR excepcionHR = new ExcepcionHR(-1, ex.getMessage(), "Error general del sistema. Consulte con el administrador.", null);
            throw excepcionHR;
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), "Error general del sistema. Consulte con el administrador.", null);
            throw excepcionHR;
        }
    }

    /**
     * Cierra de forma ordenada un objeto Statement y la conexión de la base de 
     * datos
     * @author Ignacio Fontecha Hernández
     * @param conexion Conexion a cerrar
     */
    private void cerrarConexion(Connection conexion, Statement sentencia) {
        try {
           sentencia.close();
           conexion.close();
        } catch (SQLException | NullPointerException ex) {}
    }
    
    /**
     * Cierra de forma ordenada un objeto Statement y la conexión de la base de 
     * datos
     * @author Ignacio Fontecha Hernández
     * @param conexion Conexion a cerrar
     */
    private void cerrarConexion(Connection conexion, PreparedStatement sentenciaPreparada) {
        try {
           sentenciaPreparada.close();
           conexion.close();
        } catch (SQLException | NullPointerException ex) {}
    }
    
    /**
     * Cierra de forma ordenada un objeto Statement y la conexión de la base de 
     * datos
     * @author Ignacio Fontecha Hernández
     * @param conexion Conexion a cerrar
     */
    private void cerrarConexion(Connection conexion, CallableStatement sentenciaLlamable) {
        try {
           sentenciaLlamable.close();
           conexion.close();
        } catch (SQLException | NullPointerException ex) {}
       
    }
    
    /**
     * Inserta una región en la base de datos.
     * @author David Fernandez Garcia
     * @param region Región a insertar
     * @return Cantidad de regiones que se han insertado
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int insertarRegion(Region region) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            dml = "insert into regions(region_id,region_name) values(?,?)";
            sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setInt(1, region.getRegionId());
            sentenciaPreparada.setString(2, region.getRegionName());
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
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
            cerrarConexion(conexion, sentenciaPreparada);
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
        PreparedStatement sentenciaPreparada = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            dml = "delete from REGIONS where REGION_ID=?";
            sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setInt(1, regionId);
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), "Error general del sistema. Consulte con el administrador.", null);
            switch (ex.getErrorCode()) {
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("No se puede eliminar esta region porque tiene paises asociados");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Modifica una región de la base de datos
     * @author Jonathan León Lorenzo
     * @param regionId Identificador de la región a modificar
     * @param region Nuevos datos de la región a modificar
     * @return Cantidad de regiones modificadas
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int modificarRegion(int regionId, Region region) throws ExcepcionHR {
        Statement sentencia = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            sentencia = conexion.createStatement();
            dml = "update regions set REGION_ID=" + region.getRegionId() + ", REGION_NAME='" + region.getRegionName() + "' where REGION_ID=" + regionId;
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
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

/**
     * Consulta una región de la base de datos
     * @author Jonathan Leon-Byron Morales
     * @param regionId Identificador de la región a consultar
     * @return Región a consultar
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public Region leerRegion(int regionId) throws ExcepcionHR {
        Statement sentencia = null;
        String dql = null;
        Region r = null;
        try {
            dql = "SELECT * FROM REGIONS WHERE REGION_ID=" + regionId;
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                r = new Region(resultado.getInt("REGION_ID"), resultado.getString("REGION_NAME"));
            }
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), "Error general del sistema. Consulte con el administrador.", null);
            switch (ex.getErrorCode()) {
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return r;
    }
    /**
     * Consulta todas las regiones de la base de datos
     * @author Jonathan León Lorenzo
     * @return Lista de todas las regiones
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public ArrayList<Region> leerRegions() throws ExcepcionHR {
        Statement sentencia = null;
        String dql = null;
        Region r = null;
        ArrayList<Region> a = new ArrayList();
        try {
            dql = "select * from REGIONS";
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                r = new Region(resultado.getInt("REGION_ID"), resultado.getString("REGION_NAME"));
                a.add(r);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), "Error general del sistema. Consulte con el administrador.", null);
            switch (ex.getErrorCode()) {
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return a;
    }

    /**
     * Inserta un país en la base de datos
     * @author Ricardo Pérez Barreda
     * @param country País a insertar
     * @return Cantidad de paises insertados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int insertarCountry(Country country) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            dml = "INSERT INTO COUNTRIES"
                    + "(COUNTRY_ID,COUNTRY_NAME,REGION_ID) "
                    + "VALUES (?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, country.getCountryId());
            sentenciaPreparada.setString(2, country.getCountryName());
            sentenciaPreparada.setInt(3, country.getRegion().getRegionId());
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("La región introducida no existe.");
                    break;
                case 1400:
                    excepcionHR.setMensajeErrorUsuario("El pais tiene que tener un código.");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("No puede haber mas de un pais con el mismo código.");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Elimina un país de la base de datos
     * @author Ignacio Fontecha Hernández
     * @param countryId Identificador del país a eliminar
     * @return Cantidad de paises eliminados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int borrarCountry(String countryId) throws ExcepcionHR {
        CallableStatement sentenciaLlamable = null;
        String llamada = null;
        int registrosAfectados = 0;
        try {
            llamada = "call BORRAR_COUNTRY(?)";
            sentenciaLlamable = conexion.prepareCall(llamada);
            sentenciaLlamable.setString(1, countryId);
            registrosAfectados = sentenciaLlamable.executeUpdate();
            sentenciaLlamable.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), "Error general del sistema. Consulte con el administrador.", null);
            switch (ex.getErrorCode()) {
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("No se puede borrar porque tiene localidades asociadas");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            cerrarConexion(conexion, sentenciaLlamable);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Modifica un país de la base de datos
     * @author David Fernandez Garcia
     * @param countryId Identificador del país a modificar
     * @param country Nuevos datos del pais a modificar
     * @return Cantidad de paises modificados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int modificarCountry(String countryId, Country country) throws ExcepcionHR {
        CallableStatement sentenciaLlamable = null;
        String llamada = null;
        int registrosAfectados = 0;
        try {
            llamada = "call MODIFICARCOUNTRY(?,?,?,?)";
            sentenciaLlamable = conexion.prepareCall(llamada);
            sentenciaLlamable.setString(1, countryId);
            sentenciaLlamable.setString(2, country.getCountryId());
            sentenciaLlamable.setString(3, country.getCountryName());
            sentenciaLlamable.setInt(4, country.getRegion().getRegionId());
            registrosAfectados = sentenciaLlamable.executeUpdate();
            sentenciaLlamable.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, llamada);
            switch (ex.getErrorCode()) {
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
            cerrarConexion(conexion, sentenciaLlamable);
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
    public Country leerCountry(String countryId) throws ExcepcionHR {
        Statement sentencia = null;
        String dql = null;
        Country c = null;
        try {
            dql = ("Select * from COUNTRIES where COUNTRY_ID = '" + countryId + "'");
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                Region r = new Region(resultado.getInt("REGION_ID"), null);
                c = new Country(resultado.getString("COUNTRY_ID"), resultado.getString("COUNTRY_NAME"), r);
            }
            resultado.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(
                    ex.getErrorCode(),
                    ex.getMessage(),
                    "Error general del sistema. Consulte con el administrador",
                    dql);
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return c;
    }

    /**
     * Consulta todos los paises de la base de datos
     * @author Ricardo Pérez Barreda
     * @return Lista de todos los paises
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public ArrayList<Country> leerCountrys() throws ExcepcionHR {
        Statement sentencia = null;
        String dql = null;
        Country c = null;
        Region r = null;
        ArrayList<Country> lista = new ArrayList();
        try {
            dql = "select * from COUNTRIES";
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                r = new Region();
                r.setRegionId(resultado.getInt("REGION_ID"));
                c = new Country(resultado.getString("COUNTRY_ID"), resultado.getString("COUNTRY_NAME"), r);
                lista.add(c);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(
                    ex.getErrorCode(),
                    ex.getMessage(),
                    "Error general del sistema. Consulte con el administrador",
                    dql);
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return lista;
    }

    /**
     * Inserta una localidad en la base de datos
     * @author Carlos Labrador Amieva
     * @param location Localidad a insertar
     * @return Cantidad de localidades insertadas
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int insertarLocation(Location location) throws ExcepcionHR {
        CallableStatement sentenciaLlamable = null;
        String llamada = null;
        int registrosAfectados = 0;
        try {
            llamada = "INSERT INTO LOCATIONS(LOCATION_ID,STREET_ADDRESS,POSTAL_CODE,CITY,STATE_PROVINCE,COUNTRY_ID) VALUES (?,?,?,?,?,?)";
            sentenciaLlamable = conexion.prepareCall(llamada);
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
            cerrarConexion(conexion, sentenciaLlamable);
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
    public int borrarLocation(int locationId) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            dml = "DELETE FROM LOCATIONS WHERE LOCATION_ID=?";
            sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setInt(1, locationId);
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("La localidad a borrar, tiene departamentos asociados.");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                    break;
            }
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Modifica una localidad de la base de datos
     * @author Alberto Martínez - Pilar Sánchez
     * @param locationId Identificador de la localidad a modificar
     * @param location Nuevos datos de la localidad a modificar
     * @return Cantidad de localidades modificadas
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int modificarLocation(int locationId, Location location) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            dml = "update LOCATIONS set LOCATION_ID = ? , STREET_ADDRESS = ? , POSTAL_CODE = ? , CITY = ? ,STATE_PROVINCE = ? , COUNTRY_ID = ? where LOCATION_ID = ?";
            sentenciaPreparada = conexion.prepareStatement(dml);
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
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("El pais no existe");
                    break;
                case 1407:
                    excepcionHR.setMensajeErrorUsuario("El identificador y el nombre de la ciudad son obligatorios");
                    break;
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador de localidad ya que tiene departamentos asociados");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("El identificador de localidad ya existe");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Consulta una localidad de la base de datos
     * @author Alberto Martínez - Pilar Sánchez
     * @param locationId Identificador de la localidad a consultar
     * @return Localidad a consultar
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public Location leerLocation(int locationId) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dql = null;
        Region region = null;
        Country country = null;
        Location location = null;
        
        try {
            dql = "SELECT * " +
                    "FROM REGIONS R, COUNTRIES C, LOCATIONS L " +
                    "WHERE R.REGION_ID = C.REGION_ID " +
                    "  AND C.COUNTRY_ID = L.COUNTRY_ID " +
                    "  AND LOCATION_ID = ?";
            sentenciaPreparada = conexion.prepareStatement(dql);
            sentenciaPreparada.setInt(1, locationId);
            sentenciaPreparada.executeQuery();

            ResultSet resultado = sentenciaPreparada.executeQuery(dql);
            while (resultado.next()) {
                region = new Region();
                region.setRegionId(resultado.getInt("REGION_ID"));
                region.setRegionName(resultado.getString("REGION_NAME"));
                country = new Country();
                country.setCountryId(resultado.getString("COUNTRY_ID"));
                country.setCountryName(resultado.getString("COUNTRY_NAME"));
                country.setRegion(region);
                location = new Location();
                location.setLocationId(resultado.getInt("LOCATION_ID"));
                location.setStreetAddress(resultado.getString("STREET_ADDRESS"));
                location.setPostalCode(resultado.getString("POSTAL_CODE"));
                location.setCity(resultado.getString("CITY"));
                location.setStateProvince(resultado.getString("STATE_PROVINCE"));
                location.setCountry(country);
            }
            resultado.close();
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(
                    ex.getErrorCode(), 
                    ex.getMessage(), 
                    "Error general del sistema. Consulte con el administrador.", 
                    dql);
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return location;
    }

    /**
     * Consulta todas las localidades de la base de datos
     * @author Ignacio Fontecha Hernández
     * @return Lista de todas las localidades
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public ArrayList<Location> leerLocations() throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dql = null;
        Region region = null;
        Country country = null;
        Location location = null;
        ArrayList<Location> listaLocations = new ArrayList();
        
        try {
            dql = "SELECT * " +
                    "FROM REGIONS R, COUNTRIES C, LOCATIONS L " +
                    "WHERE R.REGION_ID = C.REGION_ID " +
                    "  AND C.COUNTRY_ID = L.COUNTRY_ID";
            sentenciaPreparada = conexion.prepareStatement(dql);
            sentenciaPreparada.executeQuery();

            ResultSet resultado = sentenciaPreparada.executeQuery(dql);
            while (resultado.next()) {
                region = new Region();
                region.setRegionId(resultado.getInt("REGION_ID"));
                region.setRegionName(resultado.getString("REGION_NAME"));
                country = new Country();
                country.setCountryId(resultado.getString("COUNTRY_ID"));
                country.setCountryName(resultado.getString("COUNTRY_NAME"));
                country.setRegion(region);
                location = new Location();
                location.setLocationId(resultado.getInt("LOCATION_ID"));
                location.setStreetAddress(resultado.getString("STREET_ADDRESS"));
                location.setPostalCode(resultado.getString("POSTAL_CODE"));
                location.setCity(resultado.getString("CITY"));
                location.setStateProvince(resultado.getString("STATE_PROVINCE"));
                location.setCountry(country);
                listaLocations.add(location);
            }
            resultado.close();
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(
                    ex.getErrorCode(), 
                    ex.getMessage(), 
                    "Error general del sistema. Consulte con el administrador.", 
                    dql);
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return listaLocations;
    }

    /**
     * Inserta un departamento en la base de datos
     * @author Ignacio Fontecha Hernández
     * @param department Departmento a insertar
     * @return Cantidad de departamentos insertados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int insertarDepartment(Department department) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            dml = "insert into DEPARTMENTS (DEPARTMENT_ID, DEPARTMENT_NAME,MANAGER_ID,LOCATION_ID)"
                    + " values(?,?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setInt(1, department.getDepartmentId());
            sentenciaPreparada.setString(2, department.getDepartmentName());
            sentenciaPreparada.setInt(3, department.getManager().getEmployeeId());
            sentenciaPreparada.setInt(4, department.getLocation().getLocationId());
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
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
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Elimina un departamento de la base de datos
     * @author Ignacio Fontecha Hernández
     * @param departmentId Identificador del departamento a eliminar
     * @return Cantidad de departamentos eliminados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int borrarDepartment(int departmentId) throws ExcepcionHR {
        Statement sentencia = null;
        String dml = null;
        int registrosAfectados = -1;
        try {
            sentencia = conexion.createStatement();

            dml = "delete from departments where DEPARTMENT_ID=" + departmentId;
            registrosAfectados = sentencia.executeUpdate(dml);
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("Error: No se puede eliminar el departamento ya que tiene empleados o registros historicos");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                    break;
            }
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Modifica un departamento de la base de datos
     * @author Ignacio Fontecha Hernández
     * @param departmentId Identificador del departamento a modificar
     * @param department Nuevos datos del departamento a modificar
     * @return Cantidad de departamentos modificados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int modificarDepartment(int departmentId, Department department) throws ExcepcionHR {
        Statement sentencia = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            sentencia = conexion.createStatement();

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
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("La localidad elegida no existe o el jefe de departamento no es un empleado de la empresa");
                    break;
                case 1407:
                    excepcionHR.setMensajeErrorUsuario("El identificador y el nombre de departmento son obligatorios");
                    break;
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador de departmento ya que tiene empleados asociadeos o datos históricos asociados");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("El identificador de departmento ya existe");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Consulta un departamento de la base de datos
     * @author Ignacio Fontecha Hernández
     * @param departmentId Identificador del departmento a consultar
     * @return Departmento a consultar
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public Department leerDepartment(int departmentId) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dql = null;
        Department d = new Department();
        try {
            dql = "select * from DEPARTMENTS where DEPARTMENT_ID = ?";
            sentenciaPreparada = conexion.prepareStatement(dql);
            sentenciaPreparada.setInt(1, departmentId);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            d.setDepartmentId(resultado.getInt("DEPARTMENT_ID"));
            d.setDepartmentName(resultado.getString("DEPARTMENT_NAME"));
            Location l = new Location();
            l.setLocationId(resultado.getInt("LOCATION_ID"));
            d.setLocation(l);
            Employee e = new Employee();
            e.setEmployeeId(resultado.getInt("MANAGER_ID"));
            d.setManager(e);
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(
                    ex.getErrorCode(),
                    ex.getMessage(),
                    "Error general del sistema. Consulte con el administrador",
                    dql);
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return d;
    }

    /**
     * Consulta todos los departamentos de la base de datos
     * @author Ricardo Pérez Barreda
     * @return Lista de todos los departamentos
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public ArrayList<Department> leerDepartments() throws ExcepcionHR {
        Statement sentencia = null;
        String dql = null;
        ArrayList<Department> a = new ArrayList<Department>();
        try {
            sentencia = conexion.createStatement();
            dql = "select * from DEPARTMENTS";
            ResultSet resultados = sentencia.executeQuery(dql);
            while (resultados.next()) {
                Department d = new Department();
                d.setDepartmentId(resultados.getInt("DEPARTMENT_ID"));
                d.setDepartmentName(resultados.getString("DEPARTMENT_NAME"));
                Location l = new Location();
                l.setLocationId(resultados.getInt("LOCATION_ID"));
                d.setLocation(l);
                Employee e = new Employee();
                e.setEmployeeId(resultados.getInt("MANAGER_ID"));
                d.setManager(e);
                a.add(d);
            }
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(
                    ex.getErrorCode(),
                    ex.getMessage(),
                    "Error general del sistema. Consulte con el administrador",
                    dql);
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return a;
    }

    /**
     * Inserta un empleado en la base de datos
     * @author Ignacio Fontecha Hernández
     * @param employee Empleado a insertar
     * @return Cantidad de empleados insertados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int insertarEmployee(Employee employee) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            dml = "INSERT INTO EMPLOYEES"
                    + "(EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(dml);
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
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("El departamento, el trabajo o el jefe no existen.");
                    break;
                case 1400:
                    excepcionHR.setMensajeErrorUsuario("El email, la fecha de contratación, el apellido y el trabajo son obligatorios.");
                    break;
                case 2290:
                    excepcionHR.setMensajeErrorUsuario("El salario tiene que ser mayor que 0.");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("El identificador de usuario y el email no pueden repetirse.");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Elimina un empleado de la base de datos
     * @author Jonathan León Lorenzo
     * @param employeeId Identificador del empleado a eliminar
     * @return Cantidad de empleados eliminados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int borrarEmployee(int employeeId) throws ExcepcionHR {
        CallableStatement sentenciaLlamable = null;
        String llamada = null;
        int registrosAfectados = 0;
        try {
            llamada = "call BORRAR_EMPLOYEE(?)";
            sentenciaLlamable = conexion.prepareCall(llamada);
            sentenciaLlamable.setInt(1, employeeId);
            registrosAfectados = sentenciaLlamable.executeUpdate();
            sentenciaLlamable.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), "Error general del sistema. Consulte con el administrador.", null);
            switch (ex.getErrorCode()) {
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("No se puede borrar porque tiene un historial asociado");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            cerrarConexion(conexion, sentenciaLlamable);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Modifica un empleado de la base de datos
     * @author Ricardo Pérez Barreda
     * @param employeeId Identificador del empleado a modificar
     * @param employee Nuevos datos del empleado a modificar
     * @return Cantidad de empleados modificados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int modificarEmployee(int employeeId, Employee employee) throws ExcepcionHR {
        Statement sentencia = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            sentencia = conexion.createStatement();

            dml = "update EMPLOYEES set "
                    + "EMPLOYEE_ID = " + employee.getEmployeeId() + ","
                    + "FIRST_NAME = '" + employee.getFirstName() + "',"
                    + "LAST_NAME = '" + employee.getLastName() + "',"
                    + "EMAIL = '" + employee.getEmail() + "',"
                    + "PHONE_NUMBER = '" + employee.getPhoneNumber() + "',"
                    + "HIRE_DATE = '" + employee.getHireDate() + "',"
                    + "JOB_ID = '" + employee.getJob().getJobId() + "',"
                    + "SALARY = " + employee.getSalary() + ","
                    + "COMMISSION_PCT = " + employee.getCommissionPct() + ","
                    + "MANAGER_ID = " + employee.getManager().getEmployeeId() + ","
                    + "DEPARTMENT_ID = " + employee.getDepartment().getDepartmentId() + " "
                    + "where EMPLOYEE_ID = " + employeeId;
            registrosAfectados = sentencia.executeUpdate(dml);

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("El jefe de empleado, el trabajo o el departamento seleccionado no existen");
                    break;
                case 1407:
                    excepcionHR.setMensajeErrorUsuario("Los campos: Identificador de empleado, Email, Fecha de contratación, "
                            + "Apellido y Trabajo son obligatorios");
                    break;
                case 2290:
                    excepcionHR.setMensajeErrorUsuario("El salario no puede ser menor o igual que 0");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("Tanto el Email como el Identificador de empleado no pueden repetirse");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Consulta un empleado de la base de datos
     * @author Alberto Martínez - Pilar Sánchez
     * @param employeeId Identificador del empleado a consultar
     * @return Empleado a consultar
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public Employee leerEmployee(int employeeId) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dql = null;
        Employee e = new Employee();
        try {
            Job j = new Job();
            Employee m = new Employee();
            Department d = new Department();
            dql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID= ?";
            sentenciaPreparada = conexion.prepareStatement(dql);
            sentenciaPreparada.setInt(1, employeeId);
            ResultSet resultado = sentenciaPreparada.executeQuery();
            while (resultado.next()) {
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
            ExcepcionHR excepcionHR = new ExcepcionHR(
                    ex.getErrorCode(),
                    ex.getMessage(),
                    "Error general del sistema. Consulte con el administrador",
                    dql);
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return e;
    }

    /**
     * Consulta todos los empleados de la base de datos
     * @author Alberto Martínez - Pilar Sánchez
     * @return Lista de todos los empleados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public ArrayList<Employee> leerEmployees() throws ExcepcionHR {
        Statement sentencia = null;
        String dql = null;
        ArrayList<Employee> a = new ArrayList<Employee>();
        Employee e = null;
        Employee m = null;
        Job j = null;
        Department d = null;
        try {
            sentencia = conexion.createStatement();
            dql = "select * from EMPLOYEES";
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                e = new Employee();
                e.setEmployeeId(resultado.getInt("EMPLOYEE_ID"));
                e.setFirstName(resultado.getString("FIRST_NAME"));
                e.setLastName(resultado.getString("LAST_NAME"));
                e.setEmail(resultado.getString("EMAIL"));
                e.setPhoneNumber(resultado.getString("PHONE_NUMBER"));
                e.setHireDate(resultado.getDate("HIRE_DATE"));
                j = new Job();
                j.setJobId(resultado.getString("JOB_ID"));
                e.setJob(j);
                e.setSalary(resultado.getDouble("SALARY"));
                e.setCommissionPct(resultado.getDouble("COMMISSION_PCT"));

                Employee miEmpleado = new Employee();
                e.setEmployeeId(resultado.getInt("EMPLOYEE_ID"));
                e.setManager(m);

                d = new Department();
                d.setDepartmentId(resultado.getInt("DEPARTMENT_ID"));
                e.setJob(j);
                e.setDepartment(d);

                a.add(e);

            }

            resultado.close();

            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(
                    ex.getErrorCode(),
                    ex.getMessage(),
                    "Error general del sistema. Consulte con el administrador",
                    dql);
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return a;
    }

    /**
     * Inserta un trabajo en la base de datos
     * @author Ignacio Fontecha Hernández
     * @param job Trabajo a insertar
     * @return Cantidad de trabajos insertados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int insertarJob(Job job) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            dml = "insert into jobs(job_id,job_title,min_salary,max_salary) values(?,?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, job.getJobId());
            sentenciaPreparada.setString(2, job.getJobTitle());
            sentenciaPreparada.setInt(3, job.getMinSalary());
            sentenciaPreparada.setInt(4, job.getMaxSalary());
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 1400:
                    excepcionHR.setMensajeErrorUsuario("El identificador del trabajo o el nombre del trabajo no puede ser nulo");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("El identificador del trabajo no puede repetirse.");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador.");
                    break;
            }
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    /**
     * Elimina un trabajo de la base de datos
     * @author Alberto Martínez - Pilar Sánchez
     * @param jobId Identificador del trabajo a eliminar
     * @return Cantidad de trabajos eliminados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int borrarJob(String jobId) throws ExcepcionHR {
        CallableStatement sentenciaLlamable = null;
        String llamada = null;
        int registrosAfectados = 0;
        try {
            llamada = "call BORRAR_JOB(?)";
            sentenciaLlamable = conexion.prepareCall(llamada);
            sentenciaLlamable.setString(1, jobId);
            registrosAfectados = sentenciaLlamable.executeUpdate();
            sentenciaLlamable.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, llamada);
            switch (ex.getErrorCode()) {
                case 2292:
                    System.out.printf("No se puede borrar el trabajo porque tiene empleados o historiales de trabajo adheridos.");
                    break;
                default:
                    System.out.println("Error en el sistema. Consulta con el administrador");
                    break;
            }
            cerrarConexion(conexion, sentenciaLlamable);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Modifica un trabajo de la base de datos
     * @author Pilar Sánchez - Alberto Martínez
     * @param jobId Identificador del trabajo a modificar
     * @param job Nuevos datos del trabajo a modificar
     * @return Cantidad de trabajos modifcados en la base de datos
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int modificarJob(String jobId, Job job) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            dml = "update JOBS set JOB_ID = ? , JOB_TITLE = ? , MIN_SALARY = ? , MAX_SALARY = ? where JOB_ID = ?";
            sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, job.getJobId());
            sentenciaPreparada.setString(2, job.getJobTitle());
            sentenciaPreparada.setInt(3, job.getMinSalary());
            sentenciaPreparada.setInt(4, job.getMaxSalary());
            sentenciaPreparada.setString(5, jobId);
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 1407:
                    excepcionHR.setMensajeErrorUsuario("El nombre del trabajo es obligatorio");
                    break;
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador de trabajo ya que tiene empleados o historiales asociados");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("El identificador de empleo ya existe");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Consulta un trabajo de la base de datos
     * @author Adela Verdeja
     * @param jobId Identificador del trabajo a consultar
     * @return Trabajo a consultar
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public Job leerJob(String jobId) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dql = null;
        Job job = null;
        try {
            dql = "SELECT * FROM JOBS WHERE JOB_ID = ? ";
            sentenciaPreparada = conexion.prepareStatement(dql);
            sentenciaPreparada.setString(1, jobId);
            sentenciaPreparada.executeQuery();
            ResultSet resultado = sentenciaPreparada.executeQuery(dql);
            while (resultado.next()) {
                job = new Job();
                job.setJobId(resultado.getString("JOB_ID"));
                job.setJobTitle(resultado.getString("JOB_TITLE"));
                job.setMinSalary(resultado.getInt("MIN_SALARY"));
                job.setMaxSalary(resultado.getInt("MAX_SALARY"));
            }
            resultado.close();
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(
			ex.getErrorCode(), 
			ex.getMessage(), 
			"Error General del Sistema. Consulte con el administrador", 
			dql);
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return job;
    }
    
    /**
     * Consulta todos los trabajos de la base de datos
     * @author Carlos Labrador
     * @return Lista de todos los trabajos
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public ArrayList<Job> leerJobs() throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dql = null;
        Job job = null;
        ArrayList<Job> listaJobs = new ArrayList();
        
        try {
            dql = "select * from JOBS";
            sentenciaPreparada = conexion.prepareStatement(dql);
            sentenciaPreparada.executeQuery();

            ResultSet resultado = sentenciaPreparada.executeQuery(dql);
            while (resultado.next()) {
                job = new Job();
                job.setJobId(resultado.getString("JOB_ID"));
                job.setJobTitle(resultado.getString("JOB_TITLE"));
                job.setMinSalary(resultado.getInt("MIN_SALARY"));
                job.setMaxSalary(resultado.getInt("MAX_SALARY"));
                listaJobs.add(job);
            }
            resultado.close();
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(
                    ex.getErrorCode(), 
                    ex.getMessage(), 
                    "Error general del sistema. Consulte con el administrador.", 
                    dql);
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }
        return listaJobs;
    }
    
    /**
     * Inserta un dato histórico de trabajo y/o departamento de un empleado en
     * la base de datos
     * @author Rubén Argumosa Roiz.
     * @param jobHistory dato histórico de trabajo y/o departamento de un
     * empleado a insertar
     * @return Cantidad de datos históricos de trabajo y/o departamento de un
     * empleado insertados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int insertarJobHistory(JobHistory jobHistory) throws ExcepcionHR {
        PreparedStatement sentenciaPreparada = null;
        String dml = null;
        int registrosAfectados = 0;
        try {
            dml = "INSERT JOB_HISTORY"
                    + "(EMPLOYEE_ID,START_DATE,END_DATE,JOB_ID,DEPARTMENT_ID) "
                    + "VALUES (?,?,?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setInt(1, jobHistory.getEmpleado().getEmployeeId());
            sentenciaPreparada.setDate(2, jobHistory.getStartDate());
            sentenciaPreparada.setDate(3, jobHistory.getEndDate());
            sentenciaPreparada.setString(4, jobHistory.getJob().getJobId());
            sentenciaPreparada.setInt(5, jobHistory.getDepartment().getDepartmentId());
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("Error: se ha producido uno de los siguientes errores:El departamento seleccionado no existe,El empleado no existe,El trabajo escogido no existe");
                    break;
                case 1400:
                    excepcionHR.setMensajeErrorUsuario("Identificador de empleado, fecha de entrada,y fecha de salida son obligatorios.");
                    break;
                case 2290:
                    excepcionHR.setMensajeErrorUsuario("Error: la fecha de entrada no pude ser mayor a la de salida");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("El identificador de empleado y el email no pueden repetirse.");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error: el identificador de empleado no puede repetirse, en el mismo dia.");
                    break;
            }
            cerrarConexion(conexion, sentenciaPreparada);
            throw excepcionHR;
        }

        return registrosAfectados;

    }

    /**
     * Elimina un dato histórico de trabajo y/o departamento de un empleado en
     * la base de datos
     * @author Adela Verdeja
     * @param employeeId identificador del empleado asociado al dato histórico a
     * eliminar
     * @param startDate fecha de inicio asociada al dato histórico a eliminar
     * @return Cantidad de datos históricos eliminados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int borrarJobHistory(int employeeId, java.sql.Date startDate) throws ExcepcionHR {
        CallableStatement sentenciaLlamable = null;
        String llamada = null;
        int registrosAfectados = 0;
        try {
            llamada = "call BORRAR_JOB_HISTORY(?,?)";
            sentenciaLlamable = conexion.prepareCall(llamada);
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
            cerrarConexion(conexion, sentenciaLlamable);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Modifica un dato histórico de trabajo y/o departamento de un empleado en
     * la base de datos
     * @author Rubén Argumosa Roiz
     * @param employeeId identificador del empleado asociado al dato histórico a
     * modificar
     * @param startDate fecha de inicio asociada al dato histórico a modificar
     * @param jobHistory Nuevos datos del dato histórico a modificar
     * @return Cantidad de datos históricos modificados
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public int modificarJobHistory(int employeeId, java.sql.Date startDate, JobHistory jobHistory) throws ExcepcionHR {
        CallableStatement sentenciaLlamable = null;
        String llamada = null;
        int registrosAfectados = 0;
        try {
            llamada = "call MODIFICAR_JOBHISTORY(?,?,?,?,?,?,?)";
            sentenciaLlamable = conexion.prepareCall(llamada);
            sentenciaLlamable.setInt(1, jobHistory.getEmpleado().getEmployeeId());
            sentenciaLlamable.setDate(2, jobHistory.getStartDate());
            sentenciaLlamable.setDate(3, jobHistory.getEndDate());
            sentenciaLlamable.setString(4, jobHistory.getJob().getJobId());
            sentenciaLlamable.setInt(5, jobHistory.getDepartment().getDepartmentId());
            sentenciaLlamable.setInt(6, employeeId);
            sentenciaLlamable.setDate(7, startDate);
            registrosAfectados = sentenciaLlamable.executeUpdate();
            sentenciaLlamable.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), "Error general del sistema. Consulte con el administrador.", null);
            switch (ex.getErrorCode()) {
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("Error: se ha producido uno de los siguientes errores:El departamento seleccionado no existe,El empleado no existe,El trabajo escogido no existe");
                    break;
                case 1407:
                    excepcionHR.setMensajeErrorUsuario("Identificador de empleado, fecha de entrada,y fecha de salida son obligatorios.");
                    break;
                case 2290:
                    excepcionHR.setMensajeErrorUsuario("Error: la fecha de entrada no pude ser mayor a la de salida");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("El identificador de empleado y el email no pueden repetirse.");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error: inesperado, consulte con el administrador.");
                    break;
            }
            cerrarConexion(conexion, sentenciaLlamable);
            throw excepcionHR;
        }
        return registrosAfectados;
    }

    /**
     * Consulta un dato histórico de trabajo y/o departamento de un empleado en
     * la base de datos
     * @author Rodrigo Corsini
     * @param employeeId identificador del empleado asociado al dato histórico a
     * consultar
     * @param startDate fecha de inicio asociada al dato histórico a consultar
     * @return dato histórico de trabajo y/o departamento de un empleado a
     * consultar
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public JobHistory leerJobHistory(int employeeId, Date startDate) throws ExcepcionHR {
        Statement sentencia = null;
        String dql = null;
        JobHistory j = new JobHistory();
        try {
            sentencia = conexion.createStatement();
            dql = "select * from job_history where EMPLOYEE_ID=" + employeeId + " and " + "START_DATE=" + startDate;
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                Employee e = new Employee();
                e.setEmployeeId(employeeId);
                j.setEmpleado(e);
                j.setStartDate(startDate);
                j.setEndDate(resultado.getDate("END_DATE"));
                Job r = new Job();
                r.setJobId(resultado.getString("JOB_ID"));
                j.setJob(r);
                Department a = new Department();
                a.setDepartmentId(resultado.getInt("DEPARTMENT_ID"));
                j.setJob(r);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dql);
            switch (ex.getErrorCode()) {

                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return j;
    }

    /**
     * Consulta todos los datos históricos de trabajo y/o departamento de la
     * base de datos
     * @author Rodrigo Corsini
     * @return Lista de todos los datos históricos
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    public ArrayList<JobHistory> leerJobHistorys() throws ExcepcionHR {
        Statement sentencia = null;
        String dql = null;
        ArrayList<JobHistory> a = new ArrayList<JobHistory>();
        try {
            sentencia = conexion.createStatement();

            dql = "select * from job_history";
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                JobHistory j = new JobHistory();
                Employee e = new Employee();
                e.setEmployeeId(resultado.getInt("EMPLOYEE_ID"));
                j.setEmpleado(e);
                j.setStartDate(resultado.getDate("START_DATE"));
                j.setEndDate(resultado.getDate("END_DATE"));
                Job r = new Job();
                r.setJobId(resultado.getString("JOB_ID"));
                j.setJob(r);
                Department d = new Department();
                d.setDepartmentId(resultado.getInt("DEPARTMENT_ID"));
                j.setJob(r);
                a.add(j);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), "Error general del sistema. Consulte con el administrador", dql);
            cerrarConexion(conexion, sentencia);
            throw excepcionHR;
        }
        return a;
    }
}
