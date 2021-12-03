package entity;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.xml.bind.annotation.XmlRootElement;


import shared.IProduct;


public class Product implements IProduct, Serializable {
	private int id;
	private String name;
	private String category;
	private String description;
	private boolean availability;
	private String image;
	private int price;
	private String state;
	private int rating;
	private String inventoryStatus;
	private String inventoryName;
	
	public Product(int id, String name, String category, boolean availability, String image, int price, String state) throws RemoteException {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.availability = availability;
		this.image = image;
		this.price = price;
		this.state = state;
	}
	
	public Product() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}


	public String getDescription() {
		
		return description;
	}

	
	public void setDescription(String description) {
		this.description= description;
		
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

}
