package entity;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


public class Customer implements Serializable, ICustomer{
	
	private int id;
	private String firstName;
	private String lastName;
	private String  email;
	private String login;
	private String password;
	public Product[] productSale;
	public Product[] cart;
	private int indexSale;
	public Product[] getCart() {
		return cart;
	}

	public void setCart(Product[] cart) {
		this.cart = cart;
	}

	public void setProductSale(Product[] productSale) {
		this.productSale = productSale;
	}
	private int indexCart;
	
	public Customer(int id, String firstName, String lastName, String email, String login, String password) throws RemoteException {
		super();
		this.id = id;
		this.firstName= firstName;
		this.lastName= lastName;
		this.email= email;
		this.login= login;
		this.password= password;
		indexSale= 0;
		indexCart= 0;
		
	}
	
	public Customer() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Product[] getProductSale() {
		return productSale;
	}

	public void AddProductSale(Product productSales) {
		
		if(productSale != null)
		{
			productSale[indexSale]= productSales;
		}else {
			productSale= new Product[20];
			productSale[indexSale]= productSales;
		}
		indexSale++;
		
	}
	public void addProductToCart(Product product) {
		
		if(cart != null)
		{
			cart[indexCart]= product;
		}else {
			cart= new Product[20];
			cart[indexCart]= product;
		}
		indexCart++;
		
	}
	public void removeProductFromCart(Product product) {
		int n= 0;
		for(Product p : cart) {
			if(p != null) {
				if(p.getId() == product.getId()) {
					cart[n] = null;
				}
			}
			
			n++;
		}
	}
	public int totalPrice() throws RemoteException {
		int total= 0;
		if(cart != null)
		{
		for(Product p : cart)
		{
			if(p != null)
				total= total+ p.getPrice();
		}	
		}
		return total;
	
	}
	public void removeCart() {
		cart= new Product[20];
	}


	
	

}
