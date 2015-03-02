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
public class Anillo {
    private float radioExterno;
    private float radioInterno;
    private float velRotacion;
    
    public Anillo(float radioExt, float radioInt,float velR){
        this.radioExterno=radioExt;
        this.radioInterno=radioInt;
        this.velRotacion=velR;
              
    }

    /**
     * @return the radioExterno
     */
    public float getRadioExterno() {
        return radioExterno;
    }

    /**
     * @param radioExterno the radioExterno to set
     */
    public void setRadioExterno(float radioExterno) {
        this.radioExterno = radioExterno;
    }

    /**
     * @return the radioInterno
     */
    public float getRadioInterno() {
        return radioInterno;
    }

    /**
     * @param radioInterno the radioInterno to set
     */
    public void setRadioInterno(float radioInterno) {
        this.radioInterno = radioInterno;
    }

    /**
     * @return the velRotacion
     */
    public float getVelRotacion() {
        return velRotacion;
    }

    /**
     * @param velRotacion the velRotacion to set
     */
    public void setVelRotacion(float velRotacion) {
        this.velRotacion = velRotacion;
    }
}
