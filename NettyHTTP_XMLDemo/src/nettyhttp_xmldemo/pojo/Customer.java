/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyhttp_xmldemo.pojo;

import java.util.List;

/**
 *
 * @author allen
 */
public class Customer {
    
    private long customerNumber;
    private String firstName;
    private String lastName;
    private List<String> middleNames;

    public long getCustomerNumber() {
        return customerNumber;
    }   //getCustomerNumber()

    public String getFirstName() {
        return firstName;
    }   //getFirstName()

    public String getLastName() {
        return lastName;
    }   //getLastName()

    public List<String> getMiddleNames() {
        return middleNames;
    }   //getMiddleNames()

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }   //setCustomerNumber()

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }   //setFirstName()

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }   //setLastName()

    public void setMiddleNames(List<String> middleNames) {
        this.middleNames = middleNames;
    }   //setMiddleNames()  
}   //Customer
