package com.weixin.service.impl;

import com.weixin.common.MyRunTimeExcption;
import com.weixin.entity.Customer;
import com.weixin.mapper.CustomerMapper;
import com.weixin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ZPC on 2017/3/17.
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    /*这几个缓存注解的方法之间如果是由内部调用，也就是this调用，则缓存会失效*/

    /*key相当于缓存的主键，如果不指定则默认由方法的所有参数组合而成作为主键
    条件缓存*/
    @Override
    @Cacheable(value = "myCache00", key = "#cid", condition="#cid > 0")
    public Customer getCustomerById(Integer cid) {
        return customerMapper.getCustomerById(cid);
    }

    /*对key进行缓存清空*/
    @Override
    @CacheEvict(value = "myCache00", key = "#cid")
    public void clearCache(Integer cid) { }

    /*总是执行方法，并且执行后把结果存入缓存*/
    @CachePut(value = "myCache00", key = "#cid")
    @Override
    public Customer update(Integer cid, Integer age, String address) {
        Customer customer = getCustomerById(cid);
        if (null == customer) {
            throw new MyRunTimeExcption("【没有扎到对应的信息】");
        }
        customer.setAge(age);
        customer.setAddress(address);
        customerMapper.update(customer);
        return customer;
    }


    @Override
    public Integer insert(Customer customer) {
        return customerMapper.insert(customer);
    }


    @Override
    public Integer insertList(List<Customer> customerList) {
        return customerMapper.insertList(customerList);
    }


    public List<Customer> getCustomerList() {
        return customerMapper.getCustomerList();
    }


    public List<Customer> testMapString(Map<String, String> map) {
        Set<Map.Entry<String, String>> set = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("【" + entry.getKey() + ":" + entry.getValue() + "】");
        }

        return customerMapper.testMapString(map);
    }


}
