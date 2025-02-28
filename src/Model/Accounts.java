/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Vo Anh Ben - CE190709
 */

public class Accounts {
    private Long id;
    private String username;
    private String password;
    private String email;

    public Accounts() {
    }

    public Accounts(Long id, String username, String password, String email, Long role) {
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

    private Long role;

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
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

}
