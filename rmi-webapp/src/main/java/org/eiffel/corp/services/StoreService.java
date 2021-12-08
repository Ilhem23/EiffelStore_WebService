/*
 * The MIT License
 *
 * Copyright (c) 2009-2021 PrimeTek
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.eiffel.corp.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.inject.Scope;
import javax.servlet.http.HttpSession;

import org.eiffel.corp.utils.SessionUtils;

import entity.BuyService;
import entity.BuyServiceServiceLocator;
import entity.BuyServiceSoapBindingStub;
import entity.Customer;
import entity.FeedBack;
import entity.Product;

@Named
@ApplicationScoped
public class StoreService {

    private List<Product> products;

    private  List<Product> selectedProducts;
    private BuyService buyService;
    private Map<Integer, List<FeedBack>> feedBacks;

    public BuyService getBuyService() {
		return buyService;
	}
	@PostConstruct
    public void init() {
    	try {
    		products = new ArrayList<>();
            buyService = new BuyServiceServiceLocator().getBuyService();
        	((BuyServiceSoapBindingStub) buyService).setMaintainSession(true);
        	Product[] test = buyService.getProductList();
        	for(Product p: test)
    		{        		 
        		 products.add(p);
    		}
        	init_feedBack();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	

    }
    public List<Product> getSelectedProducts() {
  		return selectedProducts;
  	}
    public void deposit(Double amount) {
    	HttpSession session = SessionUtils.getSession();
        int customerId= (int) session.getAttribute("id");
        
        try {
			buyService.deposit(customerId, amount);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public String getBalance() {
    	HttpSession session = SessionUtils.getSession();
        int customerId= (int) session.getAttribute("id");
        try {
        	
			return String.format("%.2f",buyService.balance(customerId));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "0.00";
    }
  	public void setSelectedProducts(Product selectedProducts) {
  		this.selectedProducts.add(selectedProducts);
  	}
  	/*
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
	*/
    public List<Product> getProducts(int size) {
    	init();
    	size= products.size();

        if (size > products.size()) {
            Random rand = new Random();

            List<Product> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(products.size());
                randomList.add(products.get(randomIndex));
            }

            return randomList;
        }

        else {
        	size= products.size();
            return new ArrayList<>(products.subList(0, size));
        }

    }
    public void addProductToCart(int productId){
		HttpSession session = SessionUtils.getSession();
       int customerId= (int) session.getAttribute("id");
    	
    	try {
			buyService.addProductToCart(customerId, productId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public boolean alreadyInCart(int productId) {
    	HttpSession session = SessionUtils.getSession();
        int customerId= (int) session.getAttribute("id");
    	Product[] cart;
		try {
			cart = buyService.getCart(customerId);
			if(cart != null) {
	    		for(Product p: cart) {
	    			if(p!= null) {
	    				if(p.getId() == productId) {
	    					return true;
	    				}
	    			}
	    		}
	    	}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return false;
    	
    }
    public List<Product> getCart(){
    	List<Product> randomList = new ArrayList<>();
    	
    	try {
    		HttpSession session = SessionUtils.getSession();
            int customerId= (int) session.getAttribute("id");
        	Product[] cart;
			cart = buyService.getCart(customerId);
			if(cart != null)
			{
			for(Product p : cart)
			{
				if(p != null)
				  randomList.add(p);
			}
			return randomList;
			}
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    public int getTotalePrice() {
    	int totale= 0;
    	try {
    		
    		HttpSession session = SessionUtils.getSession();
            int customerId= (int) session.getAttribute("id");
        	Product[] cart;
			cart = buyService.getCart(customerId);
			if(cart != null)
			{
			for(Product p : cart)
			{
				if(p != null)
					totale= totale+ p.getPrice();
			}
			return totale;
			}
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return totale;
    	
    }
    public List<String> getAllCurrency(){
    	String curr;
    	List<String> currency;
    	try {
			curr= buyService.getAllCurencies();
			if(curr != null) {
				currency= Arrays.asList(curr.split(";"));
				return currency;
			}
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return  null;
    }
    public void removeFromCart(int idProduct) {
    	HttpSession session = SessionUtils.getSession();
        int customerId= (int) session.getAttribute("id");
        try {
			buyService.removeProductFromCart(customerId, idProduct);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void removeCart() {
    	HttpSession session = SessionUtils.getSession();
        int customerId= (int) session.getAttribute("id");
        Product[] cart;
        try {
        	cart= buyService.getCart(customerId);
        	if(cart != null) {
        		for(Product p: cart) {
        			if(p != null) {
        				buyService.removeProductFromCart(customerId, p.getId());
        			}
        		}
        			
        	}
        	
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void init_feedBack() {
    	try {
			 feedBacks= new HashMap<Integer, List<FeedBack>>();
		    for(Product p: products) {
		          List<FeedBack> fed= new ArrayList<FeedBack>();
		          
		        FeedBack[] feed =  buyService.getFeedBackByProduct(p.getId());
				if(feed == null) {
					feed = new FeedBack[0];
				}
			 for(FeedBack f: feed) {
	        	  fed.add(f);
	          }
	        feedBacks.put(p.getId(), fed);
	        }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public List<FeedBack> getFeedBack(int idProduct){
    	return feedBacks.get(idProduct);
    }
    
    public Double convert( String currency) {
    	try {
			return buyService.convert(getTotalePrice(), currency);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    public boolean makePayment(String currency) {
    	try {
    		HttpSession session = SessionUtils.getSession();
            int customerId= (int) session.getAttribute("id");
			return buyService.buy(customerId, currency);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    	
    }
	public List<Product> getSales() {
		HttpSession session = SessionUtils.getSession();
        int customerId= (int) session.getAttribute("id");
        Customer[] crs;
        List<Product> sales= new ArrayList<>();
		try {
			crs = buyService.getAllCustomer();
			 for(Customer cr : crs) {
				 if(cr.getId() == customerId) {
					 Product[] pro= cr.getProductSale();
					 if(pro != null) {
						 for(Product p: pro) {
							 if(p != null) {
								 sales.add(p);
							 }
						 }
						 return sales;
					 }
				 }
		        	
		        }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		return sales;
	}

    /*public List<Product> getClonedProducts(int size) {
        List<Product> results = new ArrayList<>();
        List<Product> originals = getProducts(size);
        for (Product original : originals) {
            results.add(original.clone());
        }
        return results;
    }*/
}
