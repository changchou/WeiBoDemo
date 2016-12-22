package com.zhang.weibodemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhang.weibodemo.BaseFragment;
import com.zhang.weibodemo.R;
import com.zhang.weibodemo.adapter.StatusAdapter;
import com.zhang.weibodemo.api.SimpleRequestListener;
import com.zhang.weibodemo.entity.Status;
import com.zhang.weibodemo.entity.response.StatusTimeLineResponse;
import com.zhang.weibodemo.utils.TitleBuilder;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private PullToRefreshListView plv_home;
    private View view;
    private View footView;

    private StatusAdapter adapter;
    private ArrayList<Status> statuses = new ArrayList<>();
    private int curPage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.fragment_home, null);

        new TitleBuilder(view).setTitleText("首页");

        plv_home = (PullToRefreshListView) view.findViewById(R.id.lv_home);
        adapter = new StatusAdapter(activity, statuses);
        plv_home.setAdapter(adapter);
        plv_home.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadData(1);
            }
        });
        plv_home.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                loadData(curPage + 1);
            }
        });

        footView = View.inflate(activity, R.layout.footview_loading, null);

        loadData(1);

        return view;
    }

    private void loadData(final int page) {

            api.statusesHome_timeline(page,
                    new SimpleRequestListener(activity, null) {
                        @Override
                        public void onComplete(String s) {
                            super.onComplete(s);

                            if (page == 1) {
                                statuses.clear();
                            }

                            curPage = page;

                            addData(new Gson().fromJson(s, StatusTimeLineResponse.class));
                        }
                        @Override
                        public void onAllDone() {
                            super.onAllDone();
                            plv_home.onRefreshComplete();
                        }
                    });


    }

    private void addData(StatusTimeLineResponse resBean) {
        for (Status status : resBean.getStatuses()) {
            if (!statuses.contains(status)) {
                statuses.add(status);
            }
        }
        adapter.notifyDataSetChanged();

        if (curPage < resBean.getTotal_number()) {
            addFootView(plv_home, footView);
        } else {
            removeFootView(plv_home, footView);
        }
    }

    private void addFootView(PullToRefreshListView plv, View footView) {
        ListView lv = plv.getRefreshableView();
        if (lv.getFooterViewsCount() == 1) {
            lv.addFooterView(footView);
        }
    }

    private void removeFootView(PullToRefreshListView plv, View footView) {
        ListView lv = plv.getRefreshableView();
        if (lv.getFooterViewsCount() > 1) {
            lv.removeFooterView(footView);
        }
    }

}
