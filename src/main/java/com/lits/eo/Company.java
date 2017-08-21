package com.lits.eo;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
 * 下面只说@OneToMany,如需了解其他注解,
 * 可以参考上一篇：http://www.cnblogs.com/fancyzero/archive/2012/06/10/hibernate-one-to-one-annotation.html
 */
@Entity
@Table(name = "t_company")
public class Company {

    private Integer companyId;
    private String  companyName;
    private Set<Employee> employees;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCompanyId() {
        return companyId;
    }
    /**
     * @OneToMany 与 OneToOne相似的也用mappedBy,参考了Employee
     * 可以参考上一篇
     */
    @OneToMany(mappedBy = "company")
    public Set<Employee> getEmployees() {
        return employees;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}