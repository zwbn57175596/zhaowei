package com.zhaowei.HelloNN;
/**
 * Copyright (C) 2010,Under the supervision of China Telecom Corporation
 * Limited Guangdong Research Institute
 * The New Vphone Project
 * @Author fonter.yang
 * @Create date：2010-10-11
 * 
 */
public class GridInfo {

    private String name;
    
    private int imgId;

    

//    public GridInfo(String name) {
//        super();
//        this.name = name;
//    }

    public GridInfo(String name, int imgId) {
        super();
        this.name = name;
        this.imgId = imgId;
    }
    
    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
