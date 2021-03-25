package entity;
import java.io.Serializable;
import java.text.DecimalFormat;

public class Dish implements Serializable {
    private String name;
    private String id;
    private double price;

    private static final long serialVersionUID = 1229643100629232083L;
    private static int id_no = 1;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Dish(){

    }

    public Dish(String name, double price, int id) {
        this.id = "M".concat(String.valueOf(id));
        this.name = name;
        this.price = Double.parseDouble(df.format(price));
    }

    public Dish(String name, double price) {
        this.id = "M".concat(String.valueOf(id_no++));
        this.name = name;
        this.price = Double.parseDouble(df.format(price));
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return Double.parseDouble(df.format(price));
    }

    public void setPrice(double price) {
        this.price = Double.parseDouble(df.format(price));
    }

    @Override
    public String toString() {
        return "Dish name: " + name + ", " + "Dish ID: " + id + ", " + "Dish Price: RM" + df.format(price);
    }
}
