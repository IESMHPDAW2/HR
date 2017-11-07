package hr;

/**
 * Clase que representa un trabajo.
 * @author Jonathan
 * @version 1.0
 */
public class Job {
    private String jobId;
    private String jobTitle;
    private int minSalary;
    private int maxSalary;

    
    /**
     * Constructor vacio
     */
    public Job() {
    }
    
    /**
     * Constructor completo
     * @param JobId Identificador del trabajo
     * @param jobTitle Nombre del trabajo
     * @param minSalary Cantidad de salario minimo
     * @param maxSalary Cantidad de salario maximo
     */
    public Job(String jobId, String jobTitle, int minSalary, int maxSalary) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    /**
    * Getter del identificador del trabajo
    * @return Identificador del trabajo
    */
    public String getJobId() {
        return jobId;
    }

    
    /**
    * Setter del identificador del trabajo
    * @param jobId Identificador del trabajo
    */
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    
    /**
     * Getter del Nombre del trabajo
     * @return Nombre del trabajo
     */
    public String getJobTitle() {
        return jobTitle;
    }
    
    
    /**
    * Setter del Nombre del trabajo
    * @param jobTitle Nombre del trabajo
    */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    
    /**
     * Getter del Salario minimo del trabajo
     * @return Salario minimo del trabajo
     */
    public int getMinSalary() {
        return minSalary;
    }

    
    /**
    * Setter del Salario minimo del trabajo
    * @param minSalary Salario minimo del trabajo
    */
    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    
     /**
     * Getter del Salario maximo del trabajo
     * @return Salrio maximo del trabajo
     */
    public int getMaxSalary() {
        return maxSalary;
    }

    
    /**
    * Setter del Salario maximo del trabajo
    * @param maxSalary Salario maximo del trabajo
    */
    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }
    
    
}
