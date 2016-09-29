package com.theironyard;

import com.theironyard.Customer;
import com.theironyard.CustomerRepository;
import com.theironyard.Purchase;
import com.theironyard.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.Scanner;

//DB = purchasestracker

@Controller
public class PurchaseTrackerController {

  @Autowired
  CustomerRepository customers;

  @Autowired
  PurchaseRepository purchases;

  @PostConstruct
  public void init() throws Exception {
    if (customers.count() == 0) {
      File j = new File("customers.csv");
      Scanner fileScanner = new Scanner(j);

      //Scanner will continue to parse and store data from json file until there's a line with nothing to scan (the end of the file)
      while (fileScanner.hasNext()) {
        String line1 = fileScanner.nextLine();
        String[] columns1 = line1.split(",");

        Customer customer = new Customer(columns1[0], columns1[1]);
        customers.save(customer);
      }
    }

    if (purchases.count() == 0) {
      File f = new File("purchases.csv");
      Scanner fileScanner2 = new Scanner(f);

      //Loop csv file - scan line by line / data is split by commas
      while (fileScanner2.hasNext()) {
        String line2 = fileScanner2.nextLine();
        String[] columns2 = line2.split(",");

        //create columns for each of the Purchase and Customer fields
        Purchase purchase = new Purchase(columns2[1], columns2[2], Integer.valueOf(columns2[3]), columns2[4],
                customers.findOne(Integer.valueOf(columns2[0])));
        purchases.save(purchase);
      }
    }
  }

  @RequestMapping(path = "/", method = RequestMethod.GET)
  public String home(Model model, String category) {
    List<Purchase> purchaseList;
    if (category != null) {
      purchaseList = (List)purchases.findByCategory(category);
    } else {
      purchaseList = (List) purchases.findAll();
    }
    model.addAttribute("purchases", purchaseList);
    return "home";
  }

//  @RequestMapping(path = "/", method = RequestMethod.POST)
//  public String displayTable(Customer customer, String category, String creditCard, int cvv, String date) {
//    List<Purchase> purchaseList = purchases.findByCategory(category);
//    Purchase purchase = new Purchase(customer, category, creditCard, cvv, date);
//    purchases.save(purchase);
//    return "redirect:/";
//  }

}