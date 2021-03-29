package com.teachpoint.Core.entity.enums;

public enum StudentSex {
    Female(0),
    Male(1);

    private int sex;

    StudentSex(int i) {
        sex = i;
    }

    @Override
    public String toString() {
        return "StudentSex{" +
                "sex=" + sex +
                '}';
    }
}

