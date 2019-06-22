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
    public AreaList area = new AreaList();

    int aNumber;    //取得した都道府県の番号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
                String nowArea = getAddress(MapsActivity.this, latLng.latitude, latLng.longitude);
                Log.d("nowArea", nowArea);

                aNumber = area.getAreaNumber(nowArea);
//                Toast.makeText(MapsActivity.this, String.valueOf(aNumber), Toast.LENGTH_SHORT).show();

                Log.d("データ", "番号" + String.valueOf(aNumber));

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
}

class AreaList {
    ArrayList<String> area = new ArrayList<>(); //県のデータ(リスト)
    ArrayList<ArrayList<String>> near = new ArrayList<>();  //隣接する県のデータ(リストのリスト)

    public AreaList(){
        for (int i = 0; i < 47; i++) {
            near.add(new ArrayList<String>());
        }

        //都道府県のデータ
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
        //隣接する都道府県のデータ
            //北海道
            near.get(0).add("");
            //青森県
            near.get(1).add("岩手県");
            near.get(1).add("秋田県");
            //岩手県
            near.get(2).add("青森県");
            near.get(2).add("宮城県");
            near.get(2).add("秋田県");
            //宮城県
            near.get(3).add("岩手県");
            near.get(3).add("秋田県");
            near.get(3).add("山形県");
            near.get(3).add("福島県");
            //秋田県
            near.get(4).add("青森県");
            near.get(4).add("岩手県");
            near.get(4).add("宮城県");
            near.get(4).add("山形県");
            //山形県
            near.get(5).add("宮城県");
            near.get(5).add("秋田県");
            near.get(5).add("福島県");
            near.get(5).add("新潟県");
            //福島県
            near.get(6).add("宮城県");
            near.get(6).add("山形県");
            near.get(6).add("茨城県");
            near.get(6).add("栃木県");
            near.get(6).add("群馬県");
            near.get(6).add("新潟県");
            //茨城県
            near.get(7).add("福島県");
            near.get(7).add("栃木県");
            near.get(7).add("埼玉県");
            near.get(7).add("千葉県");
            //栃木県
            near.get(8).add("福島県");
            near.get(8).add("茨城県");
            near.get(8).add("群馬県");
            near.get(8).add("埼玉県");
            //群馬県
            near.get(9).add("福島県");
            near.get(9).add("栃木県");
            near.get(9).add("埼玉県");
            near.get(9).add("新潟県");
            near.get(9).add("長崎県");
            //埼玉県
            near.get(10).add("茨城県");
            near.get(10).add("栃木県");
            near.get(10).add("群馬県");
            near.get(10).add("千葉県");
            near.get(10).add("東京都");
            near.get(10).add("山梨県");
            near.get(10).add("長野県");
            //千葉県
            near.get(11).add("茨城県");
            near.get(11).add("埼玉県");
            near.get(11).add("東京都");
            //東京都
            near.get(12).add("埼玉県");
            near.get(12).add("千葉県");
            near.get(12).add("神奈川県");
            near.get(12).add("山梨県");
            //神奈川県
            near.get(13).add("東京都");
            near.get(13).add("山梨県");
            near.get(13).add("静岡県");
            //新潟県
            near.get(14).add("山形県");
            near.get(14).add("福島県");
            near.get(14).add("群馬県");
            near.get(14).add("富山県");
            near.get(14).add("長野県");
            //富山県
            near.get(15).add("新潟県");
            near.get(15).add("石川県");
            near.get(15).add("長野県");
            near.get(15).add("岐阜県");
            //石川県
            near.get(16).add("富山県");
            near.get(16).add("福井県");
            near.get(16).add("岐阜県");
            //福井県
            near.get(17).add("石川県");
            near.get(17).add("岐阜県");
            near.get(17).add("滋賀県");
            near.get(17).add("京都府");
            //山梨県
            near.get(18).add("埼玉県");
            near.get(18).add("東京都");
            near.get(18).add("神奈川県");
            near.get(18).add("長野県");
            near.get(18).add("静岡県");
            //長野県
            near.get(19).add("群馬県");
            near.get(19).add("埼玉県");
            near.get(19).add("新潟県");
            near.get(19).add("富山県");
            near.get(19).add("山梨県");
            near.get(19).add("岐阜県");
            near.get(19).add("静岡県");
            near.get(19).add("愛知県");
            //岐阜県
            near.get(20).add("富山県");
            near.get(20).add("石川県");
            near.get(20).add("福井県");
            near.get(20).add("長野県");
            near.get(20).add("愛知県");
            near.get(20).add("三重県");
            near.get(20).add("滋賀県");
            //静岡県
            near.get(21).add("神奈川県");
            near.get(21).add("山梨県");
            near.get(21).add("長野県");
            near.get(21).add("愛知県");
            //愛知県
            near.get(22).add("長野県");
            near.get(22).add("岐阜県");
            near.get(22).add("静岡県");
            near.get(22).add("三重県");
            //三重県
            near.get(23).add("岐阜県");
            near.get(23).add("愛知県");
            near.get(23).add("滋賀県");
            near.get(23).add("京都府");
            near.get(23).add("奈良県");
            near.get(23).add("和歌山県");
            //滋賀県
            near.get(24).add("福井県");
            near.get(24).add("岐阜県");
            near.get(24).add("三重県");
            near.get(24).add("京都府");
            //京都府
            near.get(25).add("三重県");
            near.get(25).add("滋賀県");
            near.get(25).add("大分県");
            near.get(25).add("兵庫県");
            near.get(25).add("奈良県");
            //大阪府
            near.get(26).add("京都府");
            near.get(26).add("兵庫県");
            near.get(26).add("奈良県");
            near.get(26).add("和歌山県");
            //兵庫県
            near.get(27).add("京都府");
            near.get(27).add("大阪府");
            near.get(27).add("鳥取県");
            near.get(27).add("岡山県");
            //奈良県
            near.get(28).add("三重県");
            near.get(28).add("京都府");
            near.get(28).add("大阪府");
            near.get(28).add("和歌山県");
            //和歌山県
            near.get(29).add("三重県");
            near.get(29).add("大阪府");
            near.get(29).add("奈良県");
            //鳥取県
            near.get(30).add("兵庫県");
            near.get(30).add("島根県");
            near.get(30).add("岡山県");
            near.get(30).add("広島県");
            //島根県
            near.get(31).add("鳥取県");
            near.get(31).add("広島県");
            near.get(31).add("山口県");
            //岡山県
            near.get(32).add("兵庫県");
            near.get(32).add("鳥取県");
            near.get(32).add("広島県");
            //広島県
            near.get(33).add("鳥取県");
            near.get(33).add("島根県");
            near.get(33).add("岡山県");
            near.get(33).add("山口県");
            //山口県
            near.get(34).add("島根県");
            near.get(34).add("広島県");
            //徳島県
            near.get(35).add("香川県");
            near.get(35).add("愛媛県");
            near.get(35).add("高知県");
            //香川県
            near.get(36).add("徳島県");
            near.get(36).add("愛媛県");
            //愛媛県
            near.get(37).add("徳島県");
            near.get(37).add("香川県");
            near.get(37).add("高知県");
            //高知県
            near.get(38).add("徳島県");
            near.get(38).add("愛媛県");
            //福岡県
            near.get(39).add("佐賀県");
            near.get(39).add("熊本県");
            near.get(39).add("大分県");
            //佐賀県
            near.get(40).add("福岡県");
            near.get(40).add("長崎県");
            //長崎県
            near.get(41).add("佐賀県");
            //熊本県
            near.get(42).add("福岡県");
            near.get(42).add("大分県");
            near.get(42).add("宮崎県");
            //大分県
            near.get(43).add("福岡県");
            near.get(43).add("熊本県");
            near.get(43).add("宮崎県");
            //宮崎県
            near.get(44).add("熊本県");
            near.get(44).add("大分県");
            near.get(44).add("鹿児島県");
            //鹿児島県
            near.get(45).add("熊本県");
            near.get(45).add("宮崎県");
            //沖縄県
            near.get(46).add("");
    }

    public void getAreaString(int num){   //都道府県の名前の取得
        return;
    }

    public int getAreaNumber(String str){   //都道府県の番号を取得
        Log.d("nowArea", "実行");

        if (this.area.contains(str) == false){
            Log.d("nowArea", "存在しません");
            return -1;
        }

        return area.indexOf(str);
    }
}
