package com.common.project;

import com.common.project.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonApplicationTests {
@Autowired
private UserMapper userMapper;


	@Test
	public void contextLoads() {
		/*List<User> userList=userMapper.selectAll();
		userList.forEach(item->System.out.println(item.getName()));
		System.out.println(userList);*/
		File path = null;
		try {
			path = new File(ResourceUtils.getURL("classpath:").getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(!path.exists()) path = new File("");
		System.out.println("path:"+path.getAbsolutePath());
		File upload = new File(path.getAbsolutePath(),"static/img/a.jpg");
		System.out.println(upload.length());

	}

}
