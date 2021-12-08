package org.eiffel.corp.views;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.eiffel.corp.services.StoreEmpService;
import org.eiffel.corp.utils.SessionUtils;

import shared.Employee;
import shared.IEmployeeController;

@Named
@SessionScoped
public class LoginView implements Serializable {


	private static final long serialVersionUID = -9061100549670720066L;
	private String pwd;
	private String msg;
	private String user;
	private  IEmployeeController employeeService = null;
	
	@Inject
    private StoreEmpService service;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public String validateUsernamePassword() {
		try {
			employeeService= (IEmployeeController) Naming.lookup("rmi://localhost:1100/employeeService");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Employee cr = null;
		try {
			cr = employeeService.login(user, pwd);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
     
		if (cr != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", cr.getLogin());
			session.setAttribute("id", cr.getId());
			return "home";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "login";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
}