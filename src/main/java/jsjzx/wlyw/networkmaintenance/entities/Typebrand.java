package jsjzx.wlyw.networkmaintenance.entities;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CiJian
 * @since 2018-08-30
 */
public class Typebrand extends Model<Typebrand> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @NotEmpty
    private String type;
    private String brandcn;
    private String branden;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrandcn() {
        return brandcn;
    }

    public void setBrandcn(String brandcn) {
        this.brandcn = brandcn;
    }

    public String getBranden() {
        return branden;
    }

    public void setBranden(String branden) {
        this.branden = branden;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Typebrand{" +
        ", id=" + id +
        ", type=" + type +
        ", brandcn=" + brandcn +
        ", branden=" + branden +
        "}";
    }
}
