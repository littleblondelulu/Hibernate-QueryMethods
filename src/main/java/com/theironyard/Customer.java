package com.theironyard;

import javax.persistence.*;


@Entity
@Table(name = "customers")
public class Customer {
    //using appropriate annotation to set primary key
    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String email;

    public Customer(){}

    public Customer(String name, String email) {
      this.name = name;
      this.email = email;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getEmail() {
      return this.email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public Integer getId() {return id; }

    public void setId(Integer id) { this.id = id; }

    @Override
    public String toString() {
        return String.format("%s %s ", this.name, this.email);
    }

}
