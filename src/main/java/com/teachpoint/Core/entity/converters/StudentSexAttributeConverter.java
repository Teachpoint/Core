package com.teachpoint.Core.entity.converters;

import com.teachpoint.Core.entity.enums.StudentSex;

import javax.persistence.AttributeConverter;

public class StudentSexAttributeConverter implements AttributeConverter<StudentSex, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StudentSex studentSex) {
        if (studentSex == null)
            return null;

        switch (studentSex) {
            case Male:
                return 1;
            case Female:
                return 0;
            default:
                throw new IllegalArgumentException(studentSex + " not supported");
        }


    }

    @Override
    public StudentSex convertToEntityAttribute(Integer integer) {
        if (integer == null)
            return null;

        switch (integer) {
            case 0:
                return StudentSex.Female;
            case 1:
                return StudentSex.Male;
            default:
                throw new IllegalArgumentException(integer + " not supported");
        }

    }
}
