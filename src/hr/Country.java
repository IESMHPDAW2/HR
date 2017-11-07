/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
/**
 * Getter del Nombre del país.
 * @return Nombre del pais
 */
    public String getCountryName() {
        return countryName;
    }
/**
 * Setter del Nombre de país.
 * @param countryName  Nombre del país.
 */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
/**
 * Getter del objeto Continente.
 * @return Objeto contiente.
 */
    public Region getRegion() {
        return region;
    }
/**
 * Setter del Objeto Continente.
 * @param region Objeto contienente.
 */
    public void setRegion(Region region) {
        this.region = region;
    }
    
}
