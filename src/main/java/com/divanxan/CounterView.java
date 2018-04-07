package com.divanxan;

import com.divanxan.dto.Product;
import com.divanxan.dto.ProductList;
import com.divanxan.json.JsonReader;
import com.rabbitmq.client.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * This is servlet for CDI bean.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@ManagedBean
@ViewScoped
public class CounterView implements Serializable {
    private static ProductList productList;
    private static String message;
    private static boolean isResiveStart = false;
    private final static String QUEUE_NAME = "hello";

    /**
     * This method return data for view
     *
     * @return String with data for application
     */
    public String getList() {
        return productList.getProductList().toString();
    }

    /**
     * This method return list of top products
     *
     * @return List<Product> - list of top products
     */
    public List<Product> getListProduct() {
        return productList.getProductList();
    }

    public CounterView(){
        if (!isResiveStart) Resive();
        if (productList == null || message!=null) {
            message = null;
            productList = new ProductList();
            JsonReader jsonReader = new JsonReader();
            try {
                productList.setProductList(jsonReader.getTop());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method updating list of products
     */
    public static void getUpdate(){
        if (null!=message) {
            message = null;
            productList = new ProductList();
            JsonReader jsonReader = new JsonReader();
            try {
                productList.setProductList(jsonReader.getTop());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void Resive() {
        isResiveStart = true;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
//        factory.setPort(5672);
        factory.setHost("192.168.99.100");
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                message = new String(body, "UTF-8");
                CounterView.getUpdate();
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        try {
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}