package com.blog.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * Create label_file for db
 * </p>
 *
 * @author haijun.zhang
 * @since 2020-06-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("label_file")
public class LabelFile extends Model<LabelFile> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer labelId;

    private String fileHash;

    private Integer status;

    private Date createdAt;

    private Integer createdBy;

    private Date updatedAt;

    private Integer updatedBy;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
