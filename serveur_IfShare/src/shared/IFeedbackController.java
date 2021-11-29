package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IFeedbackController extends Remote {
	
	public boolean addFeedBack(int productId, int employeeId, int rating, String comment) throws RemoteException;

	public boolean removeFeedBack(int id) throws RemoteException;
	
	public List<IFeedBack> searchByEmployee(int employeeId) throws RemoteException;
	
	public List<IFeedBack> searchByProduct(int productId) throws RemoteException;
	
	public IFeedBack searchById(int id) throws RemoteException;
	
	public List<IFeedBack> getAllFeedBacks() throws RemoteException ;
	
	public void setAllFeedBacks(List<IFeedBack> feedBacks) throws RemoteException ;
}
