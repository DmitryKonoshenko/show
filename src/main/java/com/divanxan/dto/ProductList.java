package com.divanxan.dto;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 * This class is container for products.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@SessionScoped
public class ProductList implements Serializable {
    @Getter
    @Setter
    private List<Product> productList;
}
