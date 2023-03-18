
public class Car {
    private String carID;
    private Brand brand;
    private String colour;
    private String frameID;
    private String engineID;

    public Car() {
    }

    public Car(String carID, Brand brand, String colour, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.colour = colour;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }
    public int comparedTo(Car c){
        int d= this.brand.brandName.compareTo(c.brand.brandName);
        if (d!=0) return d;
        else
        return this.carID.compareTo(c.carID);
    }

    @Override
    public String toString() {
        return "Car{" + "carID=" + carID + ", brand=" + brand + ", colour=" + colour + ", frameID=" + frameID + ", engineID=" + engineID + '}';
    }
    
 public String screenString() {
        return "<"+ brand + ",\n" + carID + ", " + colour + ", " + frameID + ", " + engineID+">";
    }

}
