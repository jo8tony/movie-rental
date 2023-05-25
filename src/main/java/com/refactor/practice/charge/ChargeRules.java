package com.refactor.practice.charge;

/**
 * 计费类型
 */
public interface ChargeRules {

    /**
     * 计算租金
     * @param dayRented 租赁天数
     * @return 返回租金
     */
    double calcRent(int dayRented);

    /**
     * 计算积分
     * @param dayRented 租赁天数
     * @return 返回积分
     */
    int calcBilling(int dayRented);
}
