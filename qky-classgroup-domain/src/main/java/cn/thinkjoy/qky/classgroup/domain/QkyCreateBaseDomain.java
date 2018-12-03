package cn.thinkjoy.qky.classgroup.domain;

import cn.thinkjoy.common.domain.BaseDomain;

import java.util.Date;

/**
 * Created by tengen on 2016/1/26.
 */
public class QkyCreateBaseDomain<T> extends BaseDomain<T> {

    private Object creator;
    private Long createDate = System.currentTimeMillis();
    ;
    private Object lastModifier;
    private Long lastModDate = System.currentTimeMillis();
    private Integer status;

    public QkyCreateBaseDomain() {
    }

    public Object getCreator() {
        return this.creator;
    }

    public void setCreator(Object creator) {
        this.creator = creator;
    }

    public Long getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Object getLastModifier() {
        return this.lastModifier;
    }

    public void setLastModifier(Object lastModifier) {
        this.lastModifier = lastModifier;
    }

    public Long getLastModDate() {
        return this.lastModDate;
    }

    public void setLastModDate(Long lastModDate) {
        this.lastModDate = lastModDate;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDateAsDate() {
        return this.createDate == null ? new Date() : (this.createDate.longValue() > 0L ? new Date(this.createDate.longValue()) : null);
    }

    public Date getLastModDateAsDate() {
        return this.lastModDate == null ? new Date() : (this.lastModDate.longValue() > 0L ? new Date(this.lastModDate.longValue()) : null);
    }
}
