/**
 * AccountService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bank.account;

public interface AccountService extends javax.xml.rpc.Service {
    public java.lang.String getAccountAddress();

    public bank.account.Account getAccount() throws javax.xml.rpc.ServiceException;

    public bank.account.Account getAccount(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
