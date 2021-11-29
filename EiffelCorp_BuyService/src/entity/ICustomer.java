package entity;

import java.rmi.RemoteException;
import java.util.List;

public interface ICustomer {

	int getId();

	void setId(int id);

	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	String getEmail();

	void setEmail(String email);

	String getLogin();

	void setLogin(String login);

	String getPassword();

	void setPassword(String password);

	Product[] getProductSale();

	void AddProductSale(Product productSale);

	void addProductToCart(Product product);

	void removeProductFromCart(Product product);

	int totalPrice() throws RemoteException;

	void removeCart();

}