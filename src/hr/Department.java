
package hr;

/**
 * Clase que represanta un departamento.
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
     * @param manager Objeto de la clase Employee correspondiente al departamento
     * @param location Objeto de la clase Location correspondiente al departamento
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
     * Getter del objeto de la clase Employee
     * @return Objeto de la clase Employee
     */
    public Employee getManager() {
        return manager;
    }

    /**
     * Setter del objeto de la clase Employee
     * @param manager Objeto de la clase Employee
     */
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    /**
     * Getter del objeto de la clase Location
     * @return Objeto de la clase Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Setter del objeto de la clase Location
     * @param location Objeto de la clase Location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
       
}
