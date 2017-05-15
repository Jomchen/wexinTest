package com.weixin.mapper;

import com.weixin.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by ZPC on 2017/3/17.
 */
public interface CustomerMapper {

    Customer getCustomerById(Integer cid);

    Integer insert(Customer customer);

    Integer update(Customer customer);

    Integer insertList(List<Customer> list);

    List<Customer> getCustomerList();

    List<Customer> testMapString(Map<String, String> map);

}
