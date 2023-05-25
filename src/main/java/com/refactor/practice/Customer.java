package com.refactor.practice;

import com.refactor.practice.charge.ChargeRules;
import com.refactor.practice.charge.MovieType;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
	private String _name;
	private Vector _rentals;

	public Customer(String _name, Vector _rentals) {
		this._name = _name;
		this._rentals = _rentals;
	}

	public String getName() {
		return _name;
	}

	public void addRental(Rental arg) {
		_rentals.add(arg);
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			double thisAmount = 0D;
			Rental each = (Rental) rentals.nextElement();

			// 根据不同的影片类型获取计费规则，并计算当前影片的租金和积分
			Movie movie = each.getMovie();
			MovieType movieType = MovieType.valueOf(movie.getPriceCode());
			ChargeRules chargeType = movieType.getChargeType();
			thisAmount = chargeType.calcRent(each.getDayRented());
			frequentRenterPoints += chargeType.calcBilling(each.getDayRented());

			//show figures for this rental
			result += "\t" + movie.getTitle() +
					"\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		//add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned" + frequentRenterPoints +
				" frequent renter points";
		return result;
	}

	/**
	 * 打印HTML
	 * @return 返回HTML字符串
	 */
	public String statmentHtml() {
		String html = "<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head>\n" +
				"\t<title>发票单</title>\n" +
				"\t<style>\n" +
				"\t\tbody {\n" +
				"\t\t\tfont-family: Arial, sans-serif;\n" +
				"\t\t\tbackground-color: #f5f5f5;\n" +
				"\t\t}\n" +
				"\t\th1 {\n" +
				"\t\t\ttext-align: center;\n" +
				"\t\t\tcolor: #333;\n" +
				"\t\t}\n" +
				"\t\ttable {\n" +
				"\t\t\tmargin: 0 auto;\n" +
				"\t\t\tborder-collapse: collapse;\n" +
				"\t\t\twidth: 80%;\n" +
				"\t\t\tmax-width: 800px;\n" +
				"\t\t\tbackground-color: #fff;\n" +
				"\t\t\tbox-shadow: 0 0 20px rgba(0, 0, 0, 0.1);\n" +
				"\t\t}\n" +
				"\t\tth, td {\n" +
				"\t\t\tpadding: 10px;\n" +
				"\t\t\ttext-align: left;\n" +
				"\t\t\tborder-bottom: 1px solid #ddd;\n" +
				"\t\t}\n" +
				"\t\tth {\n" +
				"\t\t\tbackground-color: #f2f2f2;\n" +
				"\t\t\tcolor: #666;\n" +
				"\t\t\tfont-weight: normal;\n" +
				"\t\t}\n" +
				"\t\ttfoot {\n" +
				"\t\t\tfont-weight: bold;\n" +
				"\t\t}\n" +
				"\t\tp {\n" +
				"\t\t\ttext-align: right;\n" +
				"\t\t\tfont-size: 1.2rem;\n" +
				"\t\t\tmargin-right: 10%;\n" +
				"\t\t}\n" +
				"\t</style>\n" +
				"</head>\n" +
				"<body>\n" +
				"<h1>"+getName()+"的租赁记录</h1>\n" +
				"<table>\n" +
				"\t\t<thead>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<th>电影名称</th>\n" +
				"\t\t\t\t<th>租金</th>\n" +
				"\t\t\t</tr>\n" +
				"\t\t</thead>\n" +
				"\t\t<tbody>\n";

		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = _rentals.elements();
		while (rentals.hasMoreElements()) {
			double thisAmount = 0D;
			Rental each = (Rental) rentals.nextElement();

			// 根据不同的影片类型获取计费规则，并计算当前影片的租金和积分
			Movie movie = each.getMovie();
			MovieType movieType = MovieType.valueOf(movie.getPriceCode());
			ChargeRules chargeType = movieType.getChargeType();
			thisAmount = chargeType.calcRent(each.getDayRented());
			frequentRenterPoints += chargeType.calcBilling(each.getDayRented());

			//show figures for this rental
			html += "<tr>\n" +
					"\t\t\t\t<td>"+movie.getTitle()+"</td>\n" +
					"\t\t\t\t<td>" + String.valueOf(thisAmount) + "</td>\n" +
					"\t\t\t</tr>\n";

			totalAmount += thisAmount;
		}

		html += "</tbody>\n" +
				"\t\t<tfoot>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td colspan=\"2\">消费总金额："+totalAmount+"元</td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td colspan=\"2\">本次消费的积分："+frequentRenterPoints+"分</td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t</tfoot>\n" +
				"\t</table>\n" +
				"</body>\n" +
				"</html>\n";

		return html;
	}
}
