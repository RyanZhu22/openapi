package com.ryan.openapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.openapi.common.model.entity.User;
import com.ryan.openapi.model.vo.LoginUserVO;
import com.ryan.openapi.model.vo.UserVO;
import com.ryan.openapi.model.dto.user.UserQueryRequest;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * User service
 *
 */
public interface UserService extends IService<User> {

    /**
     * user register
     *
     * @param userAccount   user account
     * @param userPassword  user password
     * @param checkPassword check password
     * @return new user id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * user login
     *
     * @param userAccount  user account
     * @param userPassword user password
     * @param request
     * @return user information vo
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);


    /**
     * get the currently logged-in user
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * get the currently logged in user（allow not logged in）
     *
     * @param request
     * @return
     */
    User getLoginUserPermitNull(HttpServletRequest request);

    /**
     * check the user is admin
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * check the user is admin
     *
     * @param user
     * @return
     */
    boolean isAdmin(User user);

    /**
     * user logout
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * get logged user information vo
     *
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * get user information vo
     *
     * @param user
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * get user information vo
     *
     * @param userList
     * @return
     */
    List<UserVO> getUserVO(List<User> userList);

    /**
     * get query condition
     *
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

}
