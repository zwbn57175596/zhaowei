package com.zhaowei.HelloNN.DB.pojo;

public class Fruit implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1736118946676670264L;

    private Integer id;

    /** 水果名称 */
    private String name;

    private String desc;

    private String pinyin;

    private String pic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    /*
     * public Fruit(String name, String desc, String pinyin, String pic) {
     * super(); this.name = name; this.desc = desc; this.pinyin = pinyin;
     * this.pic = pic; }
     */
    
    /**
     * create a new fruit
     * @param name
     * @param desc
     * @param pinyin
     */
    public Fruit(String name, String desc, String pinyin) {
        super();
        this.name = name;
        this.desc = desc;
        this.pinyin = pinyin;
    }
    
    public Fruit(Integer id, String name, String desc, String pinyin) {
        super();
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.pinyin = pinyin;
    }

    public Fruit(Integer id, String name, String desc, String pinyin, String pic) {
        super();
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.pinyin = pinyin;
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
