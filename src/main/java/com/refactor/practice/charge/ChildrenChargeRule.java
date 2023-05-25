package com.refactor.practice.charge;

/**
 * 儿童片 租金和积分计算规则定义类
 */
public class ChildrenChargeRule implements ChargeRules {

    /**
     * 儿童片计算租金
     * @param dayRented 租赁天数
     * @return 返回租金
     */
    @Override
    public double calcRent(int dayRented) {
        double thisAmount = 1.5D;
        if (dayRented > 3) {
            thisAmount += (dayRented - 3) * 1.5;
        }
        return thisAmount;
    }

    /**
     * 儿童片计算积分
     * @param dayRented 租赁天数
     * @return 返回积分
     */
    @Override
    public int calcBilling(int dayRented) {
        return 1;
    }
}
