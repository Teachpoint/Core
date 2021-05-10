package com.teachpoint.Core.entity;

import com.teachpoint.Core.entity.converters.StudentSexAttributeConverter;
import com.teachpoint.Core.entity.converters.StudentStateAttributeConverter;
import com.teachpoint.Core.entity.enums.StudentSex;
import com.teachpoint.Core.entity.enums.StudentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.internals.SubscriptionState;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="students")
public class Student {

	public Student(Long id, StudentState state, String name, String surname, Date dateOfBirth, Integer gender, String phoneNumber, String email, Instant creationDate, Instant modifiedDate, Set<com.teachpoint.Core.entity.Grade> grades) {
		Id = id;
		this.state = state;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.creationDate = creationDate;
		this.modifiedDate = modifiedDate;
		this.grades = grades;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long Id;

	@Convert(converter = StudentStateAttributeConverter.class)
	private StudentState state;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private Integer gender;
	private String phoneNumber;
	private String email;
	private Instant creationDate;
	@Column(name = "modified_date")
	private Instant modifiedDate;


	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "classes_students",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "class_id"))
	private Set<Grade> grades;
}
