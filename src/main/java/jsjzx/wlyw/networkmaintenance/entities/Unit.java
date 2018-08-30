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
public class Unit extends Model<Unit> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @NotEmpty
    private String companyname;
    private String companynameshort;
    private String departmentname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanynameshort() {
        return companynameshort;
    }

    public void setCompanynameshort(String companynameshort) {
        this.companynameshort = companynameshort;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Unit{" +
        ", id=" + id +
        ", companyname=" + companyname +
        ", companynameshort=" + companynameshort +
        ", departmentname=" + departmentname +
        "}";
    }
}
