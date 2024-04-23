package org.mps.employee;

import org.assertj.core.api.AbstractAssert;

public class EmployeeAssert extends AbstractAssert<EmployeeAssert, Employee> {

    public EmployeeAssert(Employee actual) {
        super(actual, EmployeeAssert.class);
    }

    public static EmployeeAssert assertEmployee(Employee actual) {
        return new EmployeeAssert(actual);
    }

    public EmployeeAssert hasName(String name) {
        isNotNull();
        if (!actual.getName().equals(name)) {
            failWithMessage("Expected person to have full name %s but was %s", name, actual.getName());
        }
        return this;
    }
    
    public EmployeeAssert isAdult() {
        isNotNull();
        if (actual.getAge() < 18) {
            failWithMessage("Expected person to be adult");
        }
        return this;
    }
    
    public EmployeeAssert hasDuty(String duty) {
        isNotNull();
        if (!actual.getDuties().contains(duty)) {
            failWithMessage("Expected person to have duty %s", duty);
        }
        return this;
    }
}
