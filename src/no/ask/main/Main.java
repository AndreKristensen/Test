package no.ask.main;

import java.util.ArrayList;
import java.util.List;

import no.ask.domain.Customer;
import no.ask.domain.Orders;
import no.ask.infrastructure.OrderJPA;

public class Main {

	public static void main(String[] args) {
		OrderJPA jpa = new OrderJPA();
		
		
		List<Customer> c = new ArrayList<Customer>();
		
		c = jpa.getAllCustomer();
		
		
		System.out.println(c.size());

//		Customer c = new Customer();
//		c.setName("TOre");
//		jpa.creatCustomer(c);
//		
//		Customer c2 = new Customer();
//		c2.setName("Ole");
//		jpa.creatCustomer(c2);
//		
//		Customer c3 = new Customer();
//		c3.setName("Knut");
//		jpa.creatCustomer(c3);
//		
//		
//		Orders o1 = new Orders();
//		o1.setCustomer(c);
//		o1.setAddress("nedre ulle");
//		
//		jpa.creatOrder(o1);
//		
//		Orders o2 = new Orders();
//		
//		o2.setCustomer(c);
//		o2.setAddress("address");
//		
//		jpa.creatOrder(o2);
		List<Orders> orderList = jpa.getAllOrders();
		
		System.out.println(orderList.size());
		System.out.println("all orders");
		for(Orders o: orderList){
			System.out.println(o.getAddress());
		}
		
		Orders n = orderList.get(0);
		n.setAddress("new Address");
		System.out.println("update a order" + jpa.updateOrder(n));
		System.out.println("updated");
		for(Orders o: orderList){
			System.out.println(o.getAddress());
		}
		
		System.out.println("orders from a customer");
//		orderList.clear();
		orderList = jpa.getAllOrdersFromACustomer("Andre");
		for(Orders o: orderList){
			System.out.println(o.getAddress());
		}
		
	}
	
}
