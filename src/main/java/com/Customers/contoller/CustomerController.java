package com.Customers.contoller;

import com.Customers.dto.Customer;
import com.Customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<String> newCustomer(@RequestBody Customer newCustomer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.newCustomer(newCustomer));
    }

    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomerByName(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerByName(name));
    }

    @PostMapping("/sendSMS")
    public ResponseEntity<String> sendSMS(@RequestParam("name") String name,
                                              @RequestParam("message") String message,
                                          @RequestParam("messageCount") int messageCount) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.sendSMS(name, message, messageCount));
    }
    @GetMapping("/bill/{name}")
    public ResponseEntity<Double> getCurrentBill(@PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCurrentBill(name));
    }

}
