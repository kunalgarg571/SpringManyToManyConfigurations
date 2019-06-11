package com.example.demo.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.PublisherRepository;
import com.example.demo.model.Book;
import com.example.demo.model.Publisher;


@RestController
public class Demo {
	@Autowired
	PublisherRepository publisherRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@RequestMapping(value="/addPublisher", method = RequestMethod.POST)
	public String addPublisher(@RequestBody Publisher publisher){
		publisherRepository.save(publisher);
		return "Saved";
	}
	
	@RequestMapping(value="/addBook", method = RequestMethod.POST)
	public String addBook(@RequestBody Book bookDto){
		
//		Book book = new Book();
//		
//		book.setName(bookDto.getName());
//		
//		String str[] = bookDto.getPublishers().split(",");
//		
//		Set<Publisher> publisher = new HashSet<>();
//		
//		for(int i= 0 ; i<str.length;i++)
//			publisher.add(new Publisher(str[i]));
//		
//		book.setPublishers(publisher);
		
		bookRepository.save(bookDto);
		return "Saved";
	}
	
	@PostMapping("/{id}/students/{publisherId}") // Path variable names must match with method's signature variables.
    public Set<Publisher> publisher(@PathVariable int id, @PathVariable int publisherId) throws Exception{
        // Finds a persisted student
        	Publisher publisher = this.publisherRepository.findById(publisherId).orElseThrow(
                () -> new Exception("publisher not found")
        	);
        
        // Finds a lecturer and adds the given student to the lecturer's set.
        	
        	
        return this.bookRepository.findById(id).map((book) -> {
            book.getPublishers().add(publisher);
            return this.bookRepository.save(book).getPublishers(); 
        }).orElseThrow(() -> new Exception("Lecturer"));
    }
	
	
	
	@GetMapping("/{bookId}/students")
    public Book getBook(@PathVariable int bookId) throws Exception{
        // Finds lecturer by id and returns it's recorded students, otherwise throws exception 
        return this.bookRepository.findById(bookId).map((book) -> {
            return book;
        }).orElseThrow(() -> new Exception("Lecturer"));
    }
	
	@GetMapping("/students/{publisherId}")
	public Set<Book> getPublisher(@PathVariable int publisherId) throws Exception{
		return this.publisherRepository.findById(publisherId).map((Publisher) -> {
            return Publisher.getBooks();
        }).orElseThrow(() -> new Exception("Lecturer"));
	}
}
