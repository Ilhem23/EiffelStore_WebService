package entity;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import shared.IFeedBack;

public class FeedBack implements IFeedBack, Serializable{
	
    public FeedBack() {
		
	}
    
	private  int id;
    private int productId;
    private int employeeId;
    private int rating;	
	private String comment;
	private Date dateComment;
	
	
	public FeedBack(int id, int productId, int employeeId, int rating, String comment) throws RemoteException {
		super();
		this.id = id;
		this.productId = productId;
		this.employeeId = employeeId;
		this.rating = rating;
		this.comment = comment;
		this.dateComment= new Date();
	}
 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDateComment() {
		return dateComment;
	}

	public void setDateComment(Date dateComment) {
		this.dateComment = dateComment;
	}
	
	
	
}
