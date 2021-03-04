package com.lj.springcloud.study.providerticket.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;

@Configuration
@ConfigurationProperties("test")
public class TestMyProperties {

    String name;
    String sex;
    String[] habit;
    HashMap<String,String> education;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String[] getHabit() {
        return habit;
    }

    public void setHabit(String[] habit) {
        this.habit = habit;
    }

    public HashMap<String, String> getEducation() {
        return education;
    }

    public void setEducation(HashMap<String, String> education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "TestProperties{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", habit=" + Arrays.toString(habit) +
                ", education=" + education +
                '}';
    }
}
