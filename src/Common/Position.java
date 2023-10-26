package Common;

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
        this.posX = 0;
        this.posY = 0;
        this.posY = 0;
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
}
