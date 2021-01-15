package com.omreon.rest.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4040")
public class TodoResource {

	@Autowired
	private TodoHardCodedService todoHardCodedService;

	@GetMapping(path = "/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoHardCodedService.findAll();
	}
	@GetMapping(path = "/users/{username}/todos/{id}")
	public Todo getTodoById(@PathVariable String username, @PathVariable int id) {
		return todoHardCodedService.findById(id);
	}

	@DeleteMapping(path = "/users/{username}/todos/{id}")
	public ResponseEntity<Void> delete(@PathVariable String username , @PathVariable int id) {
		
		Todo deletedTodo = todoHardCodedService.deleteById(id);
		if(deletedTodo!=null) 
		{
			return ResponseEntity.noContent().build();		
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping(path = "/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodoById(@PathVariable String username , @PathVariable int id , @RequestBody Todo todo) {
		System.out.println("testr");
		Todo updatedTodo = todoHardCodedService.save(todo);
		return new ResponseEntity<Todo>(updatedTodo,HttpStatus.OK);
	}
	
	@PostMapping(path = "/users/{username}/todos")
	public ResponseEntity<Todo> save( @PathVariable String username , @RequestBody Todo todo) {
		System.out.println("testr");
		Todo createdTodo = todoHardCodedService.save(todo);;
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return  ResponseEntity.created(uri).build();
	}

}
