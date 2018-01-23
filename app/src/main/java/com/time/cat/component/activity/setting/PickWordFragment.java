package com.time.cat.component.activity.setting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.time.cat.R;
import com.time.cat.component.base.BaseActivity;
import com.time.cat.util.ConstantUtil;
import com.shang.commonjar.contentProvider.SPHelper;

/**
 * Created by penglu on 2016/12/10.
 */

public class PickWordFragment extends BaseRecyclerFragment {

    private MonitorSettingCard settingCard;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isChecked = intent.getBooleanExtra(ConstantUtil.SHOW_TENCENT_SETTINGS, true);
            if (isChecked) {
                int index = 0;
                if (!newAdapter.containsView(settingCard))
                    index = cardViews.size();
                newAdapter.addView(settingCard, index);

            } else {
                if (newAdapter.containsView(settingCard))
                    newAdapter.deleteView(settingCard);
            }
        }
    };

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            BaseActivity baseActivity = ((BaseActivity) getActivity());
            baseActivity.getSupportActionBar().setTitle(R.string.fragment_segment);
        }
    }

    @Override
    protected void prepareCardView() {
        settingCard = new MonitorSettingCard(getActivity());
        cardViews.add(new GoToSettingCard(getActivity()));
        cardViews.add(new FunctionSettingCard(getActivity()));
        if (SPHelper.getBoolean(ConstantUtil.MONITOR_CLICK, true)) {
            cardViews.add(settingCard);
        }
        initLocalBroadcast();
    }


    private void initLocalBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConstantUtil.Setting_content_Changes);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mReceiver, intentFilter);
    }


    @Override
    public void onDestroy() {
        try {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
