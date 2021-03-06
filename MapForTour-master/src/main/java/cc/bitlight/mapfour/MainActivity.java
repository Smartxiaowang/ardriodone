package cc.bitlight.mapfour;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cc.bitlight.mapfour.customclass.DeviceMessageApplication;
import cc.bitlight.mapfour.customclass.MyRadarNearbyInfo;
import cc.bitlight.mapfour.customclass.MyViewPager;
import cc.bitlight.mapfour.fragment.MapFragment;
import cc.bitlight.mapfour.fragment.NearbyFragment;
import cc.bitlight.mapfour.fragment.OptionMapFragment;
import cc.bitlight.mapfour.maplistener.FindTabAdapter;

public class MainActivity extends AppCompatActivity implements MapFragment.OnclickSearchNearbyListener, OptionMapFragment.OptionFragmentOnclickListener {
  DeviceMessageApplication application;
  //??????
  ActionBarDrawerToggle mActionBarDrawerToggle;
  DrawerLayout drawerLayout;
  //Toolbar??????
  Toolbar toolbar;
  String toolbarTitle = "??????";
  Menu menuToolbar;
  MenuItem menuToolbarItemTrackHistoryStatus;
  MenuItem menuToolbarItemGeoFenceStatus;
  MenuItem menuToolbarItemNotification;
  MenuItem menuToolbarItemAbout;
  //NavigationView??????
  View headerView;
  ImageView imageViewNavigationViewHeaderItem;
  TextView tvNavigationViewHeaderUserID;
  TextView tvNavigationHeaderIsAdmin;
  TextView tvNavigationViewHeaderAddress;
  Menu menuNavigationView;
  MenuItem NavigationViewItemadmin;
  MenuItem NavigationViewItemLocateMode;
  MenuItem NavigationViewItemLongitude;
  MenuItem NavigationViewItemLatitude;
  MenuItem NavigationViewItemRadius;
  //NavigationView??????????????????
  MenuItem NavigationViewItemTrackQuery;
  MenuItem navigationViewItemTimeStart;
  MenuItem navigationViewItemTimeEnd;
  //????????????????????????
  View viewGeoFenceDialog;
  EditText dialogGeoFenceRadiusSetting;
  TextView dialogGeoFenceUserId;
  //???????????????
  Calendar calendar;
  SimpleDateFormat simpleDateFormatDate;
  //??????UI??????
  Handler handler;
  //??????
  Intent startService;
  //??????
  LocalBroadcastManager mLocalBroadcastManager;
  BroadcastReceiver locationReceiver;
  //??????
  MapFragment mapFragment;
  OptionMapFragment optionMapFragment;
  NearbyFragment nearbyFragment;
  //???????????????
  static final int MESSAGE_LOGIN_SUCCEED = 233;
  static final int UPDATE_NAVIGATIONVIEW_TIME_START = 234;
  static final int UPDATE_NAVIGATIONVIEW_TIME_END = 235;
  CoordinatorLayout coordinatorLayoutContainer;
  Snackbar snackbar;
  MyViewPager vp_FindFragment_pager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SDKInitializer.initialize(getApplicationContext());
    setContentView(R.layout.activity_main);
    application = (DeviceMessageApplication) getApplication();
    //?????????????????????
    mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("cc.bitlight.broadcast.location.data");
    intentFilter.addAction("cc.bitlight.broadcast.nearbyinfo.data");
    intentFilter.addAction("cc.bitlight.broadcast.track.historytrack.draw");
    intentFilter.addAction("cc.bitlight.broadcast.geofence.reload");
    intentFilter.addAction("cc.bitlight.broadcast.geofence.notification");
    locationReceiver = new MainBroadCastReceiver();
    mLocalBroadcastManager.registerReceiver(locationReceiver, intentFilter);
    coordinatorLayoutContainer = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutContainer);

    //??????UI??????
    setToolbarDrawerLayout();
    setTabLayout();

    //???????????????
    simpleDateFormatDate = new SimpleDateFormat("yyyy-M-d H:mm", Locale.CHINA);
    calendar = Calendar.getInstance();
    //????????????
    startService = new Intent(this, MapService.class);
    startService(startService);
    //??????UI??????
    handler = new Handler() {
      @Override
      public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
          case MESSAGE_LOGIN_SUCCEED:
            optionMapFragment.setLoginSucceed();
            break;

          case UPDATE_NAVIGATIONVIEW_TIME_START:
            navigationViewItemTimeStart.setTitle("?????????" + simpleDateFormatDate.format(application.getdateStartTime()));
            break;

          case UPDATE_NAVIGATIONVIEW_TIME_END:
            navigationViewItemTimeEnd.setTitle("?????????" + simpleDateFormatDate.format(application.getdateEndTime()));

            Log.d("lml", "application.getMillisecondStartTime():" + (int) (application.getMillisecondStartTime() / 1000));

            Log.d("lml", "application.getMillisecondEndTime():" + (int) (application.getMillisecondEndTime() / 1000));

            Log.d("lml", "System.currentTimeMillis():" + (int) (System.currentTimeMillis() / 1000));
            break;

        }
      }
    };
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Intent stopService = new Intent(this, MapService.class);
    stopService(stopService);
    application.clear();
  }

  void setToolbarDrawerLayout() {
    //Toolbar??????
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.inflateMenu(R.menu.toolbar_menu);
    //toolbar????????????
    menuToolbar = toolbar.getMenu();
    menuToolbarItemAbout = menuToolbar.findItem(R.id.menuToolbarItemAbout);
    menuToolbarItemTrackHistoryStatus = menuToolbar.findItem(R.id.menuToolbarItemTrackHistoryStatus);
    menuToolbarItemGeoFenceStatus = menuToolbar.findItem(R.id.menuToolbarItemGeoFenceStatus);
    menuToolbarItemNotification = menuToolbar.findItem(R.id.menuToolbarItemNotification);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
          //toolbar????????????????????????
          case R.id.menuToolbarItemTrackHistoryStatus:
            toolbarOperateClearTrack();
            snackbar = Snackbar.make(coordinatorLayoutContainer, "???????????????????????????", Snackbar.LENGTH_LONG);
            snackbar.show();
            break;
          case R.id.menuToolbarItemGeoFenceStatus:
            switch (menuToolbarItemGeoFenceStatus.getTitle().toString()) {
              case "???????????????":
                break;
              case "???????????????":
                Intent intent = new Intent("cc.bitlight.broadcast.geofence.query");
                intent.putExtra("type", "delete");
                mLocalBroadcastManager.sendBroadcast(intent);
                mapFragment.fenceCircleOverlay = null;
                mapFragment.refreshMapUI();
                menuToolbarItemGeoFenceStatus.setTitle("???????????????");
                menuToolbarItemGeoFenceStatus.setIcon(R.mipmap.menu_toolbar_ic_geofence_off);
                break;
              case "????????????":
                break;
            }
            break;
          case R.id.menuToolbarItemNotification:
            menuToolbarItemNotification.setIcon(R.mipmap.menu_toolbar_ic_notifications_off);
            LatLng pt2 = new LatLng(25.277218, 110.292316);
            LatLng pt1 = new LatLng(25.286893, 110.342765);
            // ?????? ????????????
            NaviParaOption para = new NaviParaOption()
                .startPoint(pt1).endPoint(pt2);

            BaiduMapNavigation.openWebBaiduMapNavi(para, MainActivity.this);
            break;
          case R.id.menuToolbarItemAbout:
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_toolbar_about, null);
            builder.setView(view);
            builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
              }
            });
            builder.create().show();
            break;
        }
        return true;
      }
    });
    toolbar.setTitle(toolbarTitle);
    toolbar.setTitleTextColor(getResources().getColor(R.color.white));

    //??????????????????
    drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    //?????????????????????toolbar
    mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.DrawerToggleOpen, R.string.DrawerToggleClose);
    drawerLayout.addDrawerListener(mActionBarDrawerToggle);
    //?????????????????????????????????
    drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
      @Override
      public void onDrawerSlide(View drawerView, float slideOffset) {
      }

      @Override
      public void onDrawerOpened(View drawerView) {
        toolbar.setTitle("??????");
      }

      @Override
      public void onDrawerClosed(View drawerView) {
        toolbar.setTitle(toolbarTitle);
      }

      @Override
      public void onDrawerStateChanged(int newState) {

      }
    });
    //mNavigationView?????????
    NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
    mNavigationView.setItemIconTintList(null);//???????????????????????????????????????
    //mNavigationView????????????
    headerView = mNavigationView.getHeaderView(0);
    imageViewNavigationViewHeaderItem = (ImageView) headerView.findViewById(R.id.imageViewNavigationViewHeaderItem);
    tvNavigationViewHeaderUserID = (TextView) headerView.findViewById(R.id.tvNavigationViewHeaderUserID);
    tvNavigationHeaderIsAdmin = (TextView) headerView.findViewById(R.id.tvNavigationHeaderIsAdmin);
    tvNavigationViewHeaderAddress = (TextView) headerView.findViewById(R.id.tvNavigationViewHeaderAddress);

    menuNavigationView = mNavigationView.getMenu();
    NavigationViewItemLocateMode = menuNavigationView.findItem(R.id.NavigationViewItemLocateMode);
    NavigationViewItemLongitude = menuNavigationView.findItem(R.id.NavigationViewItemLongitude);
    NavigationViewItemLatitude = menuNavigationView.findItem(R.id.NavigationViewItemLatitude);
    NavigationViewItemRadius = menuNavigationView.findItem(R.id.NavigationViewItemRadius);
    NavigationViewItemadmin = menuNavigationView.findItem(R.id.NavigationViewItemadmin);
    //NavigationView??????????????????
    NavigationViewItemTrackQuery = menuNavigationView.findItem(R.id.NavigationViewItemTrackQuery);
    navigationViewItemTimeStart = menuNavigationView.findItem(R.id.NavigationViewItemTimeStart);
    navigationViewItemTimeEnd = menuNavigationView.findItem(R.id.NavigationViewItemTimeEnd);
    mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.NavigationViewItemTrackQuery:
            queryHistoryTrack(application.getEntityName());
            break;
          case R.id.NavigationViewItemGeoFence:
            Toast.makeText(MainActivity.this, "sub item 04", Toast.LENGTH_SHORT).show();
            break;
          case R.id.NavigationViewItemTimeStart:
            SettingDate(0);
            break;
          case R.id.NavigationViewItemTimeEnd:
            SettingDate(1);
            break;
        }
        //??????????????????
        drawerLayout.closeDrawers();
        return true;
      }
    });
  }

  void setTabLayout() {
    TabLayout tab_FindFragment_title = (TabLayout) findViewById(R.id.tab_title);
    vp_FindFragment_pager = (MyViewPager) findViewById(R.id.vp_pager);

    //????????????fragment
    nearbyFragment = new NearbyFragment();
    optionMapFragment = new OptionMapFragment();
    mapFragment = new MapFragment();

    //???fragment???????????????
    List<Fragment> list_fragment = new ArrayList<>();
    list_fragment.add(mapFragment);
    list_fragment.add(nearbyFragment);
    list_fragment.add(optionMapFragment);

    //???????????????tab????????????????????????????????????????????????values/arrays.xml???????????????????????????
    List<String> list_title = new ArrayList<>();
    list_title.add("??????");
    list_title.add("??????");
    list_title.add("??????");

    //??????TabLayout?????????
    tab_FindFragment_title.setTabMode(TabLayout.MODE_FIXED);

    //???TabLayout??????tab??????
    tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
    tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
    tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(2)));
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentPagerAdapter fAdapter = new FindTabAdapter(fragmentManager, list_fragment, list_title);

    //viewpager??????adapter
    vp_FindFragment_pager.setAdapter(fAdapter);

    //TabLayout??????viewpager
    tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);
    // tab_FindFragment_title.
  }

  void SettingDate(final int index) {
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    View view = getLayoutInflater().inflate(R.layout.dialog_setting_date, null);
    final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
    //?????????????????????
    calendar.setTimeInMillis(System.currentTimeMillis());
    datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);
    //??????date??????
    builder.setView(view);
    builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        application.year = datePicker.getYear();
        application.month = datePicker.getMonth();
        application.day = datePicker.getDayOfMonth();
        Log.d("lml", "???????????????" + application.year + "  " + application.month + "  " + application.day);
        dialog.cancel();
        SettingTime(index);
      }
    });
    builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });
    builder.create().show();
  }

  void SettingTime(final int index) {
    //???????????????
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    View view = getLayoutInflater().inflate(R.layout.dialog_setting_time, null);
    final TimePicker timePicker = (TimePicker) view.findViewById(R.id.time_picker);
    //???????????????
    calendar.setTimeInMillis(System.currentTimeMillis());
    timePicker.setIs24HourView(true);
    timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
    timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    //??????time??????
    builder.setView(view);
    builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        application.hour = timePicker.getCurrentHour();
        application.minute = timePicker.getCurrentMinute();
        Log.d("lml", "???????????????" + application.hour + "  " + application.minute + "  " + application.second);

        dialog.cancel();
        Message message = new Message();
        switch (index) {
          case 0:
            message.what = UPDATE_NAVIGATIONVIEW_TIME_START;
            handler.sendMessage(message);
            break;
          case 1:
            message.what = UPDATE_NAVIGATIONVIEW_TIME_END;
            handler.sendMessage(message);
            break;
        }
      }
    });
    builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });
    builder.create().show();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mActionBarDrawerToggle.syncState();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    mActionBarDrawerToggle.onConfigurationChanged(newConfig);
  }

  /**
   * fragment?????????????????????????????????????????????
   */
  /**
   * OptionMapFragment??????????????????????????????
   */
  //???????????????????????????
  @Override
  public void sendBroadcastToSearchNearbyInfo() {
    Intent intent = new Intent("cc.bitlight.broadcast.service.start");
    mLocalBroadcastManager.sendBroadcast(intent);
    //??????NavigationView??????????????????
    imageViewNavigationViewHeaderItem.setImageResource(application.getGender().equals("m") ? R.mipmap.map_portrait_man : R.mipmap.map_portrait_woman);
    tvNavigationViewHeaderUserID.setText(application.getEntityName());
    tvNavigationHeaderIsAdmin.setText(application.getUsersIdentity());
    tvNavigationHeaderIsAdmin.setVisibility(View.VISIBLE);
    tvNavigationViewHeaderAddress.setVisibility(View.VISIBLE);
    NavigationViewItemadmin.setVisible(true);
    tvNavigationHeaderIsAdmin.setTextColor(getResources().getColor(R.color.red));
  }

  //?????????????????????Service????????????
  @Override
  public void LoginSucceedToQuit() {
    Intent intent = new Intent("cc.bitlight.broadcast.service.stop");
    mLocalBroadcastManager.sendBroadcast(intent);
    tvNavigationViewHeaderUserID.setText("???????????????");
    tvNavigationHeaderIsAdmin.setVisibility(View.GONE);
    tvNavigationViewHeaderAddress.setVisibility(View.GONE);
    nearbyFragment.clearList();
  }

  //??????????????????Service??????????????????
  void queryHistoryTrack(String entityName) {
    Intent intent = new Intent("cc.bitlight.broadcast.track.queryhistorytrack");
    toolbarTitle = entityName;
    intent.putExtra("entityName", entityName);
    mLocalBroadcastManager.sendBroadcast(intent);
  }

  /**
   * MapFragment??????????????????????????????
   */
  //??????toolbar???,???????????????????????????,????????????????????????
  //true:????????????????????????
  //false:?????????????????????????????????
  @Override
  public void toolbarOperateCloseTrackOrOpenColor(boolean isTrackOpen) {
    if (isTrackOpen) {
      toolbar.setTitle(toolbarTitle + "?????????");
      menuToolbarItemTrackHistoryStatus.setIcon(R.mipmap.menu_toolbar_ic_track_on);
    } else
      toolbarOperateClearTrack();
  }

  //?????????????????????????????????userId????????????
  @Override
  public void userQueryHistoryTrack(String userId) {
    queryHistoryTrack(userId);
  }

  //??????????????????????????????
  @Override
  public void geoFenceRadiusSettingDialogShow(final String userId, final LatLng latLng) {
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    //???????????????????????????
    viewGeoFenceDialog = View.inflate(MainActivity.this, R.layout.item_geofence_radius_setting, null);
    dialogGeoFenceRadiusSetting = (EditText) viewGeoFenceDialog.findViewById(R.id.dialogGeoFenceRadiusSetting);
    dialogGeoFenceUserId = (TextView) viewGeoFenceDialog.findViewById(R.id.dialogGeoFenceUserId);
    dialogGeoFenceUserId.setText(userId);

    builder.setView(viewGeoFenceDialog);
    builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        int geoFenceRadius = Integer.valueOf(dialogGeoFenceRadiusSetting.getText().toString());
        menuToolbarItemGeoFenceStatus.setTitle("???????????????");
        mapFragment.createOrUpdateFenceShow(geoFenceRadius, latLng);
        Intent intent = new Intent("cc.bitlight.broadcast.geofence.query");
        intent.putExtra("type", "create");
        intent.putExtra("longitude", latLng.longitude);
        intent.putExtra("latitude", latLng.latitude);
        intent.putExtra("radius", geoFenceRadius);
        intent.putExtra("userId", userId);
        mLocalBroadcastManager.sendBroadcast(intent);
        menuToolbarItemGeoFenceStatus.setIcon(R.mipmap.menu_toolbar_ic_geofence_on);
        dialog.cancel();
      }
    });
    builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });
    builder.create().show();
  }

  //??????toolbar????????????????????????????????????
  void toolbarOperateClearTrack() {
    //  mapFragment.isHistoryTrace = false;
    //  mapFragment.isTrace = false;
    mapFragment.polylineMyTrace = null;
    mapFragment.polylineHistoryTrace = null;
    mapFragment.refreshMapUI();
    menuToolbarItemTrackHistoryStatus.setIcon(R.mipmap.menu_toolbar_ic_track_off);
    mapFragment.switchLBSTrace.setChecked(false);
    toolbarTitle = "??????";
    toolbar.setTitle(toolbarTitle);
  }

  //???????????????
  public class MainBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      switch (intent.getAction()) {
        //??????????????????????????????
        case "cc.bitlight.broadcast.location.data": {
          NavigationViewItemLocateMode.setTitle("???????????????" + application.getLocateMode());
          NavigationViewItemLongitude.setTitle("?????????" + application.getLongitude());
          NavigationViewItemLatitude.setTitle("?????????" + application.getLatitude());
          NavigationViewItemRadius.setTitle("?????????" + application.getRadius() + "???");
          tvNavigationViewHeaderAddress.setText("???????????????" + application.getAddress());
          MyLocationData locData = new MyLocationData.Builder()
              .accuracy(application.getRadius())
              .latitude(application.getLatitude())
              .longitude(application.getLongitude()).build();
          // ??????????????????
          mapFragment.setBaiduMapLocationData(locData);
          Log.d("lml", "location.data?????????");
        }
        break;
        //??????????????????????????????
        case "cc.bitlight.broadcast.nearbyinfo.data": {
          List<MyRadarNearbyInfo> myRadarNearbyInfoList = application.getMyRadarNearbyInfoList();
          int infoListSize = myRadarNearbyInfoList.size();
          nearbyFragment.setRadarNearbyInfoList(myRadarNearbyInfoList, infoListSize);
          mapFragment.setRadarNearbyInfoList(myRadarNearbyInfoList, infoListSize);
          Log.d("lml", "nearbyinfo.data????????? ;" + "NearbyFragment:????????????list.size():" + infoListSize);
          Message message = new Message();
          message.what = MESSAGE_LOGIN_SUCCEED;
          handler.sendMessage(message);
        }
        break;
        //?????????????????????????????????????????????
        case "cc.bitlight.broadcast.track.historytrack.draw": {
          Log.d("lml", "track.historytrack.draw??????" + intent.getDoubleExtra("distance", 0));
          int distance = (int) intent.getDoubleExtra("distance", 0);
          NavigationViewItemTrackQuery.setTitle("??????????????????" + distance + "???");
          mapFragment.polylineHistoryTrace = new PolylineOptions();
          mapFragment.refreshMapUI();
          toolbarOperateCloseTrackOrOpenColor(true);//??????toolbar?????????????????????
        }
        break;
        case "cc.bitlight.broadcast.geofence.reload":
          menuToolbarItemGeoFenceStatus.setTitle("???????????????");
          menuToolbarItemGeoFenceStatus.setIcon(R.mipmap.menu_toolbar_ic_geofence_on);
          int radius = intent.getIntExtra("radius", 0);
          LatLng latLng = new LatLng(intent.getDoubleExtra("latitude", 0), intent.getDoubleExtra("longitude", 0));
          mapFragment.createOrUpdateFenceShow(radius, latLng);
          break;
        case "cc.bitlight.broadcast.geofence.notification":
          String userID = intent.getStringExtra("userID");
          int fenceStatus = intent.getIntExtra("fenceStatus", 0);
          if (fenceStatus == 1) {
            snackbar = Snackbar.make(coordinatorLayoutContainer, "??????\"" + userID + "\"????????????????????????", Snackbar.LENGTH_LONG);
            snackbar.setAction("????????????", new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                vp_FindFragment_pager.setCurrentItem(1);
              }
            });
            snackbar.show();
          }
          if (fenceStatus == 2) {
            snackbar = Snackbar.make(coordinatorLayoutContainer, "??????\"" + userID + "\"????????????????????????", Snackbar.LENGTH_LONG);
            snackbar.show();
          }
          break;
      }
    }
  }
}
