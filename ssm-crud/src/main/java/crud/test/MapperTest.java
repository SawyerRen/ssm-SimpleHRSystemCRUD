package crud.test;

import crud.bean.Department;
import crud.bean.Employee;
import crud.dao.DepartmentMapper;
import crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层的工作
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    //测试部门
    @Test
    public void testCRUD() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        DepartmentMapper departmentMapper = context.getBean(DepartmentMapper.class);
        System.out.println(departmentMapper);
//        插入
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));

        //员工插入
//        employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "Jerry@qq.com", 1));
        //批量插入多个员工
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null, uid, "M", uid + "@qq,com", 1));
        }
        System.out.println("批量执行完成");
    }
}
