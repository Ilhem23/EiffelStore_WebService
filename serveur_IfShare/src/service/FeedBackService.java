package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.FeedBack;
import shared.IFeedBack;
import shared.IFeedbackController;
import shared.IPurshase;

public class FeedBackService extends UnicastRemoteObject implements IFeedbackController{
	Map<Integer,List<IFeedBack>> feedbacks;
	private int idCounter= 0;
	protected FeedBackService() throws RemoteException {
		super();
		feedbacks= new HashMap<Integer, List<IFeedBack>>();
	}
	private static FeedBackService single_instance = null;

	public static FeedBackService GetInstance() throws RemoteException {
		// To ensure only one instance is created
		if (single_instance == null) {
			single_instance = new FeedBackService();
		}
		return single_instance;
	}


	@Override
	public boolean addFeedBack(int productId, int employeeId, int rating, String comment) throws RemoteException {
		IFeedBack feedback= new FeedBack(getIdCounter(), productId, employeeId, rating, comment);
		if(feedbacks.containsKey(productId))
		{
			List<IFeedBack> fb = feedbacks.get(productId);
			fb.add(feedback);
			
		}else {
			List<IFeedBack> fb= new ArrayList<IFeedBack>();
			fb.add(feedback);
			feedbacks.put(productId, fb);	
		}
		
		return true;
	}

	@Override
	public boolean removeFeedBack(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<IFeedBack> searchByEmployee(int employeeId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IFeedBack> searchByProduct(int productId) throws RemoteException {
		
		return feedbacks.get(productId);
	}

	@Override
	public IFeedBack searchById(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IFeedBack> getAllFeedBacks() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAllFeedBacks(List<IFeedBack> feedBacks) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	public int getIdCounter() {
		idCounter++;
		return idCounter;
	}


}
