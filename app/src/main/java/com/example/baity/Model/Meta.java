package com.example.baity.Model;

public class Meta {
    private Method method;

    private Offset offset;

    private String school;

    private String timezone;

    private String midnightMode;

    private String latitude;

    private String longitude;

    private String latitudeAdjustmentMethod;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getMidnightMode() {
        return midnightMode;
    }

    public void setMidnightMode(String midnightMode) {
        this.midnightMode = midnightMode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitudeAdjustmentMethod() {
        return latitudeAdjustmentMethod;
    }

    public void setLatitudeAdjustmentMethod(String latitudeAdjustmentMethod) {
        this.latitudeAdjustmentMethod = latitudeAdjustmentMethod;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "method=" + method +
                ", offset=" + offset +
                ", school='" + school + '\'' +
                ", timezone='" + timezone + '\'' +
                ", midnightMode='" + midnightMode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitudeAdjustmentMethod='" + latitudeAdjustmentMethod + '\'' +
                '}';
    }
}
