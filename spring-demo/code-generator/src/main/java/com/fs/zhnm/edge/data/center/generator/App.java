/*
 * Copyright (c) 2021-2030 丰尚智慧农牧科技有限公司
 * 不能修改和删除上面的版权声明
 * 此代码属于丰尚智慧农牧科技有限公司编写，在未经允许的情况下不得传播复制
 */
package com.fs.zhnm.edge.data.center.generator;

/**
 * 程序入口
 *
 * @author Liam
 * @date 2021-08-11 09:49:45
 */
public class App {
    public static void main(String[] args) {
        CodeCreater creator = new CodeCreater();
        try {
            creator.initConnection();
            creator.createCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            creator.complet();
        }
    }
}
