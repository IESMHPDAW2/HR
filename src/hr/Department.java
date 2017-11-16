package hr;

/**
 * Clase que representa un departamento.
 * @author david
 * @version 1.0
 */
public class Department {
    private int departmentId;
    private String departmentName;
    private Employee manager;
    private Location location;

    /**
     * Constructor vacio
     */
    public Department() {
    }

    /**
     * Constructor completo
     * @param departmentId Identificador del departamento
     * @param departmentName Nombre del departamento
     * @param manager Responsable del departamento
     * @param location Localidad en la que se ubica el departamento
     */
    public Department(int departmentId, String departmentName, Employee manager, Location location) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.manager = manager;
        this.location = location;
    }

    /**
     * Getter del identificador del departamento
     * @return Identificador del departamento
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * Setter del identificador del departamento
     * @param departmentId Identificador del departamento
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Getter del nombre del departamento
     * @return Nombre del departamento
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Setter del nombre del departamento
     * @param departmentName Nombre del departamento
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Getter del responsable del departamento
     * @return Responsable del departamento
     */
    public Employee getManager() {
        return manager;
    }

    /**
     * Setter del responsable del departamento
     * @param manager Responsable del departamento
     */
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    /**
     * Getter de la localidad en la que se ubica el departamento
     * @return Localidad en la que se ubica el departamento
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Setter de la localidad en la que se ubica el departamento
     * @param location Localidad en la que se ubica el departamento
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Método toString de la clase
     * @return Texto con todos los datos del departamento
     */
    @Override
    public String toString() {
        return "Department{" + "departmentId=" + departmentId + ", departmentName=" + departmentName + ", manager=" + manager + ", location=" + location + '}';
    }
}
