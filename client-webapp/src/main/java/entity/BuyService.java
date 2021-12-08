/**
 * BuyService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entity;

public interface BuyService extends java.rmi.Remote {
    public double convert(double montant, java.lang.String currency) throws java.rmi.RemoteException;
    public double balance(int customerId) throws java.rmi.RemoteException;
    public entity.FeedBack[] getFeedBackByProduct(int productId) throws java.rmi.RemoteException;
    public void removeProductFromCart(int customerId, int productId) throws java.rmi.RemoteException;
    public void createBankAccount(int customerId) throws java.rmi.RemoteException;
    public int getRatingByProduct(int productId) throws java.rmi.RemoteException;
    public entity.Customer addCustomer(java.lang.String firstName, java.lang.String lastName, java.lang.String email, java.lang.String login, java.lang.String password) throws java.rmi.RemoteException;
    public int getIdCounter() throws java.rmi.RemoteException;
    public java.lang.String getAllCurencies() throws java.rmi.RemoteException;
    public entity.Product[] getProductList() throws java.rmi.RemoteException;
    public entity.Product convertProduct(int n) throws java.rmi.RemoteException;
    public entity.Customer[] getAllCustomer() throws java.rmi.RemoteException;
    public void addProductToCart(int customerId, int productId) throws java.rmi.RemoteException;
    public boolean withdrawal(int customerId, double amount) throws java.rmi.RemoteException;
    public entity.Customer loginCustomer(java.lang.String login, java.lang.String password) throws java.rmi.RemoteException;
    public boolean buy(int customerId, java.lang.String currency) throws java.rmi.RemoteException;
    public entity.Product[] getCart(int customerId) throws java.rmi.RemoteException;
    public void deposit(int customerId, double amount) throws java.rmi.RemoteException;
    public java.lang.String getNameEmployee(int id) throws java.rmi.RemoteException;
}
