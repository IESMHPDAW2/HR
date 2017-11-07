package hr;

import java.sql.Date;

/**
 * Clase que representa un Empleado
 *
 * @author Corsini - Delegado
 * @version 1.0
 */
public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private Job job;
    private double salary;
    private double commissionPct;
    private Employee manager;
    private Department department;

    /**
     * Constructor vacío
     */
    public Employee() {
    }

    /**
     * Constructor Completo
     *
     * @param employeeId
     * @param firstName
     * @param lastName
     * @param email
     * @param phoneNumber
     * @param hireDate
     * @param job
     * @param salary
     * @param commissionPct
     * @param manager
     * @param department
     */
    public Employee(int employeeId, String firstName, String lastName, String email, String phoneNumber, Date hireDate, Job job, double salary, double commissionPct, Employee manager, Department department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.job = job;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.manager = manager;
        this.department = department;
    }
/**
 * Getter del identificador de Empleado
 * @return Identificador del Empleado
 */
    public int getEmployeeId() {
        return employeeId;
    }
/**
 * Setter del identificador de Empleado
 * @param employeeId Identificador del Empleado
 */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
/**
 * Getter del Nombre de empleado
 * @return Nombre de empleado
 */
    public String getFirstName() {
        return firstName;
    }
/**
 * Setter Nombre de Empleado
 * @param FirstName Nombre del Empleado
 */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
/**
 * Getter del Apellido de empleado
 * @return Apellido de empleado
 */
    public String getLastName() {
        return lastName;
    }
/**
 * Setter Apellido de Empleado
 * @param lastName Apellido del Empleado
 */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
/**
 * Getter del Email de empleado
 * @return Email de empleado
 */
    public String getEmail() {
        return email;
    }
/**
 * Setter Email de Empleado
 * @param email Email del Empleado
 */
    public void setEmail(String email) {
        this.email = email;
    }
/**
 * Getter del Numero de telefono de empleado
 * @return Numero de telefono de empleado
 */
    public String getPhoneNumber() {
        return phoneNumber;
    }
/**
 * Setter Numero de Telefono de Empleado
 * @param phoneNumber  Numero de Telefono de Empleado
 */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
/**
 * Getter de la fecha de contratacion
 * @return fecha de contratacion
 */
    public Date getHireDate() {
        return hireDate;
    }
/**
 * Setter Fecha de contratacion del EMpleado
 * @param hireDate  Fecha de contratacion del EMpleado
 */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
/**
 * Getter del trabajo
 * @return puesto de trabajo
 */
    public Job getJob() {
        return job;
    }
/**
 * Setter Job del Empleado
 * @param job  ID del Job del Empleado
 */
    public void setJob(Job job) {
        this.job = job;
    }
/**
 * Getter del Salario de empleado
 * @return Salario de empleado
 */
    public double getSalary() {
        return salary;
    }
/**
 * Setter salario del Empleado
 * @param salary  Cantidad del salario del Empleado
 */
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
/**
 * Getter de la comision del Empleado
 * @return comision del Empleado
 */
    public double getCommissionPct() {
        return commissionPct;
    }
/**
 * Setter Comision del Empleado
 * @param commissionPct  Comision del Empleado
 */
    public void setCommissionPct(double commissionPct) {
        this.commissionPct = commissionPct;
    }
/**
 * Getter del JEfe del Empleado
 * @return ID del Jefe del Empleado
 */
    public Employee getManager() {
        return manager;
    }
/**
 * Setter del JEfe del Empleado
 * @param commissionPct  ID del Jefe del Empleado
 */
    public void setManager(Employee manager) {
        this.manager = manager;
    }
/**
 * Getter del departamento 
 * @return ID del departamento del Empleado
 */
    public Department getDepartment() {
        return department;
    }
/**
 * Setter del departamento 
 * @param department  ID del departamento del Empleado
 */
    public void setDepartment(Department department) {
        this.department = department;
    }
/**
 * 
 * @return Todos los Datos del Empleado
 */
    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", job=" + job + ", salary=" + salary + ", commissionPct=" + commissionPct + ", manager=" + manager + ", department=" + department + '}';
    }

}
