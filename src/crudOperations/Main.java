package crudOperations;

import crudOperations.user.Address;
import crudOperations.user.Company;
import crudOperations.user.Geo;
import crudOperations.user.User;

import java.io.IOException;


public class Main {
    public static final String URL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws IOException, InterruptedException {
        Address address = new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", new Geo("-37.3159", "81.1496"));
        Company company = new Company("Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets");

        User user = new User("Orest", "Test", "test@april.biz", address, "1-770-736-8031 x56442", "hildegard.org", company);



        System.out.println(new CRUDMethods().getUserById(URL,3)); // get user by id
        System.out.println( new CRUDMethods().getUserByUserName(URL,"Bret"));// get user by name
        System.out.println( new CRUDMethods().getAllUsers(URL));// get all user


        System.out.println( new CRUDMethods().createUser(URL, user));// create user
        System.out.println( new CRUDMethods().updateUser(URL,user));// update user
        System.out.println( new CRUDMethods().deleteUser(URL,1));// delete user
    }
}