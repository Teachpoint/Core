package com.teachpoint.Core.entity.converters;

import com.teachpoint.Core.entity.enums.StudentState;

import javax.persistence.AttributeConverter;

public class StudentStateAttributeConverter implements AttributeConverter<StudentState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StudentState studentState) {
        if (studentState == null)
            return null;

        switch (studentState) {
            case INITIAL:
                return 0;
            case ACTIVE:
                return 2;
            case SUSPENDED:
                return 9;
            default:
                throw new IllegalArgumentException(studentState + " Not Supported");

        }
    }

    @Override
    public StudentState convertToEntityAttribute(Integer integer) {
        if (integer == null)
            return null;

        switch (integer) {
            case 0:
                return StudentState.INITIAL;
            case 2:
                return StudentState.ACTIVE;
            case 9:
                return StudentState.SUSPENDED;
            default:
                throw new IllegalArgumentException(integer + " not supported");
        }

    }
}
