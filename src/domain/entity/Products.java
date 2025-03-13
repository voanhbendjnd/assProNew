/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.entity;

import java.util.List;

import utils.constant.TargetEnum;
import utils.function.Utils;

public class Products {
    private Long id;
    private Long code;
    private String name;
    private String brand;
    private Long price;
    private String description;
    private Long stock;
    private String dateCreate;
    private TargetEnum target;

    // color
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm

    public Products() {
    }

    public Products(Long code, String name, String brand, TargetEnum target, Long price, String description, Long stock,
            String dateCreate) {
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.price = price;
        this.target = target;
        this.description = description;
        this.stock = stock;
        this.dateCreate = dateCreate;
    }

    public String getName() {
        return name;
    }

    public TargetEnum getTarget() {
        return target;
    }

    public void setTarget(TargetEnum target) {
        this.target = target;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String toStringFormatted() {
        return this.code + "?" + this.name + "?" + this.brand + "?" + this.target + "?" + this.price + "?"
                + this.description + "?"
                + this.stock + "?" + this.dateCreate;
    }

    @Override
    public String toString() {
        return String.format("| %s%-5d%s | %s%-26s%s | %s%-16s%s | %s%-23s%s | %s%-15s%s | %s%-80s%s | %s%-5d%s |",
                BOLD, this.code, RESET,
                GREEN, this.name, RESET,
                BLUE, this.brand, RESET,
                CYAN, this.target, RESET,
                RED, new Utils().formatPrice(this.price), RESET,
                YELLOW, this.description, RESET,
                GREEN, this.stock, RESET);
    }

    public static void printTable(List<Products> productsList) {
        StringBuilder sb = new StringBuilder();
        sb.append(BOLD + CYAN);
        sb.append(
                "+-------+----------------------------+------------------+-------------------------+-----------------+----------------------------------------------------------------------------------+-------+\n");
        sb.append(
                "| Code  | Name                       | Brand            | Target                  | Price           | Description                                                                      | Stock |\n");
        sb.append(
                "+-------+----------------------------+------------------+-------------------------+-----------------+----------------------------------------------------------------------------------+-------+\n");
        sb.append(RESET);
        for (Products product : productsList) {
            sb.append(product.toString()).append("\n");
            sb.append(
                    CYAN + "+-------+----------------------------+------------------+-------------------------+-----------------+----------------------------------------------------------------------------------+-------+\n"
                            + RESET);
        }
        System.out.println(sb.toString());
    }
}
