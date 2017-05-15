package com.weixin.controller;

import com.weixin.common.BaseController;
import com.weixin.common.MyRunTimeExcption;
import com.weixin.common.ResultObj;
import com.weixin.entity.Customer;
import com.weixin.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZPC on 2017/3/17.
 */
@RequestMapping(value = "/customer")
@Controller
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/goTest")
    public ModelAndView test(HttpServletRequest request) {
        String referUrl = request.getHeader("Referer");
        logger.warn("【进入了测试控制层】。。。。。。");
        logger.warn("【上一个请求的地址为：" + referUrl + "】。。。。。");
        return new ModelAndView("/test/test.html");
    }

    @RequestMapping(value = "/goOther")
    public ModelAndView goOther(HttpServletRequest request) {
        String referUrl = request.getHeader("Referer");
        logger.warn("【进入了其它控制层】。。。。。。");
        logger.warn("【上一个请求的地址为：" + referUrl + "】。。。。。");
        return new ModelAndView("/other/other.html");
    }

    @RequestMapping(value = "/getCustomerList")
    public ModelAndView getCustomerList(HttpServletRequest request) {
        String referUrl = request.getHeader("Referer");
        logger.warn("【进入了用户控制层】。。。。。。");
        logger.warn("【上一个请求的地址为：" + referUrl + "】。。。。。");
        List<Customer> customerList = customerService.getCustomerList();
        return new ModelAndView("/customer/customer.jsp")
                .addObject("name", customerList.get(0).getCname());
    }


    /**
     * 跳转到customer相关页面
     * @return
     */
    @RequestMapping(value = "/goCustomer")
    public ModelAndView goCustomer() {
        return new ModelAndView("/customer/customer.jsp");
    }


    /**
     * AJAX根据cid获取Customer
     * @param cid
     * @return
     */
    @RequestMapping(value= "/ajax", method = RequestMethod.POST)
    @ResponseBody
    public String ajax(Integer cid) {

        ResultObj resultObj = new ResultObj();
        resultObj.setCode(ResultObj.RESULT_FAIL);

        try {
            Customer customer = customerService.getCustomerById(cid);
            resultObj.setCode(ResultObj.RESULT_SUCCESS);
            resultObj.setObj(customer);
        } catch (MyRunTimeExcption e) {
            logger.warn(e.getMessage());
            resultObj.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            resultObj.setMsg("系统错误");
        }

        return resultObj.toJson();
    }


    /**
     * 根据cid获取Customer
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getCustomerById", method = RequestMethod.GET)
    @ResponseBody
    public String getCustomerById(Integer cid) {
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(ResultObj.RESULT_FAIL);

        try {
            Customer customer = customerService.getCustomerById(cid);
            resultObj.setCode(ResultObj.RESULT_SUCCESS);
            resultObj.setObj(customer);
        } catch (MyRunTimeExcption e) {
            logger.warn("*****" + e.getMessage() + "*****");
            resultObj.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.warn("*****" + e.getMessage() + "*****");
            resultObj.setMsg("系统错误");
        }

        return resultObj.toJson();
    }


    /**
     * 清空缓存
     * @param cid
     * @return
     */
    @RequestMapping(value = "/clearCache", method = RequestMethod.GET)
    @ResponseBody
    public String clearCache(Integer cid) {

        ResultObj resultObj = new ResultObj();
        resultObj.setCode(ResultObj.RESULT_FAIL);

        try {
            customerService.clearCache(cid);
        } catch (MyRunTimeExcption e) {
            logger.warn(e.getMessage());
            resultObj.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            resultObj.setMsg("系统错误");
        }

        return resultObj.toJson();
    }


    /**
     * 更新用户，并且清空缓存
     * @param cid
     * @param age
     * @param address
     * @return
     */
    @RequestMapping(value = "/updateCustomer", method = RequestMethod.GET)
    @ResponseBody
    public String updateCustomer(Integer cid, Integer age, String address) {
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(ResultObj.RESULT_FAIL);

        try {
            Customer customer = customerService.update(cid, age, address);
            resultObj.setCode(ResultObj.RESULT_SUCCESS);
            resultObj.setObj(customer);
        } catch (MyRunTimeExcption e) {
            logger.warn(e.getMessage());
            resultObj.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            resultObj.setMsg("系统错误");
        }

        return resultObj.toJson();
    }


    /**
     * 插入List<Customer>
     * @return
     */
    @RequestMapping(value = "/insertList", method = RequestMethod.GET)
    @ResponseBody
    public String testInsertList() {

        ResultObj resultObj = new ResultObj();
        resultObj.setCode(ResultObj.RESULT_FAIL);

        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            Customer customer = new Customer();
            customer.setCname("李寻欢" + i);
            customer.setAge(40 + i);
            customer.setAddress("北京" + i);
            customer.setBirthday(new Date());
            customerList.add(customer);
        }

        try {
            customerService.insertList(customerList);
            resultObj.setCode(ResultObj.RESULT_SUCCESS);
        } catch (MyRunTimeExcption e) {
            logger.warn("<<<<" + e.getMessage() + ">>>>。。。");
            resultObj.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.warn("<<<<" + e.getMessage() + ">>>>。。。");
            resultObj.setMsg("系统错误");
        }

        return resultObj.toJson();
    }


}
