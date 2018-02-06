import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {
	
	private ArrayList<Customer> customers;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main = new Main();
		main.start();
	}
	
	public void start() {
		
		// Step 1 - Get the JSON data - using get request 
		String usersJson = HelperFunctions.getData("https://gist.githubusercontent.com/brianw/19896c50afa89ad4dec3/raw/6c11047887a03483c50017c1d451667fd62a53ca/gistfile1.txt");
		
		// Step 2 - Parse the data using gson library - deserialize into array of objects
		Gson gs = new Gson();
		Type listType = new TypeToken<List<Customer>>() {}.getType();
		this.customers = gs.fromJson(usersJson, listType);
		
		// Step 3 - refine the array to customers within 100km
		this.customers = HelperFunctions.getCloseCustomers(customers);
		
		//Step 4 - Sort customers by Unique ID - using Comparable interface in Cust.class
		Collections.sort(customers);
		
		// Step 5 Print all users within 100km in ascending order 
		System.out.println("====Matching Customers Below====");
		System.out.println("=================================");
		for(Customer c : customers) {
			System.out.println(c.getUserId()+" - - "+c.getName());
		}
		
		
		
	}
	
	
}
