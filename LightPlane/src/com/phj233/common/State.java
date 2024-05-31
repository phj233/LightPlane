package com.phj233.common;

/**
 * @author phj233
 * @since 2024/5/31 16:51
 */
public enum State{
    RUNNING(1),
    PAUSE(2);
    private final Integer state;

    State(Integer state) {
        this.state = state;
    }


    public Integer getState() {
        return state;
    }

}
