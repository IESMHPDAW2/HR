package hr;

import java.sql.Date;

/**
 * Clase que representa un empleado
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
     * Constructor completo
     * @param employeeId Identificador del empleado
     * @param firstName Nombre del empleado
     * @param lastName Apellido del empleado
     * @param email Email del empleado
     * @param phoneNumber Número de teléfono del empleado
     * @param hireDate Fecha de contratación del empleado
     * @param job Trabajo del empleado
     * @param salary Salario del empleado
     * @param commissionPct Porcentaje de comision del empleado
     * @param manager Jefe del empleado
     * @param department Departamento del empleado
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
     * Getter del identificador de empleado
     * @return Identificador del empleado
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
     * Getter del nombre del empleado
     * @return Nombre del empleado
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Setter del nombre del empleado
     * @param firstName Nombre del empleado
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Getter del apellido del empleado
     * @return Apellido del empleado
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Setter del apellido del empleado
     * @param lastName Apellido del empleado
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Getter del email del empleado
     * @return Email del empleado
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Setter del email del empleado
     * @param email Email del empleado
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Getter del número de teléfono del empleado
     * @return Número de teléfono del empleado
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * Setter del número de teléfono del empleado
     * @param phoneNumber Número de teléfono del empleado
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * Getter de la fecha de contratación del empleado
     * @return Fecha de contratación del empleado
     */
    public Date getHireDate() {
        return hireDate;
    }
    
    /**
     * Setter de la fecha de contratación del empleado
     * @param hireDate  Fecha de contratación del empleado
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    
    /**
     * Getter del trabajo del empleado
     * @return Trabajo del empleado
     */
    public Job getJob() {
        return job;
    }
    
    /**
     * Setter del trabajo del empleado
     * @param job Trabajo del empleado
     */
    public void setJob(Job job) {
        this.job = job;
    }
    
    /**
     * Getter del salario del empleado
     * @return Salario del empleado
     */
    public double getSalary() {
        return salary;
    }
    
    /**
     * Setter del salario del empleado
     * @param salary  Salario del empleado
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    /**
     * Getter del porcentaje de comision del empleado
     * @return Porcentaje de comision del empleado
     */
    public double getCommissionPct() {
        return commissionPct;
    }
    
    /**
     * Setter del porcentaje de comision del empleado
     * @param commissionPct  Porcentaje de comision del empleado
     */
    public void setCommissionPct(double commissionPct) {
        this.commissionPct = commissionPct;
    }
    
    /**
     * Getter del jefe del empleado
     * @return Jefe del empleado
     */
    public Employee getManager() {
        return manager;
    }
    
    /**
     * Setter del jefe del empleado
     * @param manager Jefe del empleado
     */
    public void setManager(Employee manager) {
        this.manager = manager;
    }
    
    /**
     * Getter del departamento del empleado
     * @return Departamento del empleado
     */
    public Department getDepartment() {
        return department;
    }
    
    /**
     * Setter del departamento del empleado
     * @param department Departamento del empleado
     */
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    /**
     * Método toString de la clase
     * @return Texto con todos los datos del empleado
     */
    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", job=" + job + ", salary=" + salary + ", commissionPct=" + commissionPct + ", manager=" + manager + ", department=" + department + '}';
    }
}
