package com.ryan.openapi.model.dto.userinterfaceinfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ryan.openapi.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * query request
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInterfaceInfoQueryRequest extends PageRequest implements Serializable {

    /**
     * primary key
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * total of invoke
     */
    private Integer totalNum;

    /**
     * left number of invoke
     */
    private Integer leftNum;

    /**
     * 0-normal，1-ban
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}