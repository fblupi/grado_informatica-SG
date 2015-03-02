/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author MiKe1
 */
public class Luz {
    private float especular;
    private float ambiental;
    private float difusa;
    private float brillo;
    
    public Luz(float espec,float amb,float dif,float bri){
        this.especular=espec;
        this.ambiental=amb;
        this.difusa=dif;
        this.brillo=bri;
    }

    /**
     * @return the especular
     */
    public float getEspecular() {
        return especular;
    }

    /**
     * @param especular the especular to set
     */
    public void setEspecular(float especular) {
        this.especular = especular;
    }

    /**
     * @return the ambiental
     */
    public float getAmbiental() {
        return ambiental;
    }

    /**
     * @param ambiental the ambiental to set
     */
    public void setAmbiental(float ambiental) {
        this.ambiental = ambiental;
    }

    /**
     * @return the difusa
     */
    public float getDifusa() {
        return difusa;
    }

    /**
     * @param difusa the difusa to set
     */
    public void setDifusa(float difusa) {
        this.difusa = difusa;
    }

    /**
     * @return the brillo
     */
    public float getBrillo() {
        return brillo;
    }

    /**
     * @param brillo the brillo to set
     */
    public void setBrillo(float brillo) {
        this.brillo = brillo;
    }
}
