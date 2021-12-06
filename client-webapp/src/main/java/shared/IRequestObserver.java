package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRequestObserver extends Remote{

	public void notifyChange() throws RemoteException;

}