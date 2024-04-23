package org.mps.employee;

import java.util.List;

public class Employee {
    private Long id;
    private String name;
    private  Integer age;

    
    private List<String> duties;
      public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDuties() {
        return duties;
    }
    public void setDuties(List<String> duties) {
        this.duties = duties;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

  }
