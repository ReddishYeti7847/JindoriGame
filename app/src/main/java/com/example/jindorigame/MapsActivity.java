package com.example.jindorigame;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    List<String> area = new ArrayList<String>();
    int aNumber;    //取得した都道府県の番号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        area.add("北海道");
        area.add("青森県");
        area.add("岩手県");
        area.add("宮城県");
        area.add("秋田県");
        area.add("山形県");
        area.add("福島県");
        area.add("茨城県");
        area.add("栃木県");
        area.add("群馬県");
        area.add("埼玉県");
        area.add("千葉県");
        area.add("東京都");
        area.add("神奈川県");
        area.add("新潟県");
        area.add("富山県");
        area.add("石川県");
        area.add("福井県");
        area.add("山梨県");
        area.add("長野県");
        area.add("岐阜県");
        area.add("静岡県");
        area.add("愛知県");
        area.add("三重県");
        area.add("滋賀県");
        area.add("京都府");
        area.add("大阪府");
        area.add("兵庫県");
        area.add("奈良県");
        area.add("和歌山県");
        area.add("鳥取県");
        area.add("島根県");
        area.add("岡山県");
        area.add("広島県");
        area.add("山口県");
        area.add("徳島県");
        area.add("香川県");
        area.add("愛媛県");
        area.add("高知県");
        area.add("福岡県");
        area.add("佐賀県");
        area.add("長崎県");
        area.add("熊本県");
        area.add("大分県");
        area.add("宮崎県");
        area.add("鹿児島県");
        area.add("沖縄県");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//        Toast.makeText(MapsActivity.this, );

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
//                Toast.makeText(MapsActivity.this, getAddress(MapsActivity.this, latLng.latitude, latLng.longitude), Toast.LENGTH_SHORT).show();
                aNumber = getAreaNumber(getAddress(MapsActivity.this, latLng.latitude, latLng.longitude));
                Toast.makeText(MapsActivity.this, String.valueOf(aNumber), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String getAddress(
            Context context, double latitude, double longitude) {

        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        }
        catch (IOException e) {
            return "エラーが発生しました";
        }

        Log.d("県名", "県名　:" + addresses.toString());
        try {
            return 	addresses.get(0).getAdminArea();
        }
        catch (Exception e){
            return "表示できません";
        }
    }

    public int getAreaNumber(String str){
        if (area.contains(str) == false){
            return -1;
        }

        return area.indexOf(str) + 1;
    }
}
