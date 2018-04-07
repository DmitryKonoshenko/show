package com.divanxan.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * This class is simple POJO class for products.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
public class Product implements Serializable {

    public Product() {
    }

    public Product(int id, String code, String name, String brand, String description, double unitPrice,
                   int quantity, boolean active, int purchases, int views) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.active = active;
        this.purchases = purchases;
        this.views = views;
    }

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String brand;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private double unitPrice;

    @Getter
    @Setter
    private int quantity;

    @Getter
    @Setter
    private boolean active;

    @Getter
    @Setter
    private int purchases;

    @Getter
    @Setter
    private int views;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", active=" + active +
                ", purchases=" + purchases +
                ", views=" + views +
                '}';
    }
}
