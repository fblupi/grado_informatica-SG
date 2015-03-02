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
public class Camara {
    private float posX;
    private float posY;
    private float posZ;
    private float dirX;
    private float dirY;
    private float dirZ;
    private float planoDel;
    private float planoTras;
    private float anguloApertura;

    
    public Camara (float posX,float posY,float posZ,float dirX,float dirY,float dirZ,float planoD,float planoT,float anguloAp){
        this.posX=posX;
        this.posY=posY;
        this.posZ=posZ;
        this.dirX=dirX;
        this.dirY=dirY;
        this.dirZ=dirZ;
        this.planoDel=planoD;
        this.planoTras=planoT;
        this.anguloApertura=anguloAp;
    }
    /**
     * @return the posX
     */
    public float getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(float posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public float getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(float posY) {
        this.posY = posY;
    }

    /**
     * @return the posZ
     */
    public float getPosZ() {
        return posZ;
    }

    /**
     * @param posZ the posZ to set
     */
    public void setPosZ(float posZ) {
        this.posZ = posZ;
    }

    /**
     * @return the dirX
     */
    public float getDirX() {
        return dirX;
    }

    /**
     * @param dirX the dirX to set
     */
    public void setDirX(float dirX) {
        this.dirX = dirX;
    }

    /**
     * @return the dirY
     */
    public float getDirY() {
        return dirY;
    }

    /**
     * @param dirY the dirY to set
     */
    public void setDirY(float dirY) {
        this.dirY = dirY;
    }

    /**
     * @return the dirZ
     */
    public float getDirZ() {
        return dirZ;
    }

    /**
     * @param dirZ the dirZ to set
     */
    public void setDirZ(float dirZ) {
        this.dirZ = dirZ;
    }

    /**
     * @return the planoDel
     */
    public float getPlanoDel() {
        return planoDel;
    }

    /**
     * @param planoDel the planoDel to set
     */
    public void setPlanoDel(float planoDel) {
        this.planoDel = planoDel;
    }

    /**
     * @return the plantoTras
     */
    public float getPlantoTras() {
        return planoTras;
    }

    /**
     * @param plantoTras the plantoTras to set
     */
    public void setPlantoTras(float plantoTras) {
        this.planoTras = plantoTras;
    }

    /**
     * @return the anguloApertura
     */
    public float getAnguloApertura() {
        return anguloApertura;
    }

    /**
     * @param anguloApertura the anguloApertura to set
     */
    public void setAnguloApertura(float anguloApertura) {
        this.anguloApertura = anguloApertura;
    }
     
}
