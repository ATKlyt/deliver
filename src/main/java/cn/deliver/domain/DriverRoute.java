package cn.deliver.domain;

import java.sql.Timestamp;

public class DriverRoute {
    /**
     * 司机行程在数据库中的唯一标识
     */
    private Integer id;

    /**
     * 司机行程所属司机id
     */
    private Integer uid;

    /**
     * 司机行程出发地id
     */
    private Integer originalAreaId;

    /**
     * 司机行程目的地id
     */
    private Integer destinationAreaId;

    /**
     * 载重量
     */
    private Integer capacity;

    /**
     * 出发时间
     */
    private Timestamp departTime;

    /**
     * 行程状态
     * 司机行程可被用户查找到
     * 司机取消行程
     * 司机行程过期
     * 删除司机行程
     */
    private String status;

    private Timestamp createTime;

    private Timestamp updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getOriginalAreaId() {
        return originalAreaId;
    }

    public void setOriginalAreaId(Integer originalAreaId) {
        this.originalAreaId = originalAreaId;
    }

    public Integer getDestinationAreaId() {
        return destinationAreaId;
    }

    public void setDestinationAreaId(Integer destinationAreaId) {
        this.destinationAreaId = destinationAreaId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Timestamp getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Timestamp departTime) {
        this.departTime = departTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
