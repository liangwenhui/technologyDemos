package xyz.liangwh.coredemo.controllers;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.propertyeditors.PropertiesEditor;

public class UserEditer extends PropertiesEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(JSONObject.parseObject(text, User.class));
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }

}
