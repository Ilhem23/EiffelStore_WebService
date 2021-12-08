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
	private static int idCounter = 0;
	private static List<Customer> customers= null;
	public static IProductController productService = null;
	public static ISaleController saleService = null;
	public static IPurshaseController purshaseService = null;
	public static IFeedbackController feedBack = null;
	public static Account account= null;

	public BuyService() throws MalformedURLException, RemoteException, NotBoundException, ServiceException {
		super();
		productService = (IProductController) Naming.lookup("rmi://localhost:1099/productService");
		saleService = (ISaleController) Naming.lookup("rmi://localhost:1099/saleService");
		purshaseService = (IPurshaseController) Naming.lookup("rmi://localhost:1099/purshaseService");
		feedBack = (IFeedbackController) Naming.lookup("rmi://localhost:1099/feedBack");
		BuyService.GetCustomerInstance();
		BuyService.getAccountInstance();
		((AccountSoapBindingStub) account).setMaintainSession(true);
	}
	
	public static void GetCustomerInstance() throws RemoteException {
		if (customers == null) {
			customers = new ArrayList<Customer>();
			
		}
	}
	public static void getAccountInstance() throws ServiceException {
		if(account == null) {
			account = new AccountServiceLocator().getAccount();
			
		}
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
			Product p = convertProduct(n);
			productList.add(p);
		}
		return productList.toArray(new Product[0]);
	}
	
	public Product convertProduct(int n) throws RemoteException {
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
			p.setInventoryStatus("OUT OF STOCK");
			p.setInventoryName("outofstock");
		}
		return p;
		
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
	
	public Customer[] getAllCustomer() throws RemoteException{
		return customers.toArray(new Customer[0]);
		
	}

	public boolean buy(int customerId, String currency) throws RemoteException, MalformedURLException, NotBoundException, ServiceException {
		 Customer cust= null;
		for(Customer cr: customers) {
			if(cr.getId() == customerId) {
				cust= cr;
			}
		}
		int price = cust.totalPrice();
		Double realPrice= convert(price, currency);
		List<Integer> saleId = new ArrayList<Integer>();
		for (Product p : cust.cart) {
			if(p != null)
			  saleId.add(saleService.getCurrentSaleByProduct(p.getId()).getId());
		}
		if (withdrawal(customerId, realPrice)) {
			purshaseService.addPurshaseCustomer(customerId, saleId);
			for (Product p : cust.cart) {
				if(p != null)
					cust.AddProductSale(p);
			}
			
			cust.removeCart();
			return true;
		} else {
			return false;
		}

	}
	public Product[] getCart(int customerId) {
		for(Customer cr: customers) {
			if(cr.getId() == customerId) {
				if(cr.cart != null) {
				  return cr.cart;
				  }else {
					  return null;
				  }
			}
		}
		return null;	
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
	public Double balance(int customerId) throws RemoteException {
		return account.accountBalance(customerId);
	}

	public FeedBack[] getFeedBackByProduct(int productId) throws RemoteException {
		List<FeedBack> feed = new ArrayList<FeedBack>();
		List<IFeedBack> feedback= feedBack.searchByProduct(productId);
     if(feedback != null && !feedback.isEmpty()) {
    	 for (IFeedBack f : feedback) {
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
		for(Customer cr: customers) {
			if(cr.getId() == customerId) {
				cr.addProductToCart(convertProduct(productId));
			}
		}
	}

	public void removeProductFromCart(int customerId, int productId) throws RemoteException {
		for(Customer cr: customers) {
			if(cr.getId() == customerId) {
				cr.removeProductFromCart(convertProduct(productId));
			}
		}
		
	}

	public int getIdCounter() {
		idCounter++;
		return idCounter;
	}

}
