package jsjzx.wlyw.networkmaintenance.entities;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CiJian
 * @since 2018-08-30
 */
public class Brandmodel extends Model<Brandmodel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String model;
    private Integer typebrandid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getTypebrandid() {
        return typebrandid;
    }

    public void setTypebrandid(Integer typebrandid) {
        this.typebrandid = typebrandid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Brandmodel{" +
        ", id=" + id +
        ", model=" + model +
        ", typebrandid=" + typebrandid +
        "}";
    }
}
