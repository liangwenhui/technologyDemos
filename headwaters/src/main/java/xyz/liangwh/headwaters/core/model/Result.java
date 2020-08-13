package xyz.liangwh.headwaters.core.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class Result implements Serializable {

    private int state;
    private Long id;

    public Result(int state, Long id) {
        this.state = state;
        this.id = id;
    }
}
