package ro.msg.learning.shop.service.createorder;

import ro.msg.learning.shop.model.Location;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderObj {
    private LocalDateTime orderTimeStamp;
    private Location delicaryAddress;
    private Map<Integer, Integer> products;

    public OrderObj(LocalDateTime orderTimeStamp, Location delicaryAddress, Map<Integer, Integer> products) {
        this.orderTimeStamp = orderTimeStamp;
        this.delicaryAddress = delicaryAddress;
        this.products = products;
    }
}
