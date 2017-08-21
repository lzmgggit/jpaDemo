package com.lits.eo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * -----------------------------------------
 * @文件: Company.java
 * @作者: fancy
 * @邮箱: fancyzero@yeah.net
 * @时间: 2012-6-10
 * @描述: 实体类
 * -----------------------------------------
 */
/**
 * 下面只说@ManyToOne,如需了解其他注解,
 * 可以参考上一篇：http://www.cnblogs.com/fancyzero/archive/2012/06/10/hibernate-one-to-one-annotation.html
 */
@Entity
@Table(name = "t_employee")
public class Employee {

    private Integer employeeId;
    private String  employeeName;
    private Company company;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getEmployeeId() {
        return employeeId;
    }
    /**
     * @ManyToOne：多对一,cascade：级联,请参考上一篇
     * fetch = FetchType.LAZY,延迟加载策略,如果不想延迟加载可以用FetchType.EAGER
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "cid")
    public Company getCompany() {
        return company;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
}