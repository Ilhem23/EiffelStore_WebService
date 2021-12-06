package org.eiffel.corp.views;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.eiffel.corp.services.LoginService;
import org.eiffel.corp.services.SignupService;
import org.eiffel.corp.utils.SessionUtils;

@Named
@RequestScoped
public class SignupView implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String firstname;
	private String lastname;
	private String email;
	private String username;
	private String password;


	//validate login
	public String createUser() {
		return "login";
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public String validateSignUp() {
		
		try {
			int valid = SignupService.signup(firstname,lastname, email, username, password);
			return "login";
		} catch (RemoteException | ServiceException e) {
			e.printStackTrace();
		}
		return "signup";
	}

}