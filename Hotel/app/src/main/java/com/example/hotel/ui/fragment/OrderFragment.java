package com.example.hotel.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hotel.R;
import com.example.hotel.enums.OrderStatusEnum;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;


/**
 * 订单
 */
public class OrderFragment extends Fragment {
    private Activity myActivity;
    private TabLayout tlTitle;//状态标题
    private List<String> titleList = null;//状态标题
    private List<Integer> stateList = null;//状态


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        //获取控件
        tlTitle = view.findViewById(R.id.tl_title);
        ViewPager2 vpOrderContainer = view.findViewById(R.id.vp_container);
        titleList = OrderStatusEnum.getNameList();
        stateList = OrderStatusEnum.getCodeList();
        //=实例化适配器
        MyFragmentStateAdapter myFragmentStateAdapter = new MyFragmentStateAdapter(getActivity());
        vpOrderContainer.setAdapter(myFragmentStateAdapter);
        vpOrderContainer.setOffscreenPageLimit(1);
        //TabLayout与ViewPager2关联
        new TabLayoutMediator(tlTitle, vpOrderContainer, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //设置TabLayout的显示
                tab.setText(titleList.get(position));
            }
        }).attach(); // 不要忘记，否则没效果
        return view;
    }

    class MyFragmentStateAdapter extends FragmentStateAdapter {
        //存放Fragment
        Fragment[] fragments;

        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            fragments = new Fragment[titleList.size()];
        }

        //创建Fragment
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (fragments[position] == null) {
                //创建传递参数的Bundle
                Bundle bundle = new Bundle();
                bundle.putInt("stateId", stateList.get(position));
                //创建Fragment
                OrderStateFragment orderStateFragment = new OrderStateFragment();
                //设置参数
                orderStateFragment.setArguments(bundle);
                fragments[position] = orderStateFragment;

            }
            return fragments[position];
        }

        //获取Fragment的数量
        @Override
        public int getItemCount() {
            return fragments.length;
        }
    }

}
