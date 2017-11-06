
package hr;

/**
 * Clase que representa un país
 * @author usuario
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
 * @param region Objeto de la clase Regions correspondiente al país
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
    
}
