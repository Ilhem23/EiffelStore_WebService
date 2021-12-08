package entity;

public class BuyServiceProxy implements entity.BuyService {
  private String _endpoint = null;
  private entity.BuyService buyService = null;
  
  public BuyServiceProxy() {
    _initBuyServiceProxy();
  }
  
  public BuyServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initBuyServiceProxy();
  }
  
  private void _initBuyServiceProxy() {
    try {
      buyService = (new entity.BuyServiceServiceLocator()).getBuyService();
      if (buyService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)buyService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)buyService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (buyService != null)
      ((javax.xml.rpc.Stub)buyService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public entity.BuyService getBuyService() {
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService;
  }
  
  public double convert(double montant, java.lang.String currency) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.convert(montant, currency);
  }
  
  public double balance(int customerId) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.balance(customerId);
  }
  
  public int getRatingByProduct(int productId) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.getRatingByProduct(productId);
  }
  
  public entity.FeedBack[] getFeedBackByProduct(int productId) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.getFeedBackByProduct(productId);
  }
  
  public void createBankAccount(int customerId) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    buyService.createBankAccount(customerId);
  }
  
  public void removeProductFromCart(int customerId, int productId) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    buyService.removeProductFromCart(customerId, productId);
  }
  
  public java.lang.String getAllCurencies() throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.getAllCurencies();
  }
  
  public entity.Product convertProduct(int n) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.convertProduct(n);
  }
  
  public entity.Product[] getProductList() throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.getProductList();
  }
  
  public entity.Customer[] getAllCustomer() throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.getAllCustomer();
  }
  
  public int getIdCounter() throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.getIdCounter();
  }
  
  public boolean buy(int customerId, java.lang.String currency) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.buy(customerId, currency);
  }
  
  public java.lang.String getNameEmployee(int id) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.getNameEmployee(id);
  }
  
  public entity.Customer loginCustomer(java.lang.String login, java.lang.String password) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.loginCustomer(login, password);
  }
  
  public boolean withdrawal(int customerId, double amount) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.withdrawal(customerId, amount);
  }
  
  public entity.Customer addCustomer(java.lang.String firstName, java.lang.String lastName, java.lang.String email, java.lang.String login, java.lang.String password) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.addCustomer(firstName, lastName, email, login, password);
  }
  
  public entity.Product[] getCart(int customerId) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    return buyService.getCart(customerId);
  }
  
  public void addProductToCart(int customerId, int productId) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    buyService.addProductToCart(customerId, productId);
  }
  
  public void deposit(int customerId, double amount) throws java.rmi.RemoteException{
    if (buyService == null)
      _initBuyServiceProxy();
    buyService.deposit(customerId, amount);
  }
  
  
}