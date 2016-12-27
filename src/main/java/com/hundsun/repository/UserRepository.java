package com.hundsun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hundsun.domain.UserEntity;


/**  
* @ClassName: 全类名称：com.hundsun.repository.UserRepository  
* @Description:功能作用： --
* @date 创建日期：2016年12月26日 下午4:03:43    
*/
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	// 定义查询
	// @Param注解用于提取参数
	@Modifying // 说明该方法是修改方法
	@Transactional // 说明该方法是事性操作
	@Query("update UserEntity us set us.nickname=:qNickname, us.firstName=:qFirstName, us.lastName=:qLastName, us.password=:qPassword where us.id=:qId")
	public void updateUser(@Param("qNickname") String nickname, @Param("qFirstName") String firstName,
			@Param("qLastName") String lastName, @Param("qPassword") String password, @Param("qId") Integer id);

	@Query("from UserEntity u ")
	public List<UserEntity> findAllByHql();

}
