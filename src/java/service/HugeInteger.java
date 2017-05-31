/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jasper
 */
@WebService(serviceName = "HugeInteger")
public class HugeInteger {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "add")
    public String add(@WebParam(name = "first") String first, @WebParam(name = "second") String second) {
        //TODO write your implementation code here:
        return null;
    }
    
    @WebMethod(operationName = "subtract")
    public String subtract(@WebParam(name = "first") String first, @WebParam(name = "second") String second) {
        //TODO write your implementation code here:
        return null;
    }
    
    @WebMethod(operationName = "equals")
    public String equals(@WebParam(name = "first") String first, @WebParam(name = "second") String second) {
        //TODO write your implementation code here:
        return null;
    }
    
    @WebMethod(operationName = "bigger")
    public String bigger(@WebParam(name = "first") String first, @WebParam(name = "second") String second) {
        //TODO write your implementation code here:
        return null;
    }
    
    @WebMethod(operationName = "smaller")
    public String smaller(@WebParam(name = "first") String first, @WebParam(name = "second") String second) {
        //TODO write your implementation code here:
        return null;
    }
    
    @WebMethod(operationName = "toString")
    @Override
    public String toString() {
        //TODO write your implementation code here:
        return null;
    }
}
