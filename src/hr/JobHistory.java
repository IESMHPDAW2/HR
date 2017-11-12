package hr;

import java.sql.Date;

/**
 * Clase que representa un dato histórico de trabajo y/o departamento de 
 * un empleado
 * @author Jonathan
 * @version 1.0
 */
public class JobHistory {
    private Employee empleado;
    private Date startDate;
    private Date endDate;
    private Job job;
    private Department department;

    /**
     * Constructor vacio
     */
    public JobHistory() {
    }

    /**
     * Constructor completo
     * @param empleado Empleado objeto del dato histórico
     * @param startDate Fecha de inicio del dato histórico
     * @param endDate Fecha de fin del dato histórico
     * @param job Trabajo que desarrollaba el empleado entre las fechas de inicio y fin
     * @param department Departamento al que pertenecía el empleado entre las fechas de inicio y fin
     */
    public JobHistory(Employee empleado, Date startDate, Date endDate, Job job, Department department) {
        this.empleado = empleado;
        this.startDate = startDate;
        this.endDate = endDate;
        this.job = job;
        this.department = department;
    }

    /**
     * Getter del empleado objeto del dato histórico
     * @return Empleado objeto del dato histórico
     */
    public Employee getEmpleado() {
        return empleado;
    }
    
    /**
     * Setter del empleado objeto del dato histórico
     * @param empleado Empleado objeto del dato histórico
     */
    public void setEmpleado(Employee empleado) {
        this.empleado = empleado;
    }
    
    /**
     * Getter de la fecha de inicio del dato histórico
     * @return Fecha de inicio del dato histórico
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Setter de la fecha de inicio del dato histórico
     * @param startDate Fecha de inicio del dato histórico
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter de la fecha de fin del dato histórico
     * @return Fecha de fin del dato histórico
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Setter de la fecha de fin del dato histórico
     * @param endDate Fecha de fin del dato histórico
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter del trabajo que desarrollaba el empleado entre las fechas de inicio y fin
     * @return Trabajo que desarrollaba el empleado entre las fechas de inicio y fin
     */
    public Job getJob() {
        return job;
    }

    /**
     * Setter del trabajo que desarrollaba el empleado entre las fechas de inicio y fin
     * @param job Trabajo que desarrollaba el empleado entre las fechas de inicio y fin
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * Getter del departamento al que pertenecía el empleado entre las fechas de inicio y fin
     * @return Departamento al que pertenecía el empleado entre las fechas de inicio y fin
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Setter del departamento al que pertenecía el empleado entre las fechas de inicio y fin
     * @param department Departamento al que pertenecía el empleado entre las fechas de inicio y fin
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Método toString de la clase
     * @return Texto con todos los datos de un dato histórico de trabajo y/o
     * departamento de un empleado
     */
    @Override
    public String toString() {
        return "JobHistory{" + "empleado=" + empleado + ", startDate=" + startDate + ", endDate=" + endDate + ", job=" + job + ", department=" + department + '}';
    }
}
