package entity;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import com.currencysystem.webservices.currencyserver.CurncsrvReturnRate;
import com.currencysystem.webservices.currencyserver.CurrencyServerLocator;
import com.currencysystem.webservices.currencyserver.CurrencyServerSoap;

import bank.account.Account;
import bank.account.AccountServiceLocator;
import bank.account.AccountSoapBindingStub;
import shared.IFeedBack;
import shared.IFeedbackController;
import shared.IProductController;
import shared.IPurshaseController;
import shared.ISaleController;

public class BuyService {
	private int idCounter = 0;
	private static List<Customer> customers;
	public static IProductController productService = null;
	public static ISaleController saleService = null;
	public static IPurshaseController purshaseService = null;
	public static IFeedbackController feedBack = null;

	public static Account account;

	public BuyService() throws MalformedURLException, RemoteException, NotBoundException, ServiceException {
		super();
		productService = (IProductController) Naming.lookup("rmi://localhost:1099/productService");
		saleService = (ISaleController) Naming.lookup("rmi://localhost:1099/saleService");
		purshaseService = (IPurshaseController) Naming.lookup("rmi://localhost:1099/purshaseService");
		feedBack = (IFeedbackController) Naming.lookup("rmi://localhost:1099/feedBack");
		customers = new ArrayList<Customer>();
		account = new AccountServiceLocator().getAccount();
		((AccountSoapBindingStub) account).setMaintainSession(true);
	}

	public double convert(double montant, String currency) throws RemoteException, ServiceException {

		CurrencyServerSoap currencySystem = new CurrencyServerLocator().getCurrencyServerSoap();
		return montant * (double) currencySystem.rate("", "EUR", currency, false, "",
				CurncsrvReturnRate.curncsrvReturnRateNumber, "", "");
	}

	public String getAllCurencies() throws RemoteException, ServiceException {
		CurrencyServerSoap currencySystem = new CurrencyServerLocator().getCurrencyServerSoap();
		return currencySystem.allCurrencies(null);

	}

	public Product[] getProductList() throws RemoteException {

		List<Product> productList = new ArrayList<Product>();

		Set<Integer> solds = saleService.getSoldAtLeastOnes();
		for (int n : solds) {
			Product p = new Product();
			p.setId(productService.getproducts().get(n).getId());
			p.setAvailability(productService.getproducts().get(n).isAvailability());
			p.setCategory(productService.getproducts().get(n).getCategory());
			p.setImage(productService.getproducts().get(n).getImage());
			p.setName(productService.getproducts().get(n).getName());
			p.setPrice(productService.getproducts().get(n).getPrice());
			p.setState(productService.getproducts().get(n).getState());
			p.setDescription(productService.getproducts().get(n).getDescription());
			int rating = getRatingByProduct(productService.getproducts().get(n).getId());
			p.setRating(rating);
			if (p.isAvailability()) {
				p.setInventoryStatus("IN STOCK");
				p.setInventoryName("instock");

			} else {
				p.setInventoryStatus("IN STOCK");
				p.setInventoryName("outofstock");
			}

			productList.add(p);
		}
		return productList.toArray(new Product[0]);
	}

	public Customer addCustomer(String firstName, String lastName, String email, String login, String password)
			throws RemoteException {
		Customer customer = new Customer(getIdCounter(), firstName, lastName, email, login, password);
		customers.add(customer);
		return customer;
	}

	public Customer loginCustomer(String login, String password) {
		for (Customer c : customers) {
			if (c.getLogin().equals(login) && c.getPassword().equals(password)) {
				return c;
			}
		}

		return null;
	}

	public boolean buy(int customerId) throws RemoteException, MalformedURLException, NotBoundException {
		int price = customers.get(customerId).totalPrice();
		List<Integer> saleId = new ArrayList<Integer>();
		for (Product p : customers.get(customerId).cart) {
			saleId.add(saleService.getCurrentSaleByProduct(p.getId()).getId());
		}
		if (withdrawal(customerId, price)) {
			purshaseService.addPurshaseCustomer(customerId, saleId);
			customers.get(customerId).removeCart();
			return true;
		} else {
			return false;
		}

	}

	public void createBankAccount(int customerId) throws ServiceException, RemoteException {

		account.createAccount(customerId);
	}

	public void deposit(int customerId, double amount) throws RemoteException {
		account.deposit(customerId, amount);
	}

	public boolean withdrawal(int customerId, double amount) throws RemoteException {
		return account.withdrawal(customerId, amount);
	}

	public FeedBack[] getFeedBackByProduct(int productId) throws RemoteException {
		List<FeedBack> feed = new ArrayList<FeedBack>();
     if(! feedBack.searchByProduct(productId).isEmpty()) {
    	 for (IFeedBack f : feedBack.searchByProduct(productId)) {
 			String str =feedBack.getEmployeeName(f.getEmployeeId());
 			FeedBack fe = new FeedBack();
 			fe.setComment(f.getComment());
 			fe.setDateComment(f.getDateComment());
 			fe.setEmployeeName(str);
 			fe.setRating(f.getRating());
 			fe.setProductId(f.getProductId());
 			feed.add(fe);
 		}

 		return feed.toArray(new FeedBack[0]);
    	 
     }
     return feed.toArray(new FeedBack[0]);
		
	}

	public int getRatingByProduct(int productId) throws RemoteException {
		
		return feedBack.getRatingByProduct(productId);
	}

	public String getNameEmployee(int id) throws RemoteException {

		return feedBack.getEmployeeName(id);
	}

	public void addProductToCart(int customerId, int productId) throws RemoteException {

		customers.get(customerId).addProductToCart((Product) productService.searchById(productId));
	}

	public void removeProductFromCart(int customerId, int productId) throws RemoteException {
		customers.get(customerId).removeProductFromCart((Product) productService.searchById(productId));
	}

	public int getIdCounter() {
		idCounter++;
		return idCounter;
	}

}
