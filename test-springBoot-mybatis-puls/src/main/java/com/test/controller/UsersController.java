package com.test.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.pojo.Users;
import com.test.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 索方成
 * @since 2020-02-20
 */
@Controller
/*@RequestMapping("/users")*/
public class UsersController {
    @Resource
    private UsersService usersService;

    @GetMapping("index")
    public String getUsers(Model model, @RequestParam(defaultValue = "1")int pageIndex){
        List<Users> list = usersService.list(new QueryWrapper<>());
        model.addAttribute("user",list);

        IPage<Users> page =new Page<>(pageIndex,3);
        QueryWrapper<Users> users = new QueryWrapper<>();
        IPage<Users> page1 = usersService.page(page, users);
        model.addAttribute("page",page1);

        return "index";
    }

    @RequestMapping("/addUser")
    public String addUser(){
        return "addUser";
    }

    @RequestMapping("/addUserSave")
    public String addUserSave(Users user){
        boolean save = usersService.save(user);
        if (save) {
            return "redirect:/index";
        }
        return "addUser";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id){
        boolean b = usersService.removeById(id);
        if (b) {
            return "redirect:/index";
        }
        return "redirect:/index";
    }

    @RequestMapping("updateUser/{id}")
    public String updateUser(@PathVariable int id,Model model){
        Users byId = usersService.getById(id);
        model.addAttribute("user",byId);
        return "update";
    }


    @RequestMapping("updateUserSave")
    public String updateUser(Users user){
        boolean b = usersService.updateById(user);// 根据 ID 选择修改
        if (b) {
            return "redirect:/index";
        }
        return "update";
    }
}

