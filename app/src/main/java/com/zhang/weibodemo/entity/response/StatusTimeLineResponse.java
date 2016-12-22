package com.zhang.weibodemo.entity.response;


import com.zhang.weibodemo.entity.Status;

import java.util.ArrayList;

/**
 * Created by Mr.Z on 2016/10/27 0027.
 */

public class StatusTimeLineResponse {

    private ArrayList<Status> statuses;
    private int total_number;

    public ArrayList<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<Status> statuses) {
        this.statuses = statuses;
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }
}
