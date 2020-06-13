package com.in28Minutes.rest.webservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardcodedService {

	private static List<Todo> todos=new ArrayList<Todo>();
	private static int idCounter=0;
	
	static{
		todos.add(new Todo(++idCounter, "in28Minutes", "Learn Angular", new Date(), false));
		todos.add(new Todo(++idCounter, "in28Minutes", "Learn about Microservices", new Date(), false));
		todos.add(new Todo(++idCounter, "in28Minutes", "Learn about Angular", new Date(), false));
//		todos=Arrays.asList(new Todo(++idCounter, "in28Minutes", "Learn Angular", new Date(), false),
//							new Todo(++idCounter, "in28Minutes", "Learn about Microservices", new Date(), false),
//							new Todo(++idCounter, "in28Minutes", "Learn about Angular", new Date(), false));
	}
	
	public List<Todo> findAll(){
		/*List<Todo> todoss=new ArrayList();
		for(Todo todo : todos) {
			if(todo.getUsername().equalsIgnoreCase(username)) {
				todoss.add(todo);
			}
		}
		return todoss; */
		return todos;
	}
	
	public Todo deleteById(long id) {
		Todo todo = findById(id);
		
		if(todo==null) return null;

		// remove will use equals method so we have to define equals method in todo bean class
		if (todos.remove(todo)) {
			return todo;  
		}

         return null;
	}

     public Todo findById(long id) {
		for(Todo todo:todos) {
			if(todo.getId()==id) {
				return todo;
			}
		}
		return null;
	}
     
	public Todo saveTodo(Todo todo) {
		if (todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;

	}
	
	
	
}
