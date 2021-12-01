package entity;

import java.rmi.RemoteException;

public interface IRequestObserver {

	void notifyChange() throws RemoteException;

}