package com.example.myapp.controller;

import com.example.myapp.ds.Author;
import com.example.myapp.ds.Book;
import com.example.myapp.ds.Category;
import com.example.myapp.service.BookService;
import com.example.myapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CartService cartService;
    @GetMapping("/books/details")
    public  String bookDetails(@RequestParam("id") int id, Model model){
        model.addAttribute("book",bookService.findBookById(id));
        return "book-details";

    }
    @GetMapping(value = {"/","/home"})
    public  String index(Model model){
       /*model.addAttribute("category",new Category());
        model.addAttribute("author",new Author());
        model.addAttribute("book",new Book());
        model.addAttribute("authors",bookService.findAllAuthors());
        model.addAttribute("categories",bookService.findAllCategory());
        model.addAttribute("books",bookService.findAllBoooks());*/




        return "home";
    }
    @GetMapping("/show-all-books")
    public String listBooks(){
       return "list-books";

    }
    @GetMapping("/category-form")
    public  String categoryForm(Model model){
        model.addAttribute("category",new Category());
        return  "category-form";
    }
    @PostMapping("/save-category")
    public  String saveCategory(Category category, BindingResult result){
        if(result.hasErrors()){
            return "dash-board";
        }
        bookService.saveCategory(category);
        return  "redirect:/";

    }
    @PostMapping("/save-author")
    public  String saveAuthor(Author author,BindingResult result){
        if(result.hasErrors()){
            return  "dash-board";
        }
        bookService.saveAuthor(author);
        return "redirect:/";

    }

    @GetMapping("/list-books")
    public  String listAllBooks(Model model){
        model.addAttribute("books",bookService.findAllBoooks());
        return "post-list";
    }

    @PostMapping("/save-book")
    public  String saveBook(Book book,BindingResult result){
        if(result.hasErrors()){
            return "dash-board";
        }
        bookService.saveBook(book,book.getCategoryId(),book.getAuthorId());
        return "redirect:/";

    }
    @ModelAttribute("allbooks")
    public List<Book> showAllBooks(){
        return  bookService.findAllBoooks();
    }

    @GetMapping("/list-category")
    public  String listCategory(Model model){
        model.addAttribute("categories",bookService.findAllCategory());
        return  "list-category";
    }
    @GetMapping("/list-authors")
    public  String listAuthor(Model model){
        model.addAttribute("authors",bookService.findAllAuthors());
        return "list-authors";
    }
    @ModelAttribute("cartSize")
    public  int cartSize(){
        return  cartService.cartSize();

    }


}
