
package com.architect.webservice.service.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "cxfJaxUserInterface", targetNamespace = "http://cxf.service.webservice.architect.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CxfJaxUserInterface {


    /**
     * 
     * @param name
     * @return
     *     returns com.architect.webservice.service.cxf.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "queryUser", targetNamespace = "http://cxf.service.webservice.architect.com/", className = "com.architect.webservice.service.cxf.QueryUser")
    @ResponseWrapper(localName = "queryUserResponse", targetNamespace = "http://cxf.service.webservice.architect.com/", className = "com.architect.webservice.service.cxf.QueryUserResponse")
    public User queryUser(
        @WebParam(name = "name", targetNamespace = "")
        String name);

}