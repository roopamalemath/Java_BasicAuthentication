package com.in28Minutes.rest.webservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
public class TodoJpaResource {
	
	
	@Autowired
	private  TodoHardcodedService todoService;

	@Autowired
	private  TodoJpaRepository todoJpaRepository;
	
	@GetMapping("/jpa/users/{user_name}/todos")
	public List<Todo> getAllTodos(@PathVariable("user_name") String username){
		return todoJpaRepository.findByUsername(username);
		//return todoService.findAll();		
	}
	
	@GetMapping("/jpa/users/{user_name}/todos/{todo_id}")
	public Todo getTodo(@PathVariable String user_name,
			               @PathVariable long todo_id){
		return todoJpaRepository.findById(todo_id).get();
		//return todoService.findById(todo_id);	
		
	}
	
	// location of created resource
	@PostMapping("/jpa/users/{user_name}/todos")
	public ResponseEntity<Todo> createTodo(@RequestBody Todo todo,
			@PathVariable String user_name){
		//Todo createdResource=todoService.saveTodo(todo);
		todo.setUsername(user_name);
		Todo createdResource=todoJpaRepository.save(todo);
		
		//url fr the created resosurce
		// get cuurent resource url + append id 
		// /users/{user_name}/todos/{id}
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		         .buildAndExpand(createdResource.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/jpa/users/{user_name}/todos/{todo_id}")
	// sent ok status with content of updated todo
	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo,
						@PathVariable String user_name,
						@PathVariable long todo_id){
//		Todo updatedTodo=todoService.saveTodo(todo);
		Todo updatedTodo=todoJpaRepository.save(todo);
		// new respnse entity containing the todo
		return new ResponseEntity<Todo>(updatedTodo,HttpStatus.OK);
	}
	
	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	// no content option as a response
	// ResponseEntity enables us to return specific status back
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,
	                                       @PathVariable long id){
		//Todo todo= todoService.deleteById(id);
		todoJpaRepository.deleteById(id);
		
		//if(todo!=null) {
			return ResponseEntity.noContent().build();
	//	}
		//return ResponseEntity.notFound().build();
	}

}
