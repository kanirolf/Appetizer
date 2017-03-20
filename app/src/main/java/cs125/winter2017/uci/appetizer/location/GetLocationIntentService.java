package cs125.winter2017.uci.appetizer.location;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import cs125.winter2017.uci.appetizer.R;

public class GetLocationIntentService extends IntentService {

    private static final String SERVICE_TAG = "LOC_SERV";

    public GetLocationIntentService(){
        super("");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GetLocationIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null)
            return;

        ResultReceiver resultReceiver = intent.getParcelableExtra(GetLocationConstants.RECEIVER);
        Location location = intent.getParcelableExtra(GetLocationConstants.LOCATION);
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());


        List<Address> addresses = null;
        String errorMsg = null;

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),
                    GetLocationConstants.LOCATION_FETCH_DEFAULT);
        } catch (IOException e) {
            errorMsg = getString(R.string.get_location_service_failed);
            Log.e(SERVICE_TAG, errorMsg);
        } catch (IllegalArgumentException e){
            errorMsg = getString(R.string.get_location_invalid_location);
            Log.e(SERVICE_TAG, errorMsg);
        }

        if (addresses == null || addresses.isEmpty()){
            if (errorMsg == null){
                errorMsg = getString(R.string.get_location_no_addresses);
                Log.e(SERVICE_TAG, errorMsg);
            }
            deliverResult(resultReceiver, GetLocationConstants.FETCH_FAILURE, null);
        } else {
            Log.i(SERVICE_TAG, getString(R.string.get_location_found));
            deliverResult(resultReceiver, GetLocationConstants.FETCH_SUCCESS,
                    addresses.toArray(new Address[]{}));
        }
    }

    private static void deliverResult(ResultReceiver receiver, int resultCode,
                                      Address[] addresses){

        if (resultCode == GetLocationConstants.FETCH_FAILURE){
            receiver.send(resultCode, null);
        } else {
            Bundle data = new Bundle();
            data.putParcelableArray(GetLocationConstants.RESULTS, addresses);
            receiver.send(resultCode, data);
        }
    }
}


