package entity;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import shared.IFeedBack;

public class FeedBack implements Serializable{
	
    public FeedBack() {
		
	}
    
	
    private int productId;
    private String employeeName;
    private int rating;	
	private String comment;
	private Date dateComment;
	
	
	public FeedBack(int productId, String employeeName, int rating, String comment) throws RemoteException {
		super();
		this.productId = productId;
		this.employeeName = employeeName;
		this.rating = rating;
		this.comment = comment;
		this.dateComment= new Date();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	
	
}
