package com.refactor.practice;

import com.refactor.practice.charge.MovieType;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {
	private Customer customer;
	private String baseline;

	@Before
	public void setUp() throws IOException {
		customer = new Customer("user1", new Vector());
		File file = new File("src/test/java/com/refactor/practice/baseline");
		baseline = FileUtils.readFileToString(file);
	}

	/**
	 *  重构说明：
	 *  1.抽离出Movie类型中表示电影类型的常量为MovieType枚举类型
	 *  2.MovieType枚举类型中包含影片类型编号和影片类型的计费规则
	 *  3.抽离出Customer的statement方法中的租金和积分计算规则
	 *  	创建了ChargeRules接口包含影片租金计算规则和积分计算规则
	 *  	不同的影片类型计费规则需要创建相应的类去实现ChargeRules接口：
	 *  			普通影片 RegularChargeRule
	 *  	 		儿童片 ChildrenChargeRule
	 *  	 		新片  NewMovieChargeRule
	 *  4.不同影片进行分类可自动获取当前分类的租金和积分计费规则。
	 *    	用户希望修改影片分类规则，租金计算也会自动选择相应的规则
	 *    	如果需要添加新的分类，只需要创建新类型实现ChargeRules接口定义自己的计费规则
	 *      并在MovieType中添加新的类型。
	 */
	@Test
	public void should_get_statement_of_rentals() {
		//given
		addRental(customer, "regular movie", MovieType.REGULAR, 3);
		addRental(customer, "new movie", MovieType.NEW_RELEASE, 2);
		addRental(customer, "children movie", MovieType.CHILDRENS, 5);
		//when
		String result = customer.statement();
		// 打印HTML详情单
		System.out.println(customer.statmentHtml());
		//then
		assertThat(result).isEqualTo(baseline);
	}

	private void addRental(Customer customer, String movieTitle, MovieType movieType, int dayRented) {
		Movie movie = new Movie(movieTitle, movieType.getCode());
		Rental rental = new Rental(movie, dayRented);
		customer.addRental(rental);
	}
}