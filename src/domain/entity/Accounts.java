/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.entity;

import java.util.List;

import utils.constant.RoleEnum;

/**
 *
 * @author Vo Anh Ben - CE190709
 */

public class Accounts {
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm
    private Long id;
    private String username;
    private String password;
    private String email;
    private RoleEnum role;

    public Accounts() {
    }

    public Accounts(Long id, String username, String password, String email, RoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Accounts(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toStringFormatted() {
        return this.id + "?" + this.username + "?" + this.password + "?" + this.email + "?" + this.role;
    }

    @Override
    public String toString() {
        return String.format("| %s%s%-5d%s | %s%-26s%s | %s%-26s%s | %s%-26d%s |",
                BOLD, YELLOW, this.id, RESET,
                GREEN, this.username, RESET,
                RED, this.email, RESET,
                YELLOW, this.role, RESET);
    }

    public static void printTable(List<Accounts> accountList) {
        StringBuilder sb = new StringBuilder();
        sb.append(BOLD + CYAN);
        sb.append(
                "+-------+----------------------------+----------------------------+----------------------------+\n");
        sb.append(
                "| ID    | Name                       | Email                      | Role                       |\n");
        sb.append(
                "+-------+----------------------------+----------------------------+----------------------------+\n");
        for (Accounts order : accountList) {
            sb.append(order.toString()).append("\n");
            sb.append(
                    CYAN + "+-------+----------------------------+----------------------------+----------------------------+\n"
                            + RESET);
        }
        System.out.println(sb.toString());
    }
}
