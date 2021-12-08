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

import entity.Product;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public class CrudeView implements Serializable {

    private List<Product> products;

    private Product selectedProduct;

    private List<Product> selectedProducts;
    
    private int totalePrice;
    
    private String totaleCurrencyPrice;
    
    private String currency;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Inject
    private StoreService productService;

    @PostConstruct
    public void init() {
        this.products = this.productService.getCart();
        this.totalePrice = this.productService.getTotalePrice();
        this.totaleCurrencyPrice = String.valueOf(this.productService.getTotalePrice());
        this.currency= "EUR";
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getTotalPrice() {
    	return this.productService.getTotalePrice();
    }
    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = new Product();
    }

    public void saveProduct() {
       
    }

    public void deleteProduct() {
        this.products.remove(this.selectedProduct);
        productService.removeFromCart(this.selectedProduct.getId());
        this.selectedProduct = null; 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().ajax().update("form:dt-price");
    }
    public void payment() {
    	boolean verif= productService.makePayment(currency);
    	if(verif) {
    		productService.removeCart();
    		this.selectedProduct = null; 
    		this.products.clear();
    		this.totalePrice = 0;
    		this.totaleCurrencyPrice = "";
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Payment accepted"));
    		PrimeFaces.current().ajax().update("form:messages", "form:dt-products", "form:dt-price","form:dt-price-curr");
    		PrimeFaces.current().ajax().update("fbadge");
    	}else {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Payment rejected!","Insufficient credit"));
    		PrimeFaces.current().ajax().update("form:messages");
    	
    		
    	}
    	
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedProducts.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }

    public void deleteSelectedProducts() {
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }
    public List<String> getAllCurrency(){
    	return productService.getAllCurrency();
    }
    
    public void updateCurrency() {
    	totaleCurrencyPrice = String.format("%.2f",productService.convert(currency));
    }

    public void calculateTotalPriceCurrency() {
    	
    }
    
	public String getTotaleCurrencyPrice() {
		return totaleCurrencyPrice;
	}

	public void setTotaleCurrencyPrice(String totaleCurrencyPrice) {
		this.totaleCurrencyPrice = totaleCurrencyPrice;
	}

	public int getTotalePrice() {
		return totalePrice;
	}

	public void setTotalePrice(int totalePrice) {
		this.totalePrice = totalePrice;
	}

}
