package com.example.yujin.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PublicMap extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, OnMapReadyCallback {
    public static Activity PublicMap;
    String name, address;
    private static final String TAG = "@@@";
    private GoogleApiClient mGoogleApiClient = null;
    private LocationRequest mLocationRequest;
    private static final int REQUEST_CODE_LOCATION = 2000; //임의의 정수로 정의
    private static final int REQUEST_CODE_GPS = 2001; //임의의 정수로 정의
    private GoogleMap googleMap;
    LocationManager locationManager;
    MapFragment mapFragment;
    boolean setGPS = false;
    double curLatitude;
    double curLongitude;
    double purLatitude;
    double purLogitude;
    String lol;
    String sendAddress="";
    Button btnArrived;

    String strAddress;
    List<Address> listAddress;
    Geocoder geocoder;
    android.location.Address AddrAddress;
    Barcode.GeoPoint location;

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    //GPS 활성화를 위한 다이얼로그 보여주기
    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS가 비활성화 되어있습니다. 활성화 할까요?")
                .setCancelable(false)
                .setPositiveButton("설정", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(callGPSSettingIntent, REQUEST_CODE_GPS);
                    }
                });

        alertDialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    //GPS활성화를 위한 다이얼로그의 결과처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_GPS:
                //사용자가 GPS 활성 시켰는지 검사
                if (locationManager == null) {
                    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                }
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    setGPS = true;
                    mapFragment.getMapAsync(PublicMap.this);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_map);
        PublicMap = PublicMap.this;

        Log.d("onCreate", "onCreate");

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapPu);
        mapFragment.getMapAsync(this);


        btnArrived = (Button)findViewById(R.id.btnArrived);
        btnArrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.support.v7.app.AlertDialog.Builder logout = new android.support.v7.app.AlertDialog.Builder(PublicMap.this);
                logout.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        movingMarker aa = new movingMarker();
                        aa.start();
                    }
                });
                logout.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                logout.setNeutralButton("위치 재확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                logout.setMessage("홍길동 님이 "+ name+"에 도착하였습니다.");
                logout.show();

            }
        });

    }



    @Override
    public void onMapReady(final GoogleMap Map) {

        googleMap = Map;

        //handler 호출
        //Sharedpreference에 저장된 정보 가져오기
        SharedPreferences test = getSharedPreferences("주소", MODE_PRIVATE);
        lol = test.getString("주소", "empty");
        Log.i("쿠앙", lol);

        if(lol.equals("empty")){
            Toast.makeText(getApplicationContext(), "모임의 장소가 설정되지 않았습니다.", Toast.LENGTH_SHORT).show();
        }else {
            //정보 spilt
            String loll[] = lol.split("//");
            //위도, 경도 정보
            String lollol = loll[0];
            Log.i("위도 경도1", String.valueOf(location));
            lollol = lollol.replaceAll("lat/lng:", "");
            lollol = lollol.replaceAll("\\(", "");
            lollol = lollol.replaceAll("\\)", "");
            lollol = lollol.replaceAll(" ", "");

            Log.i("위도 경도", String.valueOf(location));

            String ll[] = lollol.split(",");


            purLatitude = Double.parseDouble(ll[0]);
            purLogitude = Double.parseDouble(ll[1]);

            Log.i("위도 경도", String.valueOf(purLatitude) + "," + String.valueOf(purLogitude));
            //주소, 이름 정보
            address = loll[1];
            name = loll[2];

            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
        }

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {

            @Override
            public void onMapLoaded() {
                Log.d(TAG, "onMapLoaded");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                } else {
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !setGPS) {
                        Log.d(TAG, "onMapLoaded");
                        showGPSDisabledAlertToUser();
                    }
                    if (mGoogleApiClient == null) {
                        buildGoogleApiClient();
                    }
                    if (ActivityCompat.checkSelfPermission(PublicMap.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(PublicMap.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    googleMap.setMyLocationEnabled(true);
                }
            }
        });

        //구글 플레이 서비스 초기화
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(PublicMap.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(PublicMap.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                googleMap.setMyLocationEnabled(true);
            } else {

            }
        } else {
            buildGoogleApiClient();
            googleMap.setMyLocationEnabled(true);

            //내 위치 정보 가져오기
            googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    Location location =googleMap.getMyLocation();
                    curLatitude = location.getLatitude();
                    curLongitude = location.getLongitude();
                    Log.i("우앙", curLatitude+","+curLongitude);




                    //현재위치에 마커생성
                    LatLng latLng = new LatLng(curLatitude, curLongitude);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title("현재위치");
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.detail_ic_map_me));
                    googleMap.addMarker(markerOptions);

                    //지도상에서 보여주는 영역 이동
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

                    Geocoder geocoder = new Geocoder(PublicMap.this, Locale.getDefault());
                    List<Address> addresses = null;

                    String errorMessage="";
                    try {
                        addresses = geocoder.getFromLocation(
                                location.getLatitude(),
                                location.getLongitude(),
                                1
                        );

                    } catch (IOException e) {
                        errorMessage = "지오코더 서비스 사용불가";
                        Toast.makeText(PublicMap.this, errorMessage, Toast.LENGTH_LONG).show();
                    } catch (IllegalArgumentException illegalArgumentException) {
                        // Catch invalid latitude or longitude values.
                        errorMessage = "잘못된 GPS 좌표";
                        Toast.makeText(PublicMap.this, errorMessage, Toast.LENGTH_LONG).show();

                    }

                    // Handle case where no address was found.
                    if (addresses == null || addresses.size() == 0) {
                        if (errorMessage.isEmpty()) {
                            errorMessage = "주소 미발견";
                            Log.e(TAG, errorMessage);
                        }
                        Toast.makeText(PublicMap.this, errorMessage, Toast.LENGTH_LONG).show();
                    } else {
                        android.location.Address address = addresses.get(0);
                        sendAddress = String.valueOf(addresses.get(0));
                        Toast.makeText(PublicMap.this, address.getAddressLine(0).toString(), Toast.LENGTH_LONG).show();
                    }

                    return false;
                }
            });
        }
    }

    //성공적으로 GoogleApiClient 객체 연결되었을 때 실행
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected");

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            setGPS = true;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onConnected" + "getLocationAvailability mGoogleApiClient.isConnected()=" + mGoogleApiClient.isConnected());
                if (!mGoogleApiClient.isConnected()) mGoogleApiClient.connect();

                if (setGPS && mGoogleApiClient.isConnected()) {
                    Log.d(TAG, "onConnected" + "requestLocationUpdates");
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

                    Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                    if (location == null) return;




                }
            }
        } else {
            Log.d(TAG, "onConnected" + "getLocationAvailability mGoogleApiClient.isConnected()=" + mGoogleApiClient.isConnected());
            if (!mGoogleApiClient.isConnected()) mGoogleApiClient.connect();

            if (setGPS && mGoogleApiClient.isConnected()) {
                Log.d(TAG, "onConnected" + "requestLocationUpdates");
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

               /* final Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location == null) return;

                //현재위치에 마커생성
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("현재위치");
                googleMap.addMarker(markerOptions);
                curLatitude = location.getLatitude();
                curLongitude = location.getLongitude();

                Log.i("우앙", curLatitude+","+curLongitude);

                //지도상에서 보여주는 영역 이동
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                googleMap.getUiSettings().setCompassEnabled(true);

                //GPS를 주소로 변환
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = null;

                String errorMessage="";
                try {
                    addresses = geocoder.getFromLocation(
                            location.getLatitude(),
                            location.getLongitude(),
                            1
                    );

                } catch (IOException e) {
                    errorMessage = "지오코더 서비스 사용불가";
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                } catch (IllegalArgumentException illegalArgumentException) {
                    // Catch invalid latitude or longitude values.
                    errorMessage = "잘못된 GPS 좌표";
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }

                // Handle case where no address was found.
                if (addresses == null || addresses.size() == 0) {
                    if (errorMessage.isEmpty()) {
                        errorMessage = "주소 미발견";
                        Log.e(TAG, errorMessage);
                    }
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                } else {
                    android.location.Address address = addresses.get(0);
                    sendAddress = String.valueOf(addresses.get(0));
                    Toast.makeText(this, address.getAddressLine(0).toString(), Toast.LENGTH_LONG).show();
                }*/
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //구글 플레이 서비스 연결이 해제되었을 때, 재연결 시도
        Log.d(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    // 나의 위치가 변경될 때
    @Override
    public void onLocationChanged(Location location) {

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {

        Log.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onstart", "onstart");

        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume", "onResume");
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {

        Log.d("onStop", "onStop");
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }


    @Override
    public void onPause() {

        Log.d("onPasue", "onPause");
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d("onDestroy", "onDestroy");

        Log.d(TAG, "OnDestroy");

        if (mGoogleApiClient != null) {
            mGoogleApiClient.unregisterConnectionCallbacks(this);
            mGoogleApiClient.unregisterConnectionFailedListener(this);

            if (mGoogleApiClient.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            }

            mGoogleApiClient.disconnect();
            mGoogleApiClient = null;
        }

        super.onDestroy();
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 1:
                    LatLng latLng = new LatLng(purLatitude, purLogitude);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title("모임위치 : "+ name);
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.detail_ic_map_place));



                    googleMap.addMarker(markerOptions);

                    //지도상에서 보여주는 영역 이동
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

                    Toast.makeText(getApplicationContext(),address,Toast.LENGTH_SHORT).show();

                    break;

                case 2:

                    LatLng aa = new LatLng(purLatitude, purLogitude);
                    MarkerOptions aaOption = new MarkerOptions();
                    aaOption.position(aa);
                    aaOption.title("모임위치 : "+ name);
                    aaOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.detail_ic_map_member1));
                    googleMap.addMarker(aaOption);



                    break;
            }
        }
    };



    class movingMarker extends Thread implements Runnable{
        Boolean flag = true;
       public void run(){
           try{
               Message msg = new Message();
               msg.what=2;
               handler.sendMessage(msg);

           }catch (Exception e){
               e.printStackTrace();
           }
       }
    }

}