package domain;

import java.util.Objects;

public class Commodity {

    private long id;
    private int art;
    private double price;
    private String description;

    public Commodity(long id) {
        this.id =id;
    }

    public Commodity(long id, int art, double price, String description) {
        this.id = id;
        this.art = art;
        this.price = price;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getArt() {
        return art;
    }

    public void setArt(int art) {
        this.art = art;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity commodity = (Commodity) o;
        return id == commodity.id && art == commodity.art && Double.compare(commodity.price, price) == 0 && Objects.equals(description, commodity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, art, price, description);
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", art=" + art +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
