package com.atguigu.CountDownLatchTest;
import lombok.Getter;

public enum CountEmnu {
    ONE(1,"齐"),
    TWO(2,"魏"),
    THREE(3,"赵"),
    FOUR(4,"楚"),
    FIVE(5,"燕"),
    SIX(6,"韩");

    @Getter private Integer retCode;
    @Getter private String retMessage;

    CountEmnu(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountEmnu foreach_CountEmnu(int index){
        CountEmnu[] values = CountEmnu.values();
        for (CountEmnu element:values) {
            if(index == element.getRetCode()){
                return element;
            }
        }
        return null;
    }
}
