package com.scholarship.demo.model;

public class Process {

    private int id;
    private String year;
    private String instanceStatus;
    private String fStartTime;
    private String fEndTime;
    private String sStartTime;
    private String sEndTime;
    private String tStartTime;
    private String tEndTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(String instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public String getfStartTime() {
        return fStartTime;
    }

    public void setfStartTime(String fStartTime) {
        this.fStartTime = fStartTime;
    }

    public String getfEndTime() {
        return fEndTime;
    }

    public void setfEndTime(String fEndTime) {
        this.fEndTime = fEndTime;
    }

    public String getsStartTime() {
        return sStartTime;
    }

    public void setsStartTime(String sStartTime) {
        this.sStartTime = sStartTime;
    }

    public String getsEndTime() {
        return sEndTime;
    }

    public void setsEndTime(String sEndTime) {
        this.sEndTime = sEndTime;
    }

    public String gettStartTime() {
        return tStartTime;
    }

    public void settStartTime(String tStartTime) {
        this.tStartTime = tStartTime;
    }

    public String gettEndTime() {
        return tEndTime;
    }

    public void settEndTime(String tEndTime) {
        this.tEndTime = tEndTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", instanceStatus='" + instanceStatus + '\'' +
                ", fStartTime='" + fStartTime + '\'' +
                ", fEndTime='" + fEndTime + '\'' +
                ", sStartTime='" + sStartTime + '\'' +
                ", sEndTime='" + sEndTime + '\'' +
                ", tStartTime='" + tStartTime + '\'' +
                ", tEndTime='" + tEndTime + '\'' +
                '}';
    }
}
