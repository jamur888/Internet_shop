package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
    private long id;
    private String name;
    private boolean isBlocked;
    private List<Order> orderList;

    public  Client(){}

    public Client(long id){
        this.id =id;
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

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && isBlocked == client.isBlocked && Objects.equals(name, client.name) && Objects.equals(orderList, client.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isBlocked, orderList);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isBlocked=" + isBlocked +
                ", orderList=" + orderList +
                '}';
    }
}