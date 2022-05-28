package com.example.hotel.ui.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.R;
import com.example.hotel.adapter.RoomAdapter;
import com.example.hotel.bean.Room;
import com.example.hotel.enums.UserTypeEnum;
import com.example.hotel.ui.activity.AddActivity;
import com.example.hotel.ui.activity.RoomInfoActivity;
import com.example.hotel.util.MySqliteOpenHelper;
import com.example.hotel.util.RecyclerViewSpaces;
import com.example.hotel.util.SPUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *首页
 */
public class HomeFragment extends Fragment {
    MySqliteOpenHelper helper = null;
    private Activity myActivity;
    private LinearLayout llEmpty;
    private Spinner sp_type;
    private EditText etQuery;//搜索内容
    private ImageView ivSearch;//搜索图标
    private List<Room> list;
    private RoomAdapter roomAdapter;
    private RecyclerView rvList;
    private SmartRefreshLayout srlList;//下拉加载，上拉刷新控件
    private Integer userType;
    private FloatingActionButton btnAdd;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        helper = new MySqliteOpenHelper(myActivity);
        llEmpty = view.findViewById(R.id.ll_empty);
        sp_type = view.findViewById(R.id.sp_type);
        rvList = view.findViewById(R.id.rv_list);
        llEmpty = view.findViewById(R.id.ll_empty);
        srlList = view.findViewById(R.id.srl_list);
        etQuery = view.findViewById(R.id.et_query);
        ivSearch = view.findViewById(R.id.iv_search);
        btnAdd = view.findViewById(R.id.btn_add);
        //用户类型
        userType = (Integer) SPUtils.get(myActivity,SPUtils.USER_TYPE,0);
        btnAdd.setVisibility(userType.intValue() == UserTypeEnum.User.getCode()?View.GONE:View.VISIBLE);//普通用户隐藏
        //设置布局
        GridLayoutManager layoutManager = new GridLayoutManager(myActivity, 2);//两列
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        roomAdapter = new RoomAdapter();
        rvList.setAdapter(roomAdapter);
        HashMap<String, Integer> mapSpaces = new HashMap<>();//间距
        mapSpaces.put(RecyclerViewSpaces.TOP_DECORATION, 20);//上间距
        mapSpaces.put(RecyclerViewSpaces.BOTTOM_DECORATION, 20);//下间距
        mapSpaces.put(RecyclerViewSpaces.LEFT_DECORATION, 20);//左间距
        mapSpaces.put(RecyclerViewSpaces.RIGHT_DECORATION, 20);//右间距
        rvList.addItemDecoration(new RecyclerViewSpaces(mapSpaces));//设置间距
        roomAdapter.setItemListener(new RoomAdapter.ItemListener() {
            @Override
            public void ItemClick(Room room) {
                Intent intent;
                if (userType.intValue() == UserTypeEnum.User.getCode()) {//普通用户
                    intent = new Intent(myActivity, RoomInfoActivity.class);
                }else {//管理员
                    intent = new Intent(myActivity, AddActivity.class);
                }

                intent.putExtra("room", room);
                startActivityForResult(intent,100);
            }

            @Override
            public void ItemLongClick(int id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(myActivity);
                dialog.setMessage("确认要删除该房间吗");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = helper.getWritableDatabase();
                        db.execSQL("delete from room where id=?", new Object[]{ id});
                        db.execSQL("delete from orders where roomId=?", new Object[]{ id});
                        db.close();
                        loadData();//加载数据
                    }
                });
                dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        srlList.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData();
            }
        });
        srlList.autoRefresh();//自动加载
        //软键盘搜索
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();//加载数据
            }
        });
        //点击软键盘中的搜索
        etQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    loadData();//加载数据
                    return true;
                }
                return false;
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myActivity, AddActivity.class);
                startActivityForResult(intent,100);
            }
        });
        return view;
    }

    /**
     * 加载数据
     */
    private void loadData() {
        list = new ArrayList<>();
        int typeId = sp_type.getSelectedItemPosition();
        String content = etQuery.getText().toString();//获取搜索内容
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select * from room where title like ?";
        if (typeId > 0) {
            sql += " and typeId =" +typeId;
        }
        Cursor cursor = db.rawQuery(sql, new String[]{"%"+content+"%"});
        if (cursor != null && cursor.getColumnCount() > 0) {
            while (cursor.moveToNext()) {
                Integer id = cursor.getInt(0);//
                String title = cursor.getString(1);//标题
                String image = cursor.getString(2);//图片
                String size = cursor.getString(3);//房间大小
                String bed = cursor.getString(4);//床
                Integer quantity = cursor.getInt(5);//总数量
                Integer surplus = cursor.getInt(6);//剩余数量
                Double price = cursor.getDouble(7);//价格
                String remark = cursor.getString(8);//备注
                Integer dbTypeId = cursor.getInt(9);//
                Room room = new Room(id,title,image,size,bed,quantity,surplus,price,remark,dbTypeId);
                list.add(room);
            }
        }
        srlList.finishRefresh();//刷新完成
        db.close();
        if (list.size() > 0) {
            roomAdapter.addItem(list);
            llEmpty.setVisibility(View.GONE);
            rvList.setVisibility(View.VISIBLE);
        }else {
            llEmpty.setVisibility(View.VISIBLE);
            rvList.setVisibility(View.GONE);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK){
            loadData();//加载数据
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){//显示加载
            loadData();
        }
    }
}
