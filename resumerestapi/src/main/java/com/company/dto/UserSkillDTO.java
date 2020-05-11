package com.company.dto;

import com.company.entity.UserSkill;

public class UserSkillDTO {

    private Integer id;
    private int power;
    private SkillDTO skillDTO;

    public UserSkillDTO() {
    }

    public UserSkillDTO(UserSkill userSkill) {
        this.id = userSkill.getId();
        this.power = userSkill.getPower();
        this.skillDTO = new SkillDTO(userSkill.getSkillId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public SkillDTO getSkillDTO() {
        return skillDTO;
    }

    public void setSkillDTO(SkillDTO skillDTO) {
        this.skillDTO = skillDTO;
    }
}
