/**
 * Admin class . Fields <b>id</b> <b>name</b> <b>login</b> <b>password</b> <b>email</b>
 * @autor Oleg Suhodolsky
 * @version 1.0
 */
package com.epam.brest.model.entity;


public class Admin {
    /** field id - admin's identificator in database*/
    private int id;
    /** field name - admin's fullname*/
    private String name;
    /** field name - admin's login in system*/
    private String login;
    /** field name - admin's password*/
    private String password;
    /** field name - user's e-mail*/
    private String email;

    /**
     * Constructor - create new object
     * @see Admin#Admin()
     * @param id - identificator
     * @param name - fullname of admin
     *      * @param login - login of admin
     *      * @param password - admin's password
     *      * @param email - admin's email
     */
    public Admin(int id, String name, String login, String password, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    /**
     * Constructor - create new object
     * @see Admin#Admin(int, String,String,String,String)
     */
    public Admin() {
    }

    /**
     * Getter - get field id {@link Admin#id}
     * @return - return id of admin
     */
    public int getId() {
        return id;
    }

    /**
     * Setter - set id field to Admin {@link Admin#id}
     * @param id - identificator
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter - get field name {@link Admin#name}
     * @return - return fullname of admin
     */
    public String getName() {
        return name;
    }

    /**
     * Setter - set name field to User {@link Admin#name}
     * @param name - identificator
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter - get field login {@link Admin#login}
     * @return - return login of admin
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter - set login field to Admin {@link Admin#login}
     * @param login - identificator
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter - get field password {@link Admin#password}
     * @return - return password of admin
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter - set password field to Admin {@link Admin#password}
     * @param password - identificator
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter - get field id {@link Admin#email}
     * @return - return e-mail of admin
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter - set email field to Admin {@link Admin#email}
     * @param email - identificator
     */
    public void setEmail(String email) {
        this.email = email;
    }


}