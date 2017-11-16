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
     * @param jobId Identificador del trabajo
     * @param jobTitle Nombre del trabajo
     * @param minSalary Salario mínimo del trabajo
     * @param maxSalary Salario máximo del trabajo
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
     * Getter del nombre del trabajo
     * @return Nombre del trabajo
     */
    public String getJobTitle() {
        return jobTitle;
    }
    
    /**
     * Setter del nombre del trabajo
     * @param jobTitle Nombre del trabajo
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    /**
     * Getter del salario mínimo del trabajo
     * @return Salario mínimo del trabajo
     */
    public int getMinSalary() {
        return minSalary;
    }
    
    /**
     * Setter del salario mínimo del trabajo
     * @param minSalary Salario mínimo del trabajo
     */
    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }
    
     /**
      * Getter del salario máximo del trabajo
      * @return Salario máximo del trabajo
      */
    public int getMaxSalary() {
        return maxSalary;
    }

    /**
     * Setter del salario máximo del trabajo
     * @param maxSalary Salario máximo del trabajo
     */
    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    /**
     * Método toString de la clase
     * @return Texto con todos los datos del trabajo
     */
    @Override
    public String toString() {
        return "Job{" + "jobId=" + jobId + ", jobTitle=" + jobTitle + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary + '}';
    }
}
