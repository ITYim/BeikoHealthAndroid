package com.alensic.beikohealth.helper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import com.alensic.beikohealth.base.GlobalParams;
import com.alensic.beikohealth.utils.ToastUtils;

import java.util.List;

/**
 * @author zym
 * @since 2017-08-23 17:52
 */
public class MyLocationManager {

    private final LocationManager mLocationManager;

    public static MyLocationManager getInstance(Context context) {
        return new MyLocationManager(context);
    }

    public MyLocationManager(Context context) {
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    private Location getLocation() {
        List<String> providers = mLocationManager.getProviders(true);
        if (providers == null || providers.isEmpty()) {
            ToastUtils.showToast("没有找到位置提供者");
            return null;
        }
        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            // 网络定位
            Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location !=null) {
                return location;
            } else if (providers.contains(LocationManager.GPS_PROVIDER)) {
                // 网络定位
                return mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        else if (providers.contains(LocationManager.GPS_PROVIDER)) {
            // 网络定位
            return mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        return null;
    }

    /**
     * 获取所在国家
     */
    public String getCountry() {
        Location location = getLocation();
        if (location != null) {
            Geocoder geocoder = new Geocoder(GlobalParams.getContext());
            try {
                List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addressList == null || addressList.isEmpty())
                    return "";
                return addressList.get(0).getCountryName();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }

    /**
     * 获取所在城市
     */
    public String getCity() {
        Location location = getLocation();
        if (location != null) {
            Geocoder geocoder = new Geocoder(GlobalParams.getContext());
            try {
                List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addressList == null || addressList.isEmpty())
                    return "";
                return addressList.get(0).getLocality();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }

    /**
     * 获取所在街道
     */
    public String getStreet() {
        Location location = getLocation();
        if (location != null) {
            Geocoder geocoder = new Geocoder(GlobalParams.getContext());
            try {
                List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addressList == null || addressList.isEmpty())
                    return "";
                return addressList.get(0).getThoroughfare();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }

    /**
     * 获取所在省份
     */
    public String getProvince() {
        Location location = getLocation();
        if (location != null) {
            Geocoder geocoder = new Geocoder(GlobalParams.getContext());
            try {
                List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addressList == null || addressList.isEmpty())
                    return "";
                return addressList.get(0).getAdminArea();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }
}
