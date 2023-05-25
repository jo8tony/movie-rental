package com.refactor.practice.charge;

/**
 * 电影类别
 */
public enum MovieType {

    /**
     * 普通片
     */
    REGULAR(0, new RegularChargeRule()),
    /**
     * 新片
     */
    NEW_RELEASE(1, new NewMovieChargeRule()),
    /**
     * 儿童片
     */
    CHILDRENS(2, new ChildrenChargeRule()),


    ;

    /**
     * 影片类型编号
     */
    private final int code;
    /**
     * 影片类型计费规则
     */
    private final ChargeRules chargeType;

    MovieType(int code, ChargeRules chargeType){
       this.code = code;
       this.chargeType = chargeType;
    }

    public int getCode() {
        return code;
    }

    public ChargeRules getChargeType() {
        return chargeType;
    }

    /**
     * 通过编号获取影片类别
     * @param code 影片类型编号
     * @return 影片类别
     */
    public static MovieType valueOf(int code) {
        for (MovieType value : MovieType.values()) {
            if (code == value.code) {
                return value;
            }
        }
        throw new RuntimeException("Failed to get movie type.");
    }
}
