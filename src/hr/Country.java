package hr;

/**
 * Clase que representa un país
 * @author ruben
 * @version 1.0
 */
public class Country {
    private String countryId;
    private String countryName;
    private Region region;
    
    /**
     * Constructor vacío
     */
    public Country() {
    }

    /**
     * Constructor Completo
     * @param countryId Identificador del país
     * @param countryName Nombre del país
     * @param region Continente en el que se ubica el país
     */
    public Country(String countryId, String countryName, Region region) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.region = region;
    }
    
    /**
     * Getter del identificador de país
     * @return Identificador del país
     */
    public String getCountryId() {
        return countryId;
    }
    
    /**
     * Setter del identificador de país
     * @param countryId Identificador del país
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
    
    /**
     * Getter del nombre del país.
     * @return Nombre del pais
     */
    public String getCountryName() {
        return countryName;
    }
    
    /**
     * Setter del nombre de país
     * @param countryName  Nombre del país
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    /**
     * Getter del continente en el que se ubica el país
     * @return Continente en el que se ubica el país
     */
    public Region getRegion() {
        return region;
    }
    
    /**
     * Setter del continente en el que se ubica el país
     * @param region Continente en el que se ubica el país
     */
    public void setRegion(Region region) {
        this.region = region;
    }

     /**
     * Método toString de la clase
     * @return Texto con todos los datos del país
     */
   @Override
    public String toString() {
        return "Country{" + "countryId=" + countryId + ", countryName=" + countryName + ", region=" + region + '}';
    }
}
