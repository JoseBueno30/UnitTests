package org.mps.employee;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mps.employee.EmployeeAssert.assertEmployee;

public class EmployeeTest {

    @Test
    public void testEmployee(){
        Employee employeeToTest = new Employee();
        employeeToTest.setName("Linus Torval");
        employeeToTest.setAge(35);
        ArrayList<String> duties = new ArrayList<>(Arrays.asList("Testing", "Development"));
        employeeToTest.setDuties(duties);

        assertEmployee(employeeToTest)
                .hasName("Linus Torval")
                .isAdult()
                .hasDuty("Testing");
    }
    
}
