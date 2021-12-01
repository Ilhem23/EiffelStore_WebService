package luncher;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import service.FeedBackService;
import service.ProductService;
import service.PurshaseService;
import service.SaleService;


public class IfShareServer {


	public static void main(String[] args) throws RemoteException, MalformedURLException {
		try {
			LocateRegistry.createRegistry(1099);
			ProductService productService = ProductService.GetInstance();
			PurshaseService purshaseService = PurshaseService.GetInstance();
			SaleService saleService = SaleService.GetInstance();
			FeedBackService feedBack = FeedBackService.GetInstance();

			Naming.rebind("rmi://localhost:1099/productService", productService);
			Naming.rebind("rmi://localhost:1099/purshaseService", purshaseService);
			Naming.rebind("rmi://localhost:1099/saleService", saleService);
			Naming.rebind("rmi://localhost:1099/feedBack", feedBack);

		} catch (Exception e) {

			System.out.println("An Error Has Occured while running the server  stacktrace : " + e.getMessage());

		}

	}

}
