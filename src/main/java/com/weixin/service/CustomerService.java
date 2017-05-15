package com.weixin.service;

import com.weixin.entity.Customer;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by ZPC on 2017/3/17.
 */
public interface CustomerService {

    Customer getCustomerById(Integer cid);

    void clearCache(Integer cid);

    Customer update(Integer cid, Integer age, String address);

    Integer insert(Customer customer);

    Integer insertList(List<Customer> customerList);

    List<Customer> getCustomerList();

    List<Customer> testMapString(Map<String, String> map);

}
