package com.refactor.practice.charge;

/**
 * 普通影片 租金和积分计算规则定义类
 */
public class RegularChargeRule implements ChargeRules {

    /**
     * 普通影片计算租金
     * @param dayRented 租赁天数
     * @return 返回租金
     */
    @Override
    public double calcRent(int dayRented) {
        double thisAmount = 2D;
        if (dayRented > 2) {
            thisAmount += (dayRented - 2) * 1.5;
        }
        return thisAmount;
    }

    /**
     * 普通影片计算积分
     * @param dayRented 租赁天数
     * @return 返回积分
     */
    @Override
    public int calcBilling(int dayRented) {
        return 1;
    }
}
