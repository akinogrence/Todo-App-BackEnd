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

	@Autowired
	private TodoJpaRepository todoJpaRepository;

	@GetMapping(path = "/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoJpaRepository.findByUsername(username);
	}

	@GetMapping(path = "/users/{username}/todos/{id}")
	public Todo getTodoById(@PathVariable String username, @PathVariable long id) {
		return todoJpaRepository.findById(id).get();
	}

	@DeleteMapping(path = "/users/{username}/todos/{id}")
	public ResponseEntity<Void> delete(@PathVariable String username, @PathVariable long id) {

		todoJpaRepository.deleteById(id);
		return ResponseEntity.ok().build();

	}

	@PutMapping(path = "/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodoById(@PathVariable String username, @PathVariable long id,
			@RequestBody Todo todo) {
		todo.setUsername(username);
		Todo updatedTodo = todoJpaRepository.save(todo);
		return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
	}

	@PostMapping(path = "/users/{username}/todos")
	public ResponseEntity<Todo> save(@PathVariable String username, @RequestBody Todo todo) {
		todo.setUsername(username);
		Todo createdTodo = todoJpaRepository.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdTodo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
