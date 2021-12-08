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

import org.eiffel.corp.services.StoreEmpService;
import org.eiffel.corp.services.StoreService;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import entity.FeedBack;
import entity.Product;
import shared.IFeedBack;
import shared.IProduct;
import shared.IPurshase;

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
	private List<IProduct> products;
    private IProduct selectedProduct;
    private Double amount;

    public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Inject
    private StoreEmpService service;
	

	@PostConstruct
    public void init() {
        products = service.getProducts(12);  
        }
	
   public List<IFeedBack> getFeed(){
	   try {
		return service.getFeedBack(selectedProduct.getId());
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return null;
   }
   public String getEmployeeName(int id) {
	   return service.getEmployeeName(id);
   }

    public List<IProduct> getProducts() {
        return products;
    }

    public void setService(StoreEmpService service) {
        this.service = service;
    }
   

    public IProduct getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(IProduct selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    
    public List<IProduct> getPurchase(){
 	   return service.getPurchases(); 
    }
    public List<IProduct> getSales(){
  	   return service.getSales(); 
     }
    
    public boolean alreadyInCart(IProduct product) {
    	try {
			int productId = product.getId();
			boolean available = product.isAvailability();
			boolean isEmp= service.verif(productId);
	    	return !available || !isEmp;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    
    public String getInventory(int productId) {
    	return service.getInventory(productId);
    }
    public String getInventoryName(int productId) {
    	return service.getInventoryName(productId);
    }
    public int getRatingByProduct(int productId) {
    	return service.getRatingByProduct(productId);
    }
    public void purchase() {
    	IPurshase purshase;
		try {
			purshase = service.purchase(selectedProduct.getId());
			if(purshase != null) {
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("purchase successful"));
	    		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
	    		
	    	}else {
	    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Purchase rejected!","try again"));
	    		PrimeFaces.current().ajax().update("form:messages");
	    	
	    		
	    	}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
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
