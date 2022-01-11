package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
    private long id;
    private String name;
    private boolean isBlocked;
    private List<Order> orders = new ArrayList<>();

    public Client() {
    }

    public Client(long id) {
        this.id = id;
    }

    public Client(long id, String name, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.isBlocked = isBlocked;

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isBlocked() { return isBlocked; }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && isBlocked == client.isBlocked && Objects.equals(name, client.name) && Objects.equals(orders, client.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isBlocked, orders);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isBlocked=" + isBlocked +
                ", orders=" + orders +
                '}';
    }
}