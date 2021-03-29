package com.teachpoint.Core.entity.enums;

public enum StudentState {
    INITIAL(0),
    ACTIVE(2),
    SUSPENDED(9);

private int state;

    StudentState(int s) {
        state = s;
    }

    @Override
    public String toString() {
        return "StudentState{" +
                "state=" + state +
                '}';
    }
}
