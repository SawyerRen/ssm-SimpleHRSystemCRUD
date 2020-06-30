package crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import crud.bean.Employee;
import crud.bean.Msg;
import crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(path = "/emp/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable("ids") String ids) {
        if (ids.contains("-")) {
            List<Integer> del_ids = new ArrayList<>();
            String[] ids_arr = ids.split("-");
            for (String s : ids_arr) {
                del_ids.add(Integer.parseInt(s));
            }
            employeeService.deleteBatch(del_ids);
        } else {
            employeeService.deleteEmp(Integer.parseInt(ids));
        }
        return Msg.success();
    }

    @RequestMapping(path = "/emp/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    public Msg saveEmp(Employee employee) {
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }

    @ResponseBody
    @RequestMapping("checkuser")
    public Msg checkUser(@RequestParam("empName") String empName) {
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regx)) {
            return Msg.fail();
        }
        boolean b = employeeService.checkUser(empName);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    @ResponseBody
    @RequestMapping(path = "/emp", method = RequestMethod.POST)
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            HashMap<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Msg.fail().add("errorFields,", map);
        } else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }

    }


    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Employee> emps = employeeService.getAll();
        PageInfo page = new PageInfo(emps, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 查询所有员工数据（分页显示）
     *
     * @return
     */
//    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        PageHelper.startPage(pn, 5);
        List<Employee> emps = employeeService.getAll();
        PageInfo page = new PageInfo(emps, 5);
        model.addAttribute("pageInfo", page);
        return "list";
    }
}
