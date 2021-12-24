
/**
 * User class . Fields <b>id</b> <b>name</b> <b>login</b> <b>password</b> <b>email</b>
 * @autor Oleg Suhodolsky
 * @version 1.0
 */
package com.epam.brest.model.entity;

import java.io.Serializable;

public class User implements Serializable {
    /** field id - user's identificator in database*/
    private int id;
    /** field name - user's fullname*/
    private String name;
    /** field name - user's login in system*/
    private String login;
    /** field name - user's password*/
    private String password;
    /** field name - user's e-mail*/
    private String email;

    /**
     * Constructor - create new object
     * @see User#User()
     * @see User#User(String,String,String,String)
     * @param id - identificator
     * @param name - fullname od user
     * @param login - login of user
     * @param password - user's password
     * @param email - user's email
     */
    public User(int id, String name, String login, String password, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    /**
     * Constructor - create new object
     * @see User#User(int, String,String,String,String)
     * @see User#User(String,String,String,String)
     */
    public User() {}


    /**
     * Constructor - create new object
     * @see User#User()
     * @see User#User(int,String,String,String,String)
     * @param name - fullname od user
     * @param login - login of user
     * @param password - user's password
     * @param email - user's email
     */
    public User(String name, String login, String password, String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
    }


    /**
     * Getter - get field id {@link User#id}
     * @return - return id of user
     */
    public int getId() {
        return id;
    }

    /**
     * Setter - set id field to User {@link User#id}
     * @param id - identificator
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter - get field name {@link User#name}
     * @return - return fullname of user
     */
    public String getName() {
        return name;
    }

    /**
     * Setter - set name field to User {@link User#name}
     * @param name - identificator
     * @return
     */
    public void setName(String name) {
        this.name = name;
       }

    /**
     * Getter - get field login {@link User#login}
     * @return - return login of user
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter - set login field to User {@link User#login}
     * @param login - identificator
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter - get field password {@link User#password}
     * @return - return password of user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter - set password field to User {@link User#password}
     * @param password - identificator
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter - get field id {@link User#email}
     * @return - return e-mail of user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter - set email field to User {@link User#email}
     * @param email - identificator
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * METHOD toString()
     * @return object to string
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
