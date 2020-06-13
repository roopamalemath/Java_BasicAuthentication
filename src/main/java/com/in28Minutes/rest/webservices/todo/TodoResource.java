package com.in28Minutes.rest.webservices.todo;

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
@CrossOrigin(origins="http://localhost:4200")
public class TodoResource {
	
	
	@Autowired
	private  TodoHardcodedService todoService;		
	
	@GetMapping("/users/{user_name}/todos")
	public List<Todo> getAllTodos(@PathVariable("user_name") String username){
		return todoService.findAll();		
	}
	
	@GetMapping("/users/{user_name}/todos/{todo_id}")
	public Todo getAllTodo(@PathVariable String user_name,
			               @PathVariable long todo_id){
		return todoService.findById(todo_id);	
		
	}
	
	// location of created resource
	@PostMapping("/users/{user_name}/todos")
	public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo,
			@PathVariable String user_name){
		Todo createdResource=todoService.saveTodo(todo);
		
		//url fr the created resosurce
		// get cuurent resource url + append id 
		// /users/{user_name}/todos/{id}
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		         .buildAndExpand(createdResource.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/users/{user_name}/todos/{todo_id}")
	// sent ok status with content of updated todo
	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo,
						@PathVariable String user_name,
						@PathVariable long todo_id){
		Todo updatedTodo=todoService.saveTodo(todo);	
		// new respnse entity containing the todo
		return new ResponseEntity<Todo>(updatedTodo,HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	// no content option as a response
	// ResponseEntity enables us to return specific status back
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,
	                                       @PathVariable long id){
		Todo todo= todoService.deleteById(id);
		if(todo!=null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
