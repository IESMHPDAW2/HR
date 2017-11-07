package hr;

/**
 * Clase que representa un Continente
 * @author ruben
 * @version 1.0
 */
public class Region {
    private int regionId;
    private String regionName;
/**
 * Constructor vacio.
 */
    public Region() {
    }
/**
 * Constructor completo.
 * @param regionId Identificador del continente.
 * @param regionName Nombre del continente.
 */
    public Region(int regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
    }
/**
 * Getter del identificador del continente.
 * @return Identificador del continente.
 */
    public int getRegionId() {
        return regionId;
    }
/**
 * Setter del identificador de continente.
 * @param regionId Identificador del continente.
 */
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
/**
 * Getter del nombre del continente.
 * @return Nombre del continente.
 */
    public String getRegionName() {
        return regionName;
    }
/**
 * Setter del nombre del continente.
 * @param regionName Nombre del continente.
 */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    
}
