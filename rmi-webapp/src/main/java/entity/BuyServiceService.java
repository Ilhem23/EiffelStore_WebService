/**
 * BuyServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entity;

public interface BuyServiceService extends javax.xml.rpc.Service {
    public java.lang.String getBuyServiceAddress();

    public entity.BuyService getBuyService() throws javax.xml.rpc.ServiceException;

    public entity.BuyService getBuyService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
