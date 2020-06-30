package crud.test;

import com.github.pagehelper.PageInfo;
import crud.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 测试crud请求
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:WEB-INF/dispatcherServlet.xml"})
public class MvcTest {
    //SpringMVC的ioc容器
    @Autowired
    WebApplicationContext context;
    //虚拟mvc请求
    MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //测试分页
    @Test
    public void testPage() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1"))
                .andReturn();
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println(pageInfo.getPageNum());
        System.out.println(pageInfo.getPages());
        System.out.println(pageInfo.getTotal());

        List<Employee> list = pageInfo.getList();
        for (Employee employee : list) {
            System.out.println(employee.getEmpId() + ":" + employee.getEmpName());
        }
    }
}
