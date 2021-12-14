package domain;


import java.util.Objects;

public class Order {

    private long id;
    private int number;
    private transient Client client_id;
    private Commodity commodity_id;

    public Order() {
    }

    public Order(long id) {
        this.id = id;
    }

    public Order(long id, int number, Client client_id, Commodity commodity_id) {
        this.id = id;
        this.number = number;
        this.client_id = client_id;
        this.commodity_id = commodity_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public Commodity getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(Commodity commodity_id) {
        this.commodity_id = commodity_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && number == order.number && Objects.equals(client_id, order.client_id) && Objects.equals(commodity_id, order.commodity_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, client_id, commodity_id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number=" + number +
                ", client_id=" + client_id +
                ", commodity_id=" + commodity_id +
                '}';
    }
}
