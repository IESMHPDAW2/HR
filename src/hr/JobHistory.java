package hr;

import java.util.Date;

/**
 * Clase que representa el historial de trabajos de un empleado.
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
     * @param empleado Objeto de la clase Employee correspondiente al empleado
     * @param startDate Objeto de la clase Date correspondiente a la fecha de contratacion
     * @param endDate Objeto de la clase Date correspondiente la fecha de finalizacion del trabajo
     * @param job Objeto de la clase Job correspondiente al trabajo
     * @param department Objeto de la case Department correspondiente al departamento
     */
    public JobHistory(Employee empleado, Date startDate, Date endDate, Job job, Department department) {
        this.empleado = empleado;
        this.startDate = startDate;
        this.endDate = endDate;
        this.job = job;
        this.department = department;
    }

    
    /**
    * Getter del Empleado
    * @return Objeto de la clase Employee con los datos del empleado
    */
    public Employee getEmpleado() {
        return empleado;
    }
    
    
    /**
    * Setter del Empleado de JobHistory
    * @param empleado Objeto de la clase Employee con los datos del Empleado
    */
    public void setEmpleado(Employee empleado) {
        this.empleado = empleado;
    }

    
    /**
    * Getter de la fecha de contratacion
    * @return Objeto clase Date con fecha de contratacion
    */
    public Date getStartDate() {
        return startDate;
    }

    
    /**
    * Setter de la fecha de contratacion de JobHistory
    * @param startDate Objeto de la clase Date con la fecha de contratacion
    */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
    * Getter de la fecha de fin del trabajo
    * @return Objeto clase Date con fecha de fin del contrato
    */
    public Date getEndDate() {
        return endDate;
    }

    
    /**
    * Setter de la fecha de finalizacion del contrato de JobHistory
    * @param endDate Objeto de la clase Date con la fecha de finalizacion
    */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    /**
    * Getter de Trabajo
    * @return Objeto clase Job con los datos del trabajo
    */
    public Job getJob() {
        return job;
    }

    
    /**
    * Setter del trabajo de jobHistory
    * @param job Objeto de la clase Job con los datos del trabajo
    */
    public void setJob(Job job) {
        this.job = job;
    }

    
    /**
    * Getter del departamento
    * @return Objeto clase Department con los datos de departamento
    */
    public Department getDepartment() {
        return department;
    }

    
    /**
    * Setter del departamento de jobHistory
    * @param department Objeto de la clase Department con los datos del departamento
    */
    public void setDepartment(Department department) {
        this.department = department;
    }
    
}
