package com.Customers.service;

import com.Customers.dto.Customer;
import com.Customers.exception.customException;
import com.Customers.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public String newCustomer(Customer newCustomer) {
        customerRepository.save(newCustomer);
        return "customer created";
    }

    public Customer getCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    @Transactional
    public String sendSMS(String name, String message, int messageCount) {
        Customer customer = checkCustomer(name);

        double cost = calculateCost(customer.getPlan(), messageCount);
        if (cost < 0) {
            throw new customException(HttpStatus.BAD_REQUEST, "Invalid number of messages");
        }
        customer.setMsgCount(customer.getMsgCount() + messageCount);
        customer.setBill(customer.getBill() + cost);

        customerRepository.save(customer);
        return "Message sent successfully. Cost is : " + cost;
    }
    private double calculateCost(String plan, int messages) {
        double cost = 0;
        switch (plan) {
            case "Basic":
                cost = messages * 0.001;
                break;
            case "Silver":
                if (messages <= 100) {
                    cost = 0;
                } else {
                    cost = (messages - 100) * 0.002;
                }
                break;
            case "Gold":
                if (messages <= 1000) {
                    cost = 0;
                } else {
                    cost = (messages - 1000) * 0.003;
                }
                break;
            default:
                cost = -1;
        }
        return cost;
    }


    public Double getCurrentBill(String name) {
        Customer customer = checkCustomer(name);
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        double bill = getBillByMonth(customer, month);
        return  bill;
    }
    private double getBillByMonth(Customer customer, int month) {
        return customer.getBill();
    }
    private Customer checkCustomer(String name) {
        Customer customer = customerRepository.findByName(name);
        if (customer == null) {
            throw new customException(HttpStatus.BAD_REQUEST, "Invalid customer name");
        }
        return customer;
    }

}
