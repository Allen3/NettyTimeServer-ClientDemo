/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nettyhttp_xmldemo.pojo;

/**
 *
 * @author allen
 */
public class Order {
    private long orderNumber;
    
    private Customer customer;
    
    private Address billTo;
    private Address shipTo;
    
    private Shipping shipping;
    
    private Float total;

    public long getOrderNumber() {
        return orderNumber;
    }   //getOrderNumber()

    public Customer getCustomer() {
        return customer;
    }   //getCustomer()

    public Address getBillTo() {
        return billTo;
    }   //getBillTo()

    public Address getShipTo() {
        return shipTo;
    }   //getShipTo()

    public Shipping getShipping() {
        return shipping;
    }   //getShipping()

    public Float getTotal() {
        return total;
    }   //getTotal()

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }   //setOrderNumber()

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }   //setCustomer()

    public void setBillTo(Address billTo) {
        this.billTo = billTo;
    }   //setBillTo()

    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }   //setShipTo()

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }   //setShipping()

    public void setTotal(Float total) {
        this.total = total;
    }   //setTotal()                
}   //Order
