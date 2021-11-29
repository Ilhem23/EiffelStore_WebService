package bank.account;

import java.util.HashMap;
import java.util.Map;

public class Account {
   private static Map<Integer, Double> clients= new HashMap<Integer, Double>();
	
	public void createAccount(int idCustomer) {
		clients.putIfAbsent(idCustomer, 0.0);
	}
	
	public void deposit(int idCustomer, double amount) {
		clients.put(idCustomer, clients.get(idCustomer) + amount);
	}
	
	public boolean withdrawal(int idCustomer, double amount) {
		if(clients.get(idCustomer) < amount)
			return false;
		clients.put(idCustomer, clients.get(idCustomer) - amount);
		return true;
	}
	
	public Double accountBalance(int idCustomer) {
		return clients.get(idCustomer);
	}

}
