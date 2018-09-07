package com.pms.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_politicalstatus")
public class Politicalstatus {
    /**
     * 政治面貌ID
     */
    @Id
    @Column(name = "PS_TYPE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String type;

    /**
     * 政治面貌名称
     */
    @Column(name = "PS_Name")
    private String name;

    /**
     * 更新时间
     */
    @Column(name = "Ext1")
    private String ext1;

    /**
     * 备注
     */
    @Column(name = "Ext2")
    private String ext2;

    /**
     * 暂未使用
     */
    @Column(name = "Ext3")
    private String ext3;

    /**
     * 获取政治面貌ID
     *
     * @return PS_TYPE - 政治面貌ID
     */
    public String getType() {
        return type;
    }

    /**
     * 设置政治面貌ID
     *
     * @param type 政治面貌ID
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * 获取政治面貌名称
     *
     * @return PS_Name - 政治面貌名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置政治面貌名称
     *
     * @param name 政治面貌名称
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 获取更新时间
     *
     * @return Ext1 - 更新时间
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置更新时间
     *
     * @param ext1 更新时间
     */
    public void setExt1(final String ext1) {
        this.ext1 = ext1;
    }

    /**
     * 获取备注
     *
     * @return Ext2 - 备注
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置备注
     *
     * @param ext2 备注
     */
    public void setExt2(final String ext2) {
        this.ext2 = ext2;
    }

    /**
     * 获取暂未使用
     *
     * @return Ext3 - 暂未使用
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置暂未使用
     *
     * @param ext3 暂未使用
     */
    public void setExt3(final String ext3) {
        this.ext3 = ext3;
    }

	/** Default constructor */
	public Politicalstatus() {
	}
}