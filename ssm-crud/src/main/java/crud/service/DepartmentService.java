package crud.service;

import crud.bean.Department;
import crud.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }
}
