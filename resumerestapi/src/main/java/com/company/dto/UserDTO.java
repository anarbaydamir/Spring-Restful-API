package com.company.dto;

import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private Integer id;
    private String name;
    private String surname;
    private String password;
    private List<UserSkillDTO> userSkillDTOList;

    public UserDTO() {
    }

    public UserDTO(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.surname = u.getSurname();

        List<UserSkill> userSkillList = u.getUserSkillList();
        List<UserSkillDTO> list = new ArrayList<>();

        for (int i=0;i<userSkillList.size();i++){
            UserSkill userSkill = userSkillList.get(i);
            list.add(new UserSkillDTO(userSkill));
        }
        this.userSkillDTOList = list;
    }

    public UserDTO(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<UserSkillDTO> getUserSkillDTOList() {
        return userSkillDTOList;
    }

    public void setUserSkillDTOList(List<UserSkillDTO> userSkillDTOList) {
        this.userSkillDTOList = userSkillDTOList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
