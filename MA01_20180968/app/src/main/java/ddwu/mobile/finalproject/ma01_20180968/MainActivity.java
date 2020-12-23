package ddwu.mobile.finalproject.ma01_20180968;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";
    final static int PERMISSION_REQ_CODE = 100;

    private GoogleMap mGoogleMap;

    private MarkerOptions markerOptions;
    private PlacesClient placesClient;

    private MainThread task;
    private BitmapThread task2;
    private String apiAddress;

    private ArrayList<YoutubeData> resultList;
    private VideoAdapter videoAdapter;
    private ListView listView;
    private String searchKey = "cafe";

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(mapReadyCallBack);

        Places.initialize(getApplicationContext(), getString(R.string.api_key));
        placesClient = Places.createClient(this);

        apiAddress =getResources().getString(R.string.api_url) + getResources().getString(R.string.api_key) + "&q=";

        resultList = new ArrayList<>();
        videoAdapter = new VideoAdapter(this, R.layout.video, resultList);
        listView = (ListView)findViewById(R.id.customView);

        listView.setAdapter(videoAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(context, YoutubeActivity.class);
                intent.putExtra("id", resultList.get(pos).getId());
                startActivity(intent);
            }
        });
    }

    OnMapReadyCallback mapReadyCallBack = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mGoogleMap = googleMap;

            //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 17));

            if (checkPermission()) {
                mGoogleMap.setMyLocationEnabled(true);
                //mGoogleMap.setOnMyLocationButtonClickListener(locationsButtonClickListener);// 버튼 클릭시 동작
                //mGoogleMap.setOnMyLocationClickListener(locationClickListener);
            }

            mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    String placeId = marker.getTag().toString();
                    List<Place.Field> placeFields =
                            Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.PHONE_NUMBER, Place.Field.ADDRESS);
                    FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, placeFields).build();

                    placesClient.fetchPlace(request).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                        @Override
                        public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                            Place place = fetchPlaceResponse.getPlace();
                            Log.d(TAG, "Place found: " + place.getName());
                            Log.d(TAG, "Phone: " + place.getPhoneNumber());
                            Log.d(TAG, "Address: " + place.getAddress());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            if (exception instanceof ApiException) {
                                ApiException apiException = (ApiException) exception;
                                int statusCode = apiException.getStatusCode();
                                Log.e(TAG, "Place not found: " + exception.getMessage());
                            }
                        }
                    });
                }
            });
        }
    };

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                Log.d(TAG, "Test");

                task = new MainThread();
                task.execute(apiAddress, searchKey+"bgm");
                break;
            case R.id.btnStop:
                AsyncTask.Status status = task.getStatus();
                Toast.makeText(this, "Status" + status, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnYtb:
                Intent intent = new Intent(this, YoutubeActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSearch:
                searchStart(PlaceType.CAFE);
                break;
        }
    }

    private void searchStart(String type) {
        new NRPlaces.Builder().listener(placesListener)
                .key(getString(R.string.api_key))
                .latlng(Double.valueOf(getResources().getString(R.string.init_lat)),
                        Double.valueOf(getResources().getString(R.string.init_lng)))
                .radius(100)
                .type(type)
                .build()
                .execute();
    }


    PlacesListener placesListener = new PlacesListener() {
        @Override
        public void onPlacesSuccess(final List<noman.googleplaces.Place> places) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    searchKey = places.get(0).getName();
                }
            });
        }

        @Override
        public void onPlacesFailure(PlacesException e) {}

        @Override
        public void onPlacesStart() {}

        @Override
        public void onPlacesFinished() {}
    };

    // 필요 permission 요청
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSION_REQ_CODE);
                return false;
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQ_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // 퍼미션을 획득하였을 경우 맵 로딩 실행
                //mapLoad();
            } else {
                // 퍼미션 미획득 시 액티비티 종료
                Toast.makeText(this, "앱 실행을 위해 권한 허용이 필요함", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    public class MainThread extends AsyncTask<String, Integer, String> {
        ProgressDialog progressDlg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDlg = ProgressDialog.show(MainActivity.this, "Wait", "Downloading...");
        }

        @Override
        protected String doInBackground(String... strings) {
            String address = strings[0];
            String q = strings[1];

            String apiURL = null;

            try {
                apiURL = address + URLEncoder.encode(q, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String result = downloadContents(apiURL);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            progressDlg.dismiss();

            ArrayList<YoutubeData> parList = parse(result);     // 오픈API 결과의 파싱 수행

            if (parList.size() == 0) {
                Toast.makeText(MainActivity.this, "No data!", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "END");
                resultList.clear();
                resultList.addAll(parList);
                videoAdapter.notifyDataSetChanged();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }
    }

    private boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    protected String downloadContents(String address) {
        HttpURLConnection conn = null;
        InputStream stream = null;
        String result = null;

        try {
            URL url = new URL(address);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");

            stream = getNetworkConnection(conn);
            result = readStreamToString(stream);
            if (stream != null) stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.disconnect();
        }

        return result;
    }

    /* 주소(address)에 접속하여 비트맵 데이터를 수신한 후 반환 */
    protected Bitmap downloadImage(String address) {
        HttpURLConnection conn = null;
        InputStream stream = null;
        Bitmap result = null;

        try {
            URL url = new URL(address);
            conn = (HttpURLConnection)url.openConnection();
            stream = getNetworkConnection(conn);
            result = readStreamToBitmap(stream);
            if (stream != null) stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.disconnect();
        }

        return result;
    }


    /* URLConnection 을 전달받아 연결정보 설정 후 연결, 연결 후 수신한 InputStream 반환 */
    private InputStream getNetworkConnection(HttpURLConnection conn) throws Exception {
        conn.setReadTimeout(3000);
        conn.setConnectTimeout(3000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);

        if (conn.getResponseCode() != HttpsURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code: " + conn.getResponseCode());
        }

        return conn.getInputStream();
    }


    /* InputStream을 전달받아 문자열로 변환 후 반환 */
    protected String readStreamToString(InputStream stream){
        StringBuilder result = new StringBuilder();

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String readLine = bufferedReader.readLine();

            while (readLine != null) {
                result.append(readLine + "\n");
                readLine = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }


    /* InputStream을 전달받아 비트맵으로 변환 후 반환 */
    protected Bitmap readStreamToBitmap(InputStream stream) {
        return BitmapFactory.decodeStream(stream);
    }

    public  ArrayList<YoutubeData> parse(String string) {
        Log.d(TAG, "parse Start!");
        ArrayList<YoutubeData> resultList = new ArrayList<>();
        YoutubeData data = null;

        boolean thumType = false;
        long i = 0;

        String[] lines = string.split(System.getProperty("line.separator"));

        for (String line : lines) {
            if (line.contains("videoId")) {
                data = new YoutubeData();
                data.set_id(i);
                i += 1;

                int index1 = line.indexOf("\"");
                int index2 = line.indexOf("\"", index1 + 1);
                int index3 = line.indexOf("\"", index2 + 1);
                int index4 = line.indexOf("\"", index3 + 1);
                data.setId(line.substring(index3 + 1, index4));
                //Log.d(TAG, "id: " + line.substring(index3 + 1, index4));
            }
            else if (line.contains("title")) {
                int index1 = line.indexOf("\"");
                int index2 = line.indexOf("\"", index1 + 1);
                int index3 = line.indexOf("\"", index2 + 1);
                int index4 = line.indexOf("\"", index3 + 1);
                data.setTitle(line.substring(index3 + 1, index4));
                //Log.d(TAG, "title: " + line.substring(index3 + 1, index4));
            }
            else if (line.contains("description")) {
                int index1 = line.indexOf("\"");
                int index2 = line.indexOf("\"", index1 + 1);
                int index3 = line.indexOf("\"", index2 + 1);
                int index4 = line.indexOf("\"", index3 + 1);
                data.setDescription(line.substring(index3 + 1, index4));
                //Log.d(TAG, "des: " + line.substring(index3 + 1, index4));
            }
            else if (line.contains("thumbnails")) {
                thumType = true;
            }
            else if (line.contains("url") && thumType) {
                int index1 = line.indexOf("\"");
                int index2 = line.indexOf("\"", index1 + 1);
                int index3 = line.indexOf("\"", index2 + 1);
                int index4 = line.indexOf("\"", index3 + 1);
                data.setThubnailsURL(line.substring(index3 + 1, index4));
                thumType = false;

                //Log.d(TAG, "url: " + line.substring(index3 + 1, index4));

                resultList.add(data);
            }
        }

        task2 = new BitmapThread();
        task2.execute(resultList.get(0).getThubnailsURL(), resultList.get(1).getThubnailsURL(), resultList.get(2).getThubnailsURL());

        return resultList;
    }

    public class BitmapThread extends AsyncTask<String, Integer, Bitmap[]> {
        ProgressDialog progressDlg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDlg = ProgressDialog.show(MainActivity.this, "Wait", "Downloading...");
        }

        @Override
        protected Bitmap[] doInBackground(String... strings) {
            Bitmap[] result = new Bitmap[3];
            result[0] = downloadImage(strings[0]);
            result[1] = downloadImage(strings[1]);
            result[2] = downloadImage(strings[2]);
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap[] result) {
            progressDlg.dismiss();

            for (int i = 0; i < 3; i++) {
                resultList.get(i).setThub(result[i]);
            }

            videoAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Bitmap[] s) {
            super.onCancelled(s);
        }
    }
}