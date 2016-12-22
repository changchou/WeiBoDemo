package com.zhang.weibodemo.entity;

/**
 * Created by Mr.Z on 2016/11/1 0001.
 */

public class UserItem {

    private boolean isShowTopDivider;
    private int leftImg;
    private String subhead;
    private String caption;

    public UserItem(boolean isShowTopDivider, int leftImg, String subhead, String caption) {
        this.isShowTopDivider = isShowTopDivider;
        this.leftImg = leftImg;
        this.subhead = subhead;
        this.caption = caption;
    }

    public boolean isShowTopDivider() {
        return isShowTopDivider;
    }

    public void setShowTopDivider(boolean showTopDivider) {
        isShowTopDivider = showTopDivider;
    }

    public int getLeftImg() {
        return leftImg;
    }

    public void setLeftImg(int leftImg) {
        this.leftImg = leftImg;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
