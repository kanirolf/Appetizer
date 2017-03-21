package cs125.winter2017.uci.appetizer.location;

import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cs125.winter2017.uci.appetizer.R;

import static android.content.pm.PackageManager.PERMISSION_DENIED;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class GetLocationFragment extends Fragment implements
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        GetLocationReceiver.Receiver, OnMapReadyCallback {

    private static final float DEFAULT_ZOOM = 15f;

    private MapView locationMap;
    private TextView locationMessage;

    private GoogleApiClient googleApiClient;

    private Location location;
    private Address address;

    private OnLocationAcquireListener acquireListener;

    public GetLocationFragment(){
        location = null;
        address = null;

        acquireListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_get_location, container);

        locationMap = (MapView) view.findViewById(R.id.get_location_map);
        locationMap.onCreate(savedInstanceState);
        locationMap.setVisibility(View.GONE);

        locationMessage = (TextView) view.findViewById(R.id.get_location_message);
        locationMessage.setVisibility(View.VISIBLE);
        locationMessage.setText(R.string.get_location_getting_perms);

        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        int locationPermissions = ActivityCompat.checkSelfPermission(
                getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION);
        if (locationPermissions != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else
            connectGoogleAPI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        locationMap.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        locationMap.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        locationMap.onDestroy();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        locationMap.onLowMemory();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                    @NonNull int[] grantResults) {
        if (grantResults.length == 0 || grantResults[0] == PERMISSION_DENIED)
            locationMessage.setText(R.string.get_location_getting_perms_failed);
        else if (grantResults[0] == PERMISSION_GRANTED)
            connectGoogleAPI();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationMessage.setText(R.string.get_location_getting_location);
        //noinspection MissingPermission
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (location == null){
            locationMessage.setText(R.string.get_location_getting_location_failed);
            return;
        }

        Intent resolveLocationIntent = new Intent(getActivity(), GetLocationIntentService.class);
        GetLocationReceiver receiver = new GetLocationReceiver(new Handler());
        receiver.setReceiver(this);

        resolveLocationIntent.putExtra(GetLocationConstants.LOCATION, location);
        resolveLocationIntent.putExtra(GetLocationConstants.RECEIVER, receiver);

        getActivity().startService(resolveLocationIntent);
        locationMap.getMapAsync(this);

        locationMessage.setVisibility(View.GONE);
        locationMap.setVisibility(View.VISIBLE);

        acquireListener.onLocationAcquire(this);
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        locationMessage.setText(R.string.get_location_connecting_API_failed);
    }

    @Override
    public void onReceiverResult(int resultCode, Bundle data) {
        if (resultCode == GetLocationConstants.FETCH_FAILURE)
            return;

        final Address[] addresses =
                (Address[]) data.getParcelableArray(GetLocationConstants.RESULTS);
        address = addresses[0];

        if (acquireListener != null)
            acquireListener.onLocationAcquire(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLngPos = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLngPos);

        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLngPos));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(DEFAULT_ZOOM));
    }

    @Nullable
    public Location getLocation(){
        return location;
    }

    @Nullable
    public Address getAddress(){
        return address;
    }

    public String getLocationString(){
        String locationString = "";

        if (address != null) {
            for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                locationString += address.getAddressLine(i) + " ";
        } else if (location != null)
            locationString = location.toString();
        else
            locationString = "No location.";

        return locationString;
    }

    public void setLocationAcquireListener(OnLocationAcquireListener acquireListener){
        this.acquireListener = acquireListener;
    }

    private void connectGoogleAPI(){
        locationMessage.setText(R.string.get_location_connecting_API);
        googleApiClient.connect();
    }

    public interface OnLocationAcquireListener {
        void onLocationAcquire(GetLocationFragment locationFragment);
    }

}
