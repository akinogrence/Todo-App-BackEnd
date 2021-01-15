package com.omreon.rest.restfulwebservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardCodedService {

	private static List<Todo> todos = new ArrayList();
	private static int idCounter = 0;

	static {
		todos.add(new Todo(++idCounter, "aogrence", "Spring about programming", new Date(), false));
		todos.add(new Todo(++idCounter, "omreon", "Software about programming", new Date(), true));
		todos.add(new Todo(++idCounter, "csimsek", "Test about programming", new Date(), false));
	}

	public List<Todo> findAll() {
		return todos;
	}

	public Todo save(Todo todo) {
		if (todo.getId() == -1 || todo.getId()== 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}

	public Todo deleteById(int id) {
		Todo willRemoveTodo = findById(id);
		if (willRemoveTodo == null)
			return null;
		else if (todos.remove(willRemoveTodo)) {
			return willRemoveTodo;
		}
		return null;

	}

	public Todo findById(int id) {

		return todos.stream().filter(todo -> todo.getId()==id).findFirst().orElse(null);

	}

}
