package jsjzx.wlyw.networkmaintenance.entities;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CiJian
 * @since 2018-08-30
 */
public class Asset extends Model<Asset> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备ID

     */
    @TableId(value = "hostid", type = IdType.AUTO)
    private Integer hostid;
    /**
     * 公司名称
     */
    private String company;
    /**
     * 部门名称
     */
    private String department;
    /**
     * 设备名称
     */
    private String devicename;
    /**
     * 主机名称
     */
    @NotEmpty
    private String hostname;
    /**
     * 设备类型
     */
    private String type;
    /**
     * 设备品牌
     */
    private String brand;
    /**
     * 设备型号
     */
    private String model;
    /**
     * 序列号
     */
    private String sn;
    /**
     * 设备IP
     */
    private String ip;
    /**
     * 购买时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buytime;
    /**
     * 上架时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date shelftime;
    /**
     * 保修时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date guaranty;
    /**
     * 设备状态
     */
    private String status;
    /**
     * 设备等级
     */
    private String grade;
    /**
     * 设备位置
     */
    private String location;
    /**
     * 备注
     */
    private String note;


    public Integer getHostid() {
        return hostid;
    }

    public void setHostid(Integer hostid) {
        this.hostid = hostid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public Date getShelftime() {
        return shelftime;
    }

    public void setShelftime(Date shelftime) {
        this.shelftime = shelftime;
    }

    public Date getGuaranty() {
        return guaranty;
    }

    public void setGuaranty(Date guaranty) {
        this.guaranty = guaranty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    protected Serializable pkVal() {
        return this.hostid;
    }

    @Override
    public String toString() {
        return "Asset{" +
        ", hostid=" + hostid +
        ", company=" + company +
        ", department=" + department +
        ", devicename=" + devicename +
        ", hostname=" + hostname +
        ", type=" + type +
        ", brand=" + brand +
        ", model=" + model +
        ", sn=" + sn +
        ", ip=" + ip +
        ", buytime=" + buytime +
        ", shelftime=" + shelftime +
        ", guaranty=" + guaranty +
        ", status=" + status +
        ", grade=" + grade +
        ", location=" + location +
        ", note=" + note +
        "}";
    }
}
