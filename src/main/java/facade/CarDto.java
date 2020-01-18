package facade;

public class CarDto {

    private int id;
    private double price;

    public double getPrice() {
        return price;
    }

    public CarDto(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
