package xyz.liangwh.coredemo.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("isAa")
    private boolean aa;

    public boolean isAa() {
        return aa;
    }

    public void setAaa(boolean aa) {
        this.aa = aa;
    }
}
