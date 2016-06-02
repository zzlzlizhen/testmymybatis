package lz.model;

public class ExcepLog {
    private String id;

    private String name;

    private String exceptionDesc;

    private String exceptionBusiness;

    private String exceptionInfo;

    private String createTime;

    private String exceptionTarget;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc == null ? null : exceptionDesc.trim();
    }

    public String getExceptionBusiness() {
        return exceptionBusiness;
    }

    public void setExceptionBusiness(String exceptionBusiness) {
        this.exceptionBusiness = exceptionBusiness == null ? null : exceptionBusiness.trim();
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo == null ? null : exceptionInfo.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getExceptionTarget() {
        return exceptionTarget;
    }

    public void setExceptionTarget(String exceptionTarget) {
        this.exceptionTarget = exceptionTarget == null ? null : exceptionTarget.trim();
    }
}