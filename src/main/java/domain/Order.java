package domain;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class Order {
//transient
    private long id;
    private transient Client client_id;
    private Commodity commodity_id;

    public Order() {
    }

    public Order(long id) {
        this.id = id;
    }

    public Order(long id, Client client_id, Commodity commodity_id) {
        this.id = id;
        this.client_id = client_id;
        this.commodity_id = commodity_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return id == order.id && Objects.equals(client_id, order.client_id) && Objects.equals(commodity_id, order.commodity_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client_id, commodity_id);
    }

    @Override
    public String toString() {

     //   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return "Order{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", commodity_id=" + commodity_id +
                '}';
    }

}
