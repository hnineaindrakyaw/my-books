package com.example.myapp.controller;

import com.example.myapp.service.BookService;
import com.example.myapp.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    private  final CartService cartService;
    private  final BookService bookService;

    public CartController(CartService cartService, BookService bookService) {
        this.cartService = cartService;
        this.bookService = bookService;
    }

    @GetMapping("/card/add/{id}")
    public  String addToCart(@PathVariable int id, Model model){
        cartService.addToCart(bookService.findBookById(id));
        return  "redirect:/books/details?id="+id;

    }
   /* @ModelAttribute("cartSize")
    public  int cartSize(){
        return  cartService.cartSize();

    }*/


}
