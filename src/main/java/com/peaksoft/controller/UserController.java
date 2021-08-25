package com.peaksoft.controller;

import com.peaksoft.model.Role;
import com.peaksoft.model.User;
import com.peaksoft.service.RoleService;
import com.peaksoft.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String getHomePage(){
        return "home-page";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }


    @GetMapping("/user")
    public String getUser(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user-page";
    }

    @GetMapping("/admin")
    public String listUser(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    } @GetMapping("/users/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "/show";
    }


    @GetMapping("/add-user")
    public String createUserForm(@ModelAttribute("user") User user, Model model){
        model.addAttribute("user", user);
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "add-user";
    }

    @PostMapping("/user")
    public String createUser(User user, @RequestParam Map<String, String> form) {
        List<String> roles = roleService.getRoleNamesToList();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
            }
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("users/{id}/edit")
    public String updateUserForm( @PathVariable("id") long id, Model model){
        User user = userService.get(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "update-user";
    }

    @PostMapping("/users/{id}/edit")
    public String updateUser(@ModelAttribute("user")User user, @RequestParam Map<String, String> form){
        List<String> roles = roleService.getRoleNamesToList();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key: form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
            }
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUser(userService.get(id));
        return "redirect:/admin";
    }
}
