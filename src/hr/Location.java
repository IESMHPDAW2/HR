package hr;

/**
 * Clase que representa una localidad
 * @author david
 * @version 1.0
 */
public class Location {
    private int locationId;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private Country country;

    /**
     * Constructor vacio
     */
    public Location() {
    }

    /**
     * Constructor completo
     * @param locationId Identificador de la localidad
     * @param streetAddress Calle de la localidad
     * @param postalCode Codigo postal de la localidad
     * @param city Ciudad de la localidad
     * @param stateProvince Provincia o estado de la localidad
     * @param country País en el que se ubica la localidad
     */
    public Location(int locationId, String streetAddress, String postalCode, String city, String stateProvince, Country country) {
        this.locationId = locationId;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.country = country;
    }

    /**
     * Getter del identificador de la localidad
     * @return Identificador de la localidad 
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Setter del identificador de la localidad
     * @param locationId Identificador de la localidad
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     * Getter de la calle de la localidad
     * @return Calle de la localidad
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Setter de la calle de la localidad
     * @param streetAddress Calle de la localidad
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Getter del codigo postal de la localidad
     * @return Codigo postal de la localidad
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter del codigo postal de la localidad
     * @param postalCode Codigo postal de la localidad
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter de la ciudad de la localidad
     * @return Ciudad de la localidad
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter de la ciudad de la localidad
     * @param city Ciudad de la localidad
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter de la provincia o estado de la localidad
     * @return Provincia o estado de la localidad
     */
    public String getStateProvince() {
        return stateProvince;
    }

    /**
     * Setter de la provincia o estado de la localidad
     * @param stateProvince Provincia o estado de la localidad
     */
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    /**
     * Getter del país en el que se ubica la localidad
     * @return País en el que se ubica la localidad
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Setter del país en el que se ubica la localidad
     * @param country País en el que se ubica la localidad
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Método toString de la clase
     * @return Texto con todos los datos de la localidad
     */
    @Override
    public String toString() {
        return "Location{" + "locationId=" + locationId + ", streetAddress=" + streetAddress + ", postalCode=" + postalCode + ", city=" + city + ", stateProvince=" + stateProvince + ", country=" + country + '}';
    }
}
