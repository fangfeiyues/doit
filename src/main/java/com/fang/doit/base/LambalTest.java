package com.fang.doit.base;

/**
 * @author fangfeiyue
 * @Date 2021/5/7 10:58 上午
 */
public class LambalTest {

    private Integer stockMode;

    public Integer getStockMode() {
        return stockMode;
    }

    public void setStockMode(Integer stockMode) {
        this.stockMode = stockMode;
    }

    public static void main(String[] args) {
        LambalTest lambalTest = new LambalTest();
        int stockModel = lambalTest.getStockMode() == null ? 1 : lambalTest.getStockMode();
        System.out.println(stockModel);
    }
}
