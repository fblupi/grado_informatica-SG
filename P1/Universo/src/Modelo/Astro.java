/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author MiKe
 */
public class Astro {
    private float diametro;
    private float velTraslacion;
    private float velRotacion;
    private float distancia;
    private boolean movimiento;
    private ArrayList<Anillo> anillos; //Lo he puesto como arrayList porq en el diagrama aparece *
    private ArrayList<Astro> satelites;
    private ArrayList<Camara> camara; // Lo he puesto como arrayList porq en el diagrama aparece *
    
    public Astro(float dia,float velT,float velR,float dist,boolean mov){
        diametro=dia;
        velTraslacion=velT;
        velRotacion=velR;
        distancia=dist;
        movimiento=mov;
        
                 
        
        //Añadir al contructor ArrayList con satelites
        //Añadir metodo para añadir satelites al ArrayList de satelites.
        //Añadir metodo para añadir anillos a saturno
        
    }
    
    /**
     * @return the diametro
     */
    public float getDiametro() {
        return diametro;
    }

    /**
     * @param diametro the diametro to set
     */
    public void setDiametro(float diametro) {
        this.diametro = diametro;
    }

    /**
     * @return the periodo
     */
    

    /**
     * @return the distancia
     */
    public float getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    /**
     * @return the movimiento
     */
    public boolean isMovimiento() {
        return movimiento;
    }

    /**
     * @param movimiento the movimiento to set
     */
    public void setMovimiento(boolean movimiento) {
        this.movimiento = movimiento;
    }
}
