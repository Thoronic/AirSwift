package com.AirSwift.AirSwift.Common;

public class Position {
    private double posX;
    private double posY;
    private double posZ;

    public Position(double posX, double posY, double posZ){
        this.posX = posX;
        this.posY = posY;
        this.posY = posZ;
    }

    public Position(){
        this.posX = Double.NEGATIVE_INFINITY;
        this.posY = Double.NEGATIVE_INFINITY;
        this.posY = Double.NEGATIVE_INFINITY;
    }

    public void setNewPos(double posX, double posY, double posZ){
        this.posX = posX;
        this.posY = posY;
        this.posY = posZ;
    }

    public void movePosition(double deltaX, double deltaY, double deltaZ){
        this.posX += deltaX;
        this.posY += deltaY;
        this.posY += deltaZ;
    }

    public double getX(){
        return this.posX;
    }

    public double getY(){
        return this.posY;
    }

    public double getZ(){
        return this.posZ;
    }

    public double getDistance(Position pos2){
        double abs1 = Math.sqrt((this.posX*this.posX) + (this.posY*this.posY) + (this.posZ*this.posZ));
        double abs2 = Math.sqrt((pos2.getX()*pos2.getX()) + (pos2.getY()*pos2.getY()) + (pos2.getZ()*pos2.getZ()));
        return Math.abs(abs1-abs2);
    }
}
