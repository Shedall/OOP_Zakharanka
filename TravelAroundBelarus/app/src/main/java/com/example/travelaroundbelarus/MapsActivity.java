package com.example.travelaroundbelarus;

//////////////////////////////////////////////////
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.LatLngBounds;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
//import com.google.android.libraries.places.api.model.Place;
//import com.google.android.libraries.places.api.model.RectangularBounds;
//import com.google.android.libraries.places.api.net.FetchPlaceRequest;
//import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
//import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
//import com.google.android.libraries.places.api.net.PlacesClient;
//
//import java.util.Collections;
//
//public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
//
//    private PlacesClient placesClient;
//    private GoogleMap googleMap;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//
//        // Инициализация Places SDK с использованием вашего API-ключа
//        String apiKey = "AIzaSyBjI8gwmnvFH1J5_1FeKi7FRebaBx-QVfk";
//        Places.initialize(getApplicationContext(), apiKey);
//        placesClient = Places.createClient(this);
//
//        // Получение SupportMapFragment и установка обратного вызова OnMapReadyCallback
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    public void onMapReady(GoogleMap map) {
//        googleMap = map;
//
//        // Задание ограничения области поиска на Беларусь
//        RectangularBounds bounds = RectangularBounds.newInstance(
//                new LatLng(51.2709, 23.1900), // Юго-западная граница Беларуси
//                new LatLng(56.1724, 32.7187)  // Северо-восточная граница Беларуси
//        );
//
//        // Создание AutocompleteSessionToken
//        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
//
//        // Создание запроса на поиск автозаполнения с ограничением области
//        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
//                .setQuery("достопримечательности Беларуси")
//                .setCountry("BY") // Код страны для Беларуси
//                .setLocationRestriction(bounds)
//                .setSessionToken(token)
//                .build();
//
//        // Выполнение запроса на поиск автозаполнения
//        placesClient.findAutocompletePredictions(request)
//                .addOnSuccessListener((response) -> {
//                    // Обработка успешного выполнения запроса
//                    for (com.google.android.libraries.places.api.model.AutocompletePrediction prediction : response.getAutocompletePredictions()) {
//                        String placeId = prediction.getPlaceId();
//
//                        // Запрос на получение подробной информации о месте по его placeId
//                        placesClient.fetchPlace(FetchPlaceRequest.newInstance(placeId, Collections.singletonList(Place.Field.LAT_LNG)))
//                                .addOnSuccessListener((placeResponse) -> {
//                                    Place place = placeResponse.getPlace();
//                                    LatLng placeLatLng = place.getLatLng();
//
//                                    // Добавление метки на карту
//                                    googleMap.addMarker(new MarkerOptions().position(placeLatLng).title(prediction.getFullText(null).toString()));
//                                })
//                                .addOnFailureListener((exception) -> {
//                                    // Обработка ошибок при получении информации о месте
//                                    Toast.makeText(MapsActivity.this, "Ошибка при получении информации о месте: " + exception.getMessage(), Toast.LENGTH_LONG).show();
//                                });
//                    }
//                })
//                .addOnFailureListener((exception) -> {
//                    // Обработка ошибок при выполнении запроса
//                    Toast.makeText(MapsActivity.this, "Ошибка при выполнении запроса: " + exception.getMessage(), Toast.LENGTH_LONG).show();
//                });
//
//        // Перемещение камеры к Беларуси
//        LatLngBounds latLngBounds = new LatLngBounds(
//                new LatLng(51.2709, 23.1900), // Юго-западная граница Беларуси
//                new LatLng(56.1724, 32.7187)  // Северо-восточная граница Беларуси
//        );
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
//    }
//}


///////////////////////////////////////////////////////////////////


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.travelaroundbelarus.models.Attraction;
import com.example.travelaroundbelarus.models.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private PlacesClient placesClient;
    private GoogleMap googleMap;
    FirebaseDatabase db;
    DatabaseReference usersRef;
    DatabaseReference attraction;
    Attraction atr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Инициализация Places SDK с использованием вашего API-ключа
        String apiKey = "AIzaSyBjI8gwmnvFH1J5_1FeKi7FRebaBx-QVfk";
        Places.initialize(getApplicationContext(), apiKey);
        placesClient = Places.createClient(this);

        // Получение ссылки на SupportMapFragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        db = FirebaseDatabase.getInstance();
        attraction = db.getReference("attraction");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Задание ограничения области поиска на Беларусь
        RectangularBounds bounds = RectangularBounds.newInstance(
                new LatLng(51.2709, 23.1900), // Юго-западная граница Беларуси
                new LatLng(56.1724, 32.7187)  // Северо-восточная граница Беларуси
        );

        // Создание AutocompleteSessionToken
        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();

        // Создание запроса на поиск автозаполнения с ограничением области
        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setQuery("достопримечательности Беларуси")
                .setCountry("BY") // Код страны для Беларуси
                .setLocationRestriction(bounds)
                .setSessionToken(token)
                .build();

        // Выполнение запроса на поиск автозаполнения
        placesClient.findAutocompletePredictions(request)
                .addOnSuccessListener((response) -> {
                    // Обработка успешного выполнения запроса
                    for (com.google.android.libraries.places.api.model.AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                        Log.i("Place prediction", prediction.getFullText(null).toString());
                        // Получение идентификатора места
                        String placeId = prediction.getPlaceId();
                        // Выполнение запроса на получение информации о месте
                        List<Place.Field> placeFields = Arrays.asList(Place.Field.LAT_LNG, Place.Field.NAME);
                        FetchPlaceRequest placeRequest = FetchPlaceRequest.builder(placeId, placeFields).build();
                        placesClient.fetchPlace(placeRequest)
                                .addOnSuccessListener((placeResponse) -> {
                                    Place place = placeResponse.getPlace();
                                    String placeName = place.getName();
                                    LatLng placeLatLng = place.getLatLng();
                                    // Добавление метки на карту
                                    googleMap.addMarker(new MarkerOptions().title(placeName).position(placeLatLng));
                                })
                                .addOnFailureListener((exception) -> {
                                    // Обработка ошибок при выполнении запроса
                                    Toast.makeText(MapsActivity.this, "Ошибка при выполнении запроса: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    }
                })
                .addOnFailureListener((exception) -> {
                    // Обработка ошибок при выполнении запроса
                    Toast.makeText(MapsActivity.this, "Ошибка при выполнении запроса: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                });

        // Установка камеры карты на Беларусь
        LatLng belarusBoundsCenter = new LatLng(53.7098, 27.9534);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(belarusBoundsCenter, 7));
        attraction.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot attractionSnapshot : dataSnapshot.getChildren()) {
                    // Получите данные о достопримечательности из attractionSnapshot
                    /*Attraction*/ atr = attractionSnapshot.getValue(Attraction.class);

                    addMarker(atr.getLatitude(),atr.getLongitude(),atr.getTitle(),atr.getAdres(),atr.getDescription());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MapsActivity.this, "Ошибка при выполнении запроса: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //addMarker(53.451295,26.473257,"Мирский замок","Мир, Кореличский район","Классика жанра! Замок должен был стать резиденцией магнатского рода Ильиничей, которые начали строить его еще в XVI веке. Но род быстро закончился, а квадратные метры полезной площади перешли Радзивиллам, которые превратили его в радостный многослойный торт. По легенде, они еще и забабахали подземный ход в три полосы до второй своей резиденции – Несвижского замка. Последним владельцем Мирского замка был князь Михаил Святополк-Мирский, отец которого при загадочных обстоятельствах утонул в замковом рве, который сам же и приказал выкопать. Во времена Советов здесь находилась рабочая артель, а во время оккупации – гетто и лагерь для военнопленных. В 2000-е замок пережил масштабную реконструкцию, здесь разместили бизнес-центр и отель.");
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
    }

    public void addMarker(double latitude, double longitude, String title, String address, String description) {
        LatLng latLng = new LatLng(latitude, longitude);

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet(address);

        // Добавление пользовательской иконки маркера (если требуется)
        //Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.custom_marker);
        //markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));

        Marker marker = googleMap.addMarker(markerOptions);
        marker.setTag(description);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String description = (String) marker.getTag();
                String title = marker.getTitle();
                String address = marker.getSnippet();
                if (description != null) {
                    // Открытие нового окна или действия при нажатии на метку
                    openInfoWindow(marker,title,address, description);
                }
                return false;
            }
        });
    }

    private void openInfoWindow(Marker marker,String title, String address, String description) {
        // Действия при открытии информационного окна (например, открытие новой активности или диалогового окна)
        //Toast.makeText(mContext, "Описание: " + description, Toast.LENGTH_SHORT).show();
        User user = getIntent().getParcelableExtra("user");
        String userId = getIntent().getStringExtra("userid");
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("                 " + title + "                   ");
        dialog.setMessage("АДРЕС : " + address+"\n" + "\n" + "ОПИСАНИЕ :\n" + description);
        dialog.setPositiveButton("закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}




/////////////////////////////////////////////////////////////////////////////
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
//import com.google.android.libraries.places.api.model.RectangularBounds;
//import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
//import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
//import com.google.android.libraries.places.api.net.PlacesClient;
//
//public class MapsActivity extends AppCompatActivity {
//
//    private PlacesClient placesClient;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Инициализация Places SDK с использованием вашего API-ключа
//        String apiKey = "AIzaSyBjI8gwmnvFH1J5_1FeKi7FRebaBx-QVfk";
//        Places.initialize(getApplicationContext(), apiKey);
//        placesClient = Places.createClient(this);
//
//        // Задание ограничения области поиска на Беларусь
//        RectangularBounds bounds = RectangularBounds.newInstance(
//                new com.google.android.gms.maps.model.LatLng(51.2709, 23.1900), // Юго-западная граница Беларуси
//                new com.google.android.gms.maps.model.LatLng(56.1724, 32.7187)  // Северо-восточная граница Беларуси
//        );
//
//        // Создание AutocompleteSessionToken
//        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
//
//        // Создание запроса на поиск автозаполнения с ограничением области
//        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
//                .setCountry("BY") // Код страны для Беларуси
//                .setLocationRestriction(bounds)
//                .setSessionToken(token)
//                .build();
//
//        // Выполнение запроса на поиск автозаполнения
//        placesClient.findAutocompletePredictions(request)
//                .addOnSuccessListener((response) -> {
//                    // Обработка успешного выполнения запроса
//                    for (com.google.android.libraries.places.api.model.AutocompletePrediction prediction : response.getAutocompletePredictions()) {
//                        Log.i("Place prediction", prediction.getFullText(null).toString());
//                        // Здесь вы можете обработать каждое предсказание места по вашему усмотрению
//                        // Можете сохранить их в список или использовать для дальнейших операций с местами
//                    }
//                })
//                .addOnFailureListener((exception) -> {
//                    // Обработка ошибок при выполнении запроса
//                    Toast.makeText(MapsActivity.this, "Ошибка при выполнении запроса: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
//                });
//    }
//}


//////////////////////////////////////////////////////////////////////////////////////////////

//import androidx.fragment.app.FragmentActivity;import android.os.Bundle;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.example.travelaroundbelarus.databinding.ActivityMapsBinding;
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.model.RectangularBounds;
//import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
//import com.google.android.libraries.places.api.net.FetchPlaceRequest;
//
//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
//
//    private GoogleMap mMap;
//    private ActivityMapsBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Places.initialize(getApplicationContext(), "AIzaSyBjI8gwmnvFH1J5_1FeKi7FRebaBx-QVfk");
//        double lat = 53.9045; // Широта Беларуси
//        double lng = 27.5615; // Долгота Беларуси
//        int radius = 50000; // Радиус поиска в метрах
//
////        FetchPlaceRequest request = FetchPlaceRequest.newInstance("tourist_attraction", FetchPlaceRequest.FieldMask.ALL_FIELDS);
////        request.setLocationBias(RectangularBounds.newInstance(
////                LatLng.newInstance(lat - radius / 111000f, lng - radius / 111000f),
////                LatLng.newInstance(lat + radius / 111000f, lng + radius / 111000f)
////        ));
//
//        binding = ActivityMapsBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(53.9045,  27.5615);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Minsk"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.getUiSettings().setZoomControlsEnabled(true);
//        mMap.getUiSettings().setZoomGesturesEnabled(true);
//        mMap.getUiSettings().setScrollGesturesEnabled(true);
//    }
//}