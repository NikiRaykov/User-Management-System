package com.example.usermanagement.controller;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    /*
        Create a User

        Endpoint: POST /users

        This endpoint is used to create a new user.

        Request
Method: POST
URL: /users
Body:
 Content-Type: application/json
 Request body should contain the following fields:
 - firstName (string): The first name of the user.
 - lastName (string): The last name of the user.
 - dateOfBirth (string): The date of birth of the user in yyyy-MM-dd format.
 - phoneNumber (string): The phone number of the user.
 - emailAddress (string): The email address of the user.
Response
 Status Code: 201 Created
 Body: The created user object with the following fields:
 - id (number): The unique identifier of the user.
 - firstName (string): The first name of the user.
 - lastName (string): The last name of the user.
 - dateOfBirth (string): The date of birth of the user in yyyy-MM-dd format.
 - phoneNumber (string): The phone number of the user.
 - emailAddress (string): The email address of the user.
     */

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /*
    Get User by ID
    Endpoint: GET /users/{id}

    This endpoint is used to retrieve information about a user based on their identifier (id).

    Request
     - Method: GET
     - URL: /users/{id}
     - Path Parameters:
     - id (number): The unique identifier of the user.

     Response
     Status Code: 200 OK
     Body: The user object with the following fields:
      - id (number): The unique identifier of the user.
      - firstName (string): The first name of the user.
      - lastName (string): The last name of the user.
      - dateOfBirth (string): The date of birth of the user in yyyy-MM-dd format.
      - phoneNumber (string): The phone number of the user.
      - emailAddress (string): The email address of the user.
     */


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws UserNotFoundException {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    /*
    Get All Users
    Endpoint: GET /users

    This endpoint is used to retrieve information about all users. The response includes sorting and searching capabilities.

    Request
    Method: GET
    URL: /users
    Query Parameters (optional):
       - sort (string): Sort the users by lastName or dateOfBirth.
       - search (string): Search term to filter users based on the provided value.

    Response
    Status Code: 200 OK
    Body: An array of user objects, where each user has the following fields:
     - id (number): The unique identifier of the user.
     - firstName (string): The first name of the user.
     - lastName (string): The last name of the user.
     - dateOfBirth (string): The date of birth of the user in yyyy-MM-dd format.
     - phoneNumber (string): The phone number of the user.
     - emailAddress (string): The email address of the user.

     */

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping ResponseEntity<List<User>> getAllUsersByLastNameAndDateOfBirth(String lastName, Date dateOfBirth) {
        List<User> usersByLastNameAndDateOfBirth =
                userService.getAllUsersByLastNameAndDateOfBirth(lastName, dateOfBirth);


        return ResponseEntity.ok(usersByLastNameAndDateOfBirth);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String term) {
        List<User> users = userService.searchUsers(term);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) throws UserNotFoundException {
        User user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    /*
    Delete a User
    Endpoint: DELETE /users/{id}

    This endpoint is used to delete a user based on their identifier.

   Request
  - Method: DELETE
  - URL: /users/{id}
  - Path Parameters:
  - id (number): The unique identifier of the user.
   Response
  - Status Code: 204 No Content
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

/*

Conclusion
This API provides a set of endpoints to perform CRUD operations on users.
It allows creating, retrieving, updating, and deleting users.
 */
