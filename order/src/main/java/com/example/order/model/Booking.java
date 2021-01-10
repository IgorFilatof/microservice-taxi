package com.example.order.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
//@Table(name = "Order_table")
public class Booking implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "CUSTOMER_USER_ID")
    private String customerUserId;
    // @Column(name = "ORDER_STATUS")
    // @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String driverId;

    private String phone;

    private String address;

//    public int getOrderStatus() {
//        return orderStatus;
//    }
//
//    public void setOrderStatus(int orderStatus) {
//        this.orderStatus = orderStatus;
//    }
}
