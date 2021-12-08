/**
 * Account.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bank.account;

public interface Account extends java.rmi.Remote {
    public void createAccount(int idCustomer) throws java.rmi.RemoteException;
    public boolean withdrawal(int idCustomer, double amount) throws java.rmi.RemoteException;
    public void deposit(int idCustomer, double amount) throws java.rmi.RemoteException;
    public double accountBalance(int idCustomer) throws java.rmi.RemoteException;
}
