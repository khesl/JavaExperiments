package client.webServiceDep;

import java.io.IOException;

import java.net.MalformedURLException;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://manualRequest.com.beans/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DictRequestWebService {
    
    @WebMethod
    public ArrayList<SelectItem> callRequest(ArrayList<RequestClass> requestItems) throws MalformedURLException,
                                                                                         IOException;
   
    @WebMethod
    public ArrayList<SelectItem> callRequest2(ArrayList<SelectItem> selectLItems) throws MalformedURLException,
                                                                                         IOException;
    
    @WebMethod
    public String callRequest4(String request) throws MalformedURLException, 
                                                      IOException;
    
}