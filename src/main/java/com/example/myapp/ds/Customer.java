package com.example.myapp.ds;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column(unique=true)
    private  String name;

    private  String password;
    @Embedded
    private  Address address;

   @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles=new ArrayList<>();
   @OneToMany
   private  List<CustomerBookOrder> customerBookOrders=new ArrayList<>();
   @ManyToMany
   private  List<Book> books=new ArrayList<>();
   public  void addBook(Book book){
       book.getCustomers().add(this);
       books.add(book);
   }

   public  void addRole(Roles role){

    role.getCustomers().add(this);
    roles.add(role);
   }
   public  void addOrder(CustomerBookOrder customerBookOrder){
       customerBookOrder.setCustomer(this);
       customerBookOrders.add(customerBookOrder);
   }
}
