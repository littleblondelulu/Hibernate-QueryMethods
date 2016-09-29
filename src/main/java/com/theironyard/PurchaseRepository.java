package com.theironyard;

import com.sun.tools.javac.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
     // @Query("SELECT p FROM purchases p WHERE p.category LIKE ?1%")
      List<Purchase> findByCategory(String category);

}