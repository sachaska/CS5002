
public class Balloon {

    private String text;

    private double height, width;

    private Point location;

    public Balloon(String text, double h, double w,

                   double x, double y) {
        this.text = text;
        height = h;
        width = w;
        location = new Point(x, y);
    }

    public void resize(double newHeight, double newWidth) {
        height = newHeight;
        width = newWidth;
    }

    public void move(double newX, double newY) {
        location.setxCoordinate(newX);
        location.setyCoordinate(newY);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

class Point{

    private double xCoordinate;  // The X coordinate

    private double yCoordinate;



    public Point(double x, double y){

        xCoordinate = x;

        yCoordinate = y;

    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
}
