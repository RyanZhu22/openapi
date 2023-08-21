package com.ryan.openapi.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * create request
 *
 */
@Data
public class UserAddRequest implements Serializable {

    /**
     * username
     */
    private String userName;

    /**
     * account
     */
    private String userAccount;

    /**
     * user avatar
     */
    private String userAvatar;

    /**
     * user role: user, admin
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}