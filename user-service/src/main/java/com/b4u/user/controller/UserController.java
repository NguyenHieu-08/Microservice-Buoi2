package com.b4u.user.controller;

import com.b4u.user.constant.ApplicationConstant;
import com.b4u.user.domain.Role;
import com.b4u.user.domain.User;
import com.b4u.user.dto.Result;
import com.b4u.user.dto.UserSignUp;
import com.b4u.user.service.RoleService;
import com.b4u.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserSignUp userSignUp) {
        Result result = new Result();
        try {
            if (userService.existsByUsername(userSignUp.getUsername())) {
                result.setStatusCode(ApplicationConstant.STATUS_FAIL);
                result.setData("Error: Username is already taken!");
                return ResponseEntity
                        .badRequest()
                        .body(result);
            }

            if (userService.existsByEmail(userSignUp.getEmail())) {
                result.setStatusCode(ApplicationConstant.STATUS_FAIL);
                result.setData("Error: Email is already in use!");
                return ResponseEntity
                        .badRequest()
                        .body(result);
            }
            // TODO: Create new user's account -> save DB
            userService.saveNewUser(userSignUp);
            result.setStatusCode(ApplicationConstant.STATUS_SUCCESS);
            result.setData("User registered successfully!");
        } catch (Exception e) {
            result.setStatusCode(ApplicationConstant.STATUS_FAIL);
            result.setData("User registered unsuccessfully!");
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/assign/{userId}/role/{roleId}")
    public ResponseEntity<?> setRoleForUser(@PathVariable Long userId,
                                            @PathVariable Long roleId) {
        Result result = new Result();
        //TODO: check user or role: if not existing return status fail
        // else update role for user
        try {
            if(userService.findByUserId(userId).isEmpty()){
                result.setStatusCode(ApplicationConstant.STATUS_FAIL);
                result.setData("Error: Username is not exist!");
                return ResponseEntity
                        .badRequest()
                        .body(result);
            }

            if(roleService.findByRoleId(roleId).isEmpty()) {
                result.setStatusCode(ApplicationConstant.STATUS_FAIL);
                result.setData("Error: Role is not exist!");
                return ResponseEntity
                        .badRequest()
                        .body(result);
            }
            userService.saveUserRole(userId, roleId);
            result.setStatusCode(ApplicationConstant.STATUS_SUCCESS);
            result.setData("Roles added to user with ID: " + userId);
        }
        catch (Exception e) {
            result.setStatusCode(ApplicationConstant.STATUS_FAIL);
            result.setData("User registered unsuccessfully!");
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
}
