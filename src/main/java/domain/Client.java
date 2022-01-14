package domain;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Objects;

public class Client extends Entity {

    private String name;
    private boolean isBlocked;
    @Expose
    private List<Order> orders;

    public Client() {
    }

    public Client(long id, String name, boolean isBlocked) {
        super(id);
        this.name = name;
        this.isBlocked = isBlocked;

    }

    public Client(Long id) {
        super(id);
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

    public boolean isBlocked() {
        return isBlocked;
    }

    public List<Order> getOrders() {
        return orders;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return isBlocked == client.isBlocked && Objects.equals(name, client.name) && Objects.equals(orders, client.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isBlocked, orders);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", isBlocked=" + isBlocked +
                ", orders=" + orders +
                '}';
    }
}