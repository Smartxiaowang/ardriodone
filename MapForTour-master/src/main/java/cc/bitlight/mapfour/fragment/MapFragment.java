package cc.bitlight.mapfour.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.util.ArrayList;
import java.util.List;

import cc.bitlight.mapfour.R;
import cc.bitlight.mapfour.customclass.DeviceMessageApplication;
import cc.bitlight.mapfour.customclass.MyRadarNearbyInfo;

public class MapFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, BaiduMap.OnMarkerClickListener, OnGetGeoCoderResultListener {
    DeviceMessageApplication application;
    //??????view??????
    boolean isFirstLocate = true;
    TextureMapView mMapView = null;
    BaiduMap baiduMap;
    Switch switchLocation;
    public Switch switchLBSTrace;
    //??????????????????
    OnclickSearchNearbyListener mlistener;
    //????????????????????????
    List<MyRadarNearbyInfo> radarNearbyInfoList = null;
    int infoListSize;

    //???????????????
    BitmapDescriptor bitmapDescriptorMan;
    BitmapDescriptor bitmapDescriptorWoman;
    BitmapDescriptor bitmapDescriptorCurrent;
    // ????????????????????????
    BitmapDescriptor bmStart;
    // ????????????????????????
    BitmapDescriptor bmEnd;
    // ?????????????????????
    MarkerOptions startMarker = null;
    // ?????????????????????
    MarkerOptions endMarker = null;
    MarkerOptions option;
    GeoCoder geoCoder;
    LatLng latLngshow;
    ReverseGeoCodeOption reverseGeoCodeOption;
    //Overlay?????????????????????
    LinearLayout layoutAddOverlayRadarNearbyItem;
    Bundle bundle = null;  //???????????????marker???????????????
    boolean generalIsMale = true;//???????????????marker?????????
    public PolylineOptions polylineMyTrace = null; //?????????????????????????????? for?????????????????????
    public PolylineOptions polylineHistoryTrace = null;//?????????????????????????????? for?????????????????????????????????
    public CircleOptions fenceCircleOverlay = null;//??????????????????????????????????????????????????????
    //??????????????????
    View viewOverlayItem;
    ImageView imageViewAddOverlayItem;
    TextView tvAddOverlayItemUserID;
    TextView tvAddOverlayItemDistance;
    TextView tvAddOverlayGeoCoder;
    TextView tvAddOverlayItemLatlng;
    Button btnAddOverlayItemTrackQuery;
    Button btnAddOverlayItemGeoFencePlace;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // ??????????????????Activity?????????????????????????????????????????????????????????????????????????????????
        try {
            mlistener = (OnclickSearchNearbyListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (DeviceMessageApplication) getActivity().getApplication();
        //????????????????????????
        bitmapDescriptorMan = BitmapDescriptorFactory.fromResource(R.mipmap.map_portrait_mark_man_circle);
        bitmapDescriptorWoman = BitmapDescriptorFactory.fromResource(R.mipmap.map_portrait_mark_woman_circle);
        bmStart = BitmapDescriptorFactory.fromResource(R.mipmap.icon_start);
        bmEnd = BitmapDescriptorFactory.fromResource(R.mipmap.icon_end);
        option = new MarkerOptions();
        geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(this);
        reverseGeoCodeOption = new ReverseGeoCodeOption();

        //??????????????????????????????????????????
        viewOverlayItem = View.inflate(getContext(), R.layout.item_map_addoverlay_radarnearby_admin, null);
        tvAddOverlayItemUserID = (TextView) viewOverlayItem.findViewById(R.id.tvAddOverlayItemUserID);
        imageViewAddOverlayItem = (ImageView) viewOverlayItem.findViewById(R.id.imageViewAddOverlayItem);
        tvAddOverlayGeoCoder = (TextView) viewOverlayItem.findViewById(R.id.tvAddOverlayGeoCoder);
        tvAddOverlayItemDistance = (TextView) viewOverlayItem.findViewById(R.id.tvAddOverlayItemDistance);
        tvAddOverlayItemLatlng = (TextView) viewOverlayItem.findViewById(R.id.tvAddOverlayItemLatlng);
        layoutAddOverlayRadarNearbyItem = (LinearLayout) viewOverlayItem.findViewById(R.id.layoutAddOverlayRadarNearbyItem);
        btnAddOverlayItemTrackQuery = (Button) viewOverlayItem.findViewById(R.id.btnAddOverlayItemTrackQuery);
        btnAddOverlayItemGeoFencePlace = (Button) viewOverlayItem.findViewById(R.id.btnAddOverlayItemGeoFencePlace);
        btnAddOverlayItemTrackQuery.setOnClickListener(this);
        btnAddOverlayItemGeoFencePlace.setOnClickListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        // Inflate the layout for this fragment
        switchLocation = (Switch) view.findViewById(R.id.switchLocation);
        switchLBSTrace = (Switch) view.findViewById(R.id.switchLBSTrace);
        switchLocation.setOnCheckedChangeListener(this);
        switchLBSTrace.setOnCheckedChangeListener(this);
        mMapView = (TextureMapView) view.findViewById(R.id.bmapView);
        baiduMap = mMapView.getMap();
        baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, false, null));
        application.setMaxZoomLevel(baiduMap.getMaxZoomLevel());
        // ??????????????????
        baiduMap.setMyLocationEnabled(true);
        baiduMap.setOnMarkerClickListener(this);
        //??????????????????fragment???????????????
        if (application.latLng != null) {
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(application.getLatLng(), application.getMaxZoomLevel() - 2);
            baiduMap.animateMapStatus(u);//?????????????????????
            if (radarNearbyInfoList != null) {
                refreshMapUI();
            }
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //???activity??????onResume?????????mMapView. onResume ()?????????????????????????????????
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //???activity??????onPause?????????mMapView. onPause ()?????????????????????????????????
        baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, false, null));
        mMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //???activity??????onDestroy?????????mMapView.onDestroy()?????????????????????????????????
        mMapView.onDestroy();
    }

    public void setRadarNearbyInfoList(List<MyRadarNearbyInfo> radarNearbyInfoList, int infoListSize) {
        this.radarNearbyInfoList = radarNearbyInfoList;
        this.infoListSize = infoListSize;
        refreshMapUI();

    }

    //???????????????????????????
    public void refreshMapUI() {
        baiduMap.clear();
        List<String> entityNameListTemp = new ArrayList<>();
        if (radarNearbyInfoList != null)
            for (RadarNearbyInfo radarNearbyInfo : radarNearbyInfoList) {
                bitmapDescriptorCurrent = radarNearbyInfo.comments.equals("m") ? bitmapDescriptorMan : bitmapDescriptorWoman;
                option.icon(bitmapDescriptorCurrent);
                option.position(radarNearbyInfo.pt);
                Bundle bundle = new Bundle();

                entityNameListTemp.add(radarNearbyInfo.userID);
                bundle.putString("userID", radarNearbyInfo.userID);
                bundle.putString("general", radarNearbyInfo.comments);
                bundle.putInt("distance", radarNearbyInfo.distance);
                option.extraInfo(bundle);
                baiduMap.addOverlay(option);
            }
        if (polylineMyTrace != null) {
            polylineMyTrace.color(getResources().getColor(R.color.deepBlue));
            polylineMyTrace.points(application.getLatLngList());
            baiduMap.addOverlay(polylineMyTrace);
        }
        if (polylineHistoryTrace != null) {
            polylineHistoryTrace.color(getResources().getColor(R.color.deepBlue));
            if (bundle != null)
                if (!bundle.getString("userID").equals(application.getEntityName()))
                    polylineHistoryTrace.color(getResources().getColor(bundle.getString("general").equals("m") ? R.color.deepBlue : R.color.pink));
            polylineHistoryTrace.points(application.latLngHistoryList);
            baiduMap.addOverlay(polylineHistoryTrace);
            // ??????????????????
            startMarker = new MarkerOptions()
                    .position(application.latLngHistoryList.get(application.latLngHistoryList.size() - 1)).icon(bmStart)
                    .zIndex(9).draggable(true);

            // ??????????????????
            endMarker = new MarkerOptions().position(application.latLngHistoryList.get(0))
                    .icon(bmEnd).zIndex(9).draggable(true);
            baiduMap.addOverlay(startMarker);
            baiduMap.addOverlay(endMarker);
        }
        if (fenceCircleOverlay != null) {
            baiduMap.addOverlay(fenceCircleOverlay);
        }
        application.entityNameList = entityNameListTemp;
    }

    //?????????marker?????????????????????
    //BaiduMap.OnMarkerClickListener???????????????
    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("lml", "MapFragment:??????????????????");
        baiduMap.hideInfoWindow();
        if (marker != null) {
            latLngshow = marker.getPosition();
            reverseGeoCodeOption.location(latLngshow);
            geoCoder.reverseGeoCode(reverseGeoCodeOption);
            tvAddOverlayGeoCoder.setText("????????????????????????");
            bundle = marker.getExtraInfo();

            generalIsMale = bundle.getString("general").equals("m");
            layoutAddOverlayRadarNearbyItem.setBackgroundColor(getResources().getColor(generalIsMale ? R.color.blue : R.color.pink));
            imageViewAddOverlayItem.setImageResource(generalIsMale ? R.mipmap.map_portrait_man : R.mipmap.map_portrait_woman);
            tvAddOverlayItemUserID.setText(bundle.getString("userID"));
            tvAddOverlayItemDistance.setText("??????" + bundle.getInt("distance") + "???        ");
            tvAddOverlayItemLatlng.setText("?????????   " + latLngshow.latitude + "  ,  " + latLngshow.longitude + "           ");
            Log.d("lml", "MapFragment???????????????:" + bundle.getString("userID"));
            Log.d("lml", bundle.getString("general") + ";" + generalIsMale);
            baiduMap.showInfoWindow(new InfoWindow(viewOverlayItem, marker.getPosition(), -70));
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(marker.getPosition());
            baiduMap.animateMapStatus(update);
            return true;
        } else
            return false;
    }

    //?????????????????????????????????
    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        Log.d("lml", "?????????????????????????????????");
        tvAddOverlayGeoCoder.setText(reverseGeoCodeResult.getAddress());
    }

    //???????????????????????????????????????
    public void createOrUpdateFenceShow(int geoFenceRadius, LatLng latLng) {
        CircleOptions fenceCircleOverlayTemp = new CircleOptions();//????????????????????????????????????
        fenceCircleOverlayTemp.fillColor(0x16ff00bb); //??????????????????
        fenceCircleOverlayTemp.center(latLng);  //??????????????????
        fenceCircleOverlayTemp.stroke(new Stroke(5, Color.rgb(0xff, 0x00, 0x7b)));//????????????
        fenceCircleOverlayTemp.radius(geoFenceRadius);  //????????????
        fenceCircleOverlay = fenceCircleOverlayTemp;
        refreshMapUI();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switchLBSTrace:
                if (isChecked) {
                    polylineMyTrace = new PolylineOptions();
                    refreshMapUI();
                    mlistener.toolbarOperateCloseTrackOrOpenColor(true);
                } else {
                    mlistener.toolbarOperateCloseTrackOrOpenColor(false);
                }
                break;
            case R.id.switchLocation:
                if (isChecked) {
                    baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, false, null));
                    baiduMap.animateMapStatus(MapStatusUpdateFactory.zoomTo(baiduMap.getMaxZoomLevel() - 2));
                } else
                    baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, false, null));
                break;
        }
    }

    //??????????????????
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //???????????????????????????????????????????????????
            case R.id.btnAddOverlayItemTrackQuery:
                mlistener.userQueryHistoryTrack(bundle.getString("userID"));
                baiduMap.hideInfoWindow();
                break;
            //???????????????????????????????????????????????????
            case R.id.btnAddOverlayItemGeoFencePlace:
                mlistener.geoFenceRadiusSettingDialogShow(bundle.getString("userID"), latLngshow);
                baiduMap.hideInfoWindow();
                break;
        }
    }


    public interface OnclickSearchNearbyListener {
        //??????toolbar???,???????????????????????????,????????????????????????
        //true:????????????????????????
        //false:?????????????????????????????????
        void toolbarOperateCloseTrackOrOpenColor(boolean b);

        //??????????????????????????????
        void userQueryHistoryTrack(String userId);

        //??????????????????????????????
        void geoFenceRadiusSettingDialogShow(String userId, LatLng latLng);
    }

    public void setBaiduMapLocationData(MyLocationData locData) {
        baiduMap.setMyLocationData(locData);
        if (isFirstLocate) {
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(application.getLatLng(), application.getMaxZoomLevel() - 2);
            baiduMap.animateMapStatus(u);//?????????????????????
            isFirstLocate = false;
        }
        Log.d("lml", "MapFragment - ??????????????????");
    }

}
