package com.csi.controller;

import com.csi.model.Customer;
import com.csi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerService customerServiceImpl;

    @PostMapping("/savedata")

    public ResponseEntity<String>saveData(@RequestBody Customer customer){
        customerServiceImpl.saveData(customer);

        return ResponseEntity.ok("Data Saved Successfully");
    }

    @GetMapping("/getdatabyid/{custId}")

    public  ResponseEntity<Customer>getDataById(@PathVariable int custId){
        return ResponseEntity.ok(customerServiceImpl.getDataById(custId));
    }

    @GetMapping("/getalldata")
    public  ResponseEntity<List<Customer>>getAllData(){
        return ResponseEntity.ok(customerServiceImpl.getAllData());
    }
    @PutMapping("/updatedata/{custId}")

    public  ResponseEntity<String> updateData(@PathVariable int custId,@RequestBody Customer customer){
        customerServiceImpl.updateData(custId,customer);
        return ResponseEntity.ok("Data updated Successfully");
    }
    @DeleteMapping("/deletealldata")

    public ResponseEntity<String>deleteAllData(){
        customerServiceImpl.deleteAllData();
        return ResponseEntity.ok("Data delete Successfully");
    }
    @GetMapping("/getdatabyname/{custName}")

    public ResponseEntity<List<Customer>>getDataByName(@PathVariable String custName){
        return ResponseEntity.ok(customerServiceImpl.getAllData().stream().filter(emp-> emp.getCustName().equals(custName)).collect(Collectors.toList()));
    }

}
