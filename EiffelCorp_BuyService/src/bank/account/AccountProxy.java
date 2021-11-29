package bank.account;

public class AccountProxy implements bank.account.Account {
  private String _endpoint = null;
  private bank.account.Account account = null;
  
  public AccountProxy() {
    _initAccountProxy();
  }
  
  public AccountProxy(String endpoint) {
    _endpoint = endpoint;
    _initAccountProxy();
  }
  
  private void _initAccountProxy() {
    try {
      account = (new bank.account.AccountServiceLocator()).getAccount();
      if (account != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)account)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)account)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (account != null)
      ((javax.xml.rpc.Stub)account)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public bank.account.Account getAccount() {
    if (account == null)
      _initAccountProxy();
    return account;
  }
  
  public void createAccount(int idCustomer) throws java.rmi.RemoteException{
    if (account == null)
      _initAccountProxy();
    account.createAccount(idCustomer);
  }
  
  public void deposit(int idClient, double amount) throws java.rmi.RemoteException{
    if (account == null)
      _initAccountProxy();
    account.deposit(idClient, amount);
  }
  
  public boolean withdrawal(int idClient, double amount) throws java.rmi.RemoteException{
    if (account == null)
      _initAccountProxy();
    return account.withdrawal(idClient, amount);
  }
  
  public double accountBalance(int idClient) throws java.rmi.RemoteException{
    if (account == null)
      _initAccountProxy();
    return account.accountBalance(idClient);
  }
  
  
}