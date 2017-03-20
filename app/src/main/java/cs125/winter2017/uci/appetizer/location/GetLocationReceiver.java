package cs125.winter2017.uci.appetizer.location;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

/**
 * Created by kanirolf on 3/19/2017.
 */

public class GetLocationReceiver extends ResultReceiver {

    private Receiver receiver;

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public GetLocationReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle data){
        if (receiver != null)
            receiver.onReceiverResult(resultCode, data);
    }

    public interface Receiver {
        void onReceiverResult(int resultCode, Bundle data);
    }
}
