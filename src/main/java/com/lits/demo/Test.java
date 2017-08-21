package com.lits.demo;

import java.util.Iterator;
import java.util.Set;

import com.lits.eo.Company;
import com.lits.eo.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * -----------------------------------------
 * @文件: Test.java
 * @作者: fancy
 * @邮箱: fancyzero@yeah.net
 * @时间: 2012-6-10
 * @描述: 测试类
 * -----------------------------------------
 */
public class Test {

    public static void main(String[] args) {
        //读取hibernate配置,默认读取classpath下的hibernate.cfg.xml
        Configuration conf = new AnnotationConfiguration();
//4.x后说是使用如下代码更时髦，然并卵
//        Configuration cfg = new Configuration().configure();
//        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
//        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
//        Session s = factory.openSession();
        //构建session工厂
        SessionFactory sessionFactory = conf.configure().buildSessionFactory();
        //打开session
        Session session = sessionFactory.openSession();
        //开始事务
        session.beginTransaction();
        // * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        //test1(session);   //测试 1
        //test2(session);  //测试  2
        test3(session);   //测试   3
        // * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        //提交事务
        session.getTransaction().commit();
        //关闭session工厂
        sessionFactory.close();
        //关闭session
        session.close();
    }
    public static void test1(Session session){
        Company company = (Company)session.get(Company.class, 1); //发出Company的select语句
        Set<Employee> employee = company.getEmployees();         //不发出Employee的select语句
        System.out.println("Company ：" + company.getCompanyName());
        System.out.println("CountSum：" + employee.size()); //Employee初次被使用,发出select语句
        Iterator<Employee> it = employee.iterator(); //Employee不再发出select语句
        while(it.hasNext()){
            System.out.println("EmployeeName：" + it.next().getEmployeeName());
        }
    }
    public static void test2(Session session){
        Company company = (Company)session.get(Company.class, 2);//发出Company的select语句
        Set<Employee> employee = company.getEmployees();        //不发出Employee的select语句
        Iterator<Employee> it = employee.iterator(); //发出Employee的select语句
        Employee e = null;
        Boolean first = false;
        while(it.hasNext()){
            e = it.next();
            if(!first){
                System.out.println("EmployeeId：[" + e.getEmployeeId() + "] information will be change");
                e.setEmployeeName("fancy"); //更改雇员名字
                //  session.save(e);  //发出Employee的update语句,不发出Company的update语句
                session.save(company);    //发出Employee的update语句,不发出Company的update语句
                first = true;
            }
            System.out.println("EmployeeName：" + e.getEmployeeName());
        }
    }
    public static void test3(Session session){
        Employee employee = (Employee)session.get(Employee.class, 1);//发出Employee的select语句
        Company  company  = (Company)session.get(Company.class, 1);//发出Company的select语句
        company.setCompanyName("Oracle"); //更改公司名字
        //  session.save(company);//发出Company的update语句,不发出Employee的update语句
        session.save(employee);//发出Company的update语句,不发出Employee的update语句
    }
}