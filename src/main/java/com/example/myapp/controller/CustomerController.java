package com.example.myapp.controller;

import com.example.myapp.dao.CustomerDao;
import com.example.myapp.ds.BookDto;
import com.example.myapp.ds.Customer;
import com.example.myapp.service.CartService;
import com.example.myapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartService cartService;

    private CustomerDao customerDao;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public  String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";

    }
    private List<Integer> bookQuantityList;

  @RequestMapping("/customer/register")
    public String register(Model model, BookDto bookDto) {
       this.bookQuantityList=bookDto.getBookNumber();
      System.out.println("===================================" +bookQuantityList);
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/customer/save-customer")
    public String saveCustomer(Customer customer, BindingResult result) {
        Set<BookDto> bookDtoSet=cartService.listCart();
        int index=0;
        for(BookDto bookDto:bookDtoSet){
            bookDto.setQuantity(this.bookQuantityList.get(index));
            index++;
        }
       /*for(int i=0;i<bookDtoSet.size();i++){
           bookDtoSet.size()

       }*/
        if (result.hasErrors()) {
            return "register";
        }
        Customer exisingCustomer = customerService.findCustomerByName(customer.getName());

        if (exisingCustomer == null) {
            customerService.register( customer, bookDtoSet);

        } else {
            customerService.saveCustomerBookOrder(exisingCustomer, bookDtoSet);
        }
        cartService.clearCart();


        return "redirect:/login";
    }
}

