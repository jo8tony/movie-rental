package com.refactor.practice.charge;

/**
 * 新片 租金和积分计算规则定义类
 */
public class NewMovieChargeRule implements ChargeRules {

    /**
     * 新片 计算租金
     * @param dayRented 租赁天数
     * @return 返回租金
     */
    @Override
    public double calcRent(int dayRented) {
        return dayRented * 3;
    }

    /**
     * 新片 计算积分
     * @param dayRented 租赁天数
     * @return 返回积分
     */
    @Override
    public int calcBilling(int dayRented) {
        if (dayRented > 1) {
            return 2;
        }
        return 1;
    }
}
