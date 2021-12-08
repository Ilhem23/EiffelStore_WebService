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
package org.eiffel.corp.views;

import org.eiffel.corp.services.StoreService;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import entity.FeedBack;
import entity.Product;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class StoreView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Product> products;
    private Product selectedProduct;
    private Double amount;

    public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Inject
    private StoreService service;
	

	@PostConstruct
    public void init() {
        products = service.getProducts(12);  
        }
	
   public List<FeedBack> getFeed(){
	   return service.getFeedBack(selectedProduct.getId());
   }
   public List<Product> getSales(){
	   
	   return service.getSales();
	   
   }
    public List<Product> getProducts() {
        return products;
    }

    public void setService(StoreService service) {
        this.service = service;
    }
    public String getBalance() {
    	return service.getBalance();
    }
    public void deposit() {
    	service.deposit(amount);
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Money Added"));
        PrimeFaces.current().ajax().update("formD:messageMoney");
        PrimeFaces.current().ajax().update("fbadge");
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    
    public void addToCart() {
        service.addProductToCart(this.selectedProduct.getId());
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product added to cart"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
    
    public boolean alreadyInCart(Product product) {
    	int productId = product.getId();
    	boolean available = product.isAvailability();
    	boolean alreadyInCart = service.alreadyInCart(productId);
    	return !available || alreadyInCart;
    }
    

    public void clearMultiViewState() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        PrimeFaces.current().multiViewState().clearAll(viewId, true, this::showMessage);
    }

    private void showMessage(String clientId) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, clientId + " multiview state has been cleared out", null));
    }
}
