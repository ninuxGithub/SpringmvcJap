package com.hundsun.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//import org.hibernate.validator.constraints.NotEmpty; 用于集合
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "姓名不能为空")
	@Size(max = 10, min = 1, message = "姓名字符长度1~10")
	private String name;

	@Max(value = 120)
	@Min(value = 0)
	@NumberFormat
	@NotNull(message = "年龄不能为空")
	private Integer age;

	@Past(message = "不可是过往的时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "生日不能为空")
	private Date birthday;

	public Student() {
	}

	public Student(Long id, String name, Integer age, Date birthday) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", birthday=" + birthday + "]";
	}

}
