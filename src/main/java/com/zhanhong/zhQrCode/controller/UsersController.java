package com.zhanhong.zhQrCode.controller;

import com.zhanhong.zhQrCode.config.ServerConfig;
import com.zhanhong.zhQrCode.entity.Users;
import com.zhanhong.zhQrCode.service.UsersService;
import com.zhanhong.zhQrCode.util.IpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

@Api(value = "个人信息类")
@RequestMapping("/users")
@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Value("${server.Iport}")
    private String Iport;

    /**
     * 用户跳转注册页
     *
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("users/register");
        return modelAndView;
    }

    /**
     * 用户跳转二维码页
     *
     * @return
     */
    @RequestMapping(value = "jumpQrCode")
    public ModelAndView jumpQrCode(String Id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("users/qrCode");
        modelAndView.addObject("Id", Id);
        modelAndView.addObject("Iport", Iport);
        return modelAndView;
    }


    /**
     * 用户跳转查询页
     *
     * @return
     */
    @RequestMapping(value = "jumpUsersInfo")
    public ModelAndView jumpUsersInfo(String Id) {
        ModelAndView modelAndView = new ModelAndView("users/details");
        Users u = usersService.queryUsersById(Id);
        modelAndView.addObject("users", u);
        return modelAndView;
    }

    /**
     * @param
     * @return
     */
    @ApiOperation(value = "个人信息注册", notes = "客户根据需求填写完毕后保存入库，并返回一个唯一值ID用于查询客户信息")
    @RequestMapping("/saveUsers")
    @ResponseBody
    public Map<String, Object> saveUsers(Users users, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "400");//返回错误编号
        result.put("errMsg", "信息保存失败");//返回错误信息
        result.put("Id", users.getId());
        if (CheckUsers(users, request) > 0) {
            result.put("errMsg", "您已注册，请勿重复注册。");//返回错误信息
            return result;
        }
        try {
            String id = usersService.inserUsers(users);
            result.put("code", 200);//返回错误编号
            result.put("errMsg", "信息保存成功");//返回错误信息
            result.put("Id", id);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("errMsg", "调用接口时出错，错误原因：" + e.getMessage());//返回错误信息
        }
        return result;
    }

    public int CheckUsers(Users users, HttpServletRequest request) {

        int result = usersService.CheckUsers(users);
        return result;
    }


    /**
     * @param
     * @return
     */
    /* 方法注解 */
    @ApiOperation(value = "个人信息查询", notes = "个人信息注册返回一的唯一值ID用于查询客户信息")
    @RequestMapping("/queryUsers")
    @ResponseBody
    public Map<String, Object> queryUsers(String Id) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "400");//返回错误编号
        result.put("errMsg", "信息查询失败");//返回错误信息
        try {
            Users u = usersService.queryUsersById(Id);
            result.put("code", 200);//返回错误编号
            result.put("errMsg", "信息查询成功");//返回错误信息
            result.put("users", u);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("errMsg", "调用接口时出错，错误原因：" + e.getMessage());//返回错误信息
        }
        return result;
    }

}