package com.theironyard;

import com.theironyard.Customer;

import javax.persistence.*;
//Assign entity annotation - Hibernate will create a table for purchases
//Created tables using presql command line - so check for duplicates and fix if necessary

@Entity
@Table(name = "purchases")
public class Purchase {
  //Create unique identifier for each purchase in the table
  //Align the annotation needed to set primary key and get all the appropriate mappings; note: auto-increment

  @Id
  @GeneratedValue
  Integer purchase_id;

//The Purchase class should also have a Customer field -- will serve to join the two tables together
//Many purchases to one customer - Define an instance of Customer and make it a private field in Purchase entity
  @ManyToOne
    private Customer customer;

//null values aren't accidentally inserted into the database
  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private String creditCard;

  @Column(nullable = false)
  private Integer cvv;

  @Column(nullable = false)
  private String date;

  public Purchase(){}

  //how is the empty constructor used in the joins/when is this line used/compiled?
  public Purchase(Customer customer, String category, String creditCard, int cvv, String date){}

  public Purchase(String date, String creditCard, Integer cvv, String category, Customer customer) {
	this.category = category;
	this.creditCard = creditCard;
	this.cvv = cvv;
	this.date = date;
	this.customer = customer;
  }

  public Integer getPurchase_id() {
    return purchase_id;
  }

  public void setPurchase_id(Integer purchase_id) {
    this.purchase_id = purchase_id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Integer getCvv() {
    return cvv;
  }

  public void setCvv(Integer cvv) {
    this.cvv = cvv;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(String creditCard) {
    this.creditCard = creditCard;
  }

}
