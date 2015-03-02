/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author MiKe
 */
public class Sol extends Astro {
    private Luz luz;
    public Sol(float dia, float velR, boolean mov ){
        super(dia,0,velR,0,mov);
        this.luz= new Luz(0.2f,0.5f,0.8f,0.5f); //Datos de ejemplo ¿Pasar por parametro en constructor de sol?
        
    }
}
