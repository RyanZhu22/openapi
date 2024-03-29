package com.ryan.openapi.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * user update personal information request
 *
 */
@Data
public class UserUpdateMyRequest implements Serializable {

    /**
     * username
     */
    private String userName;

    /**
     * user avatar
     */
    private String userAvatar;

    /**
     * user profile
     */
    private String userProfile;

    private static final long serialVersionUID = 1L;
}