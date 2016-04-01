package com.pro.gen.android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.pro.gen.App;
import com.pro.gen.managers.DatabaseManager;
import com.pro.gen.utils.LogUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.text.DateFormat.getDateInstance;
import static java.text.DateFormat.getTimeInstance;

public class AndroidLauncher extends AndroidApplication {
    public static final String TAG = "MQP";
    private static final String AUTH_PENDING = "auth_state_pending";
    private boolean finished = false;

    public static GoogleApiClient mClient = null;
    private static boolean authInProgress = false;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Log.d(TAG, "SOMETHING HAPPENED");

        if (savedInstanceState != null) {
            authInProgress = savedInstanceState.getBoolean(AUTH_PENDING);
        }

        buildFitnessClient();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new App(), config);
	}

    private void buildFitnessClient() {
        // Create the Google API Client
        mClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addConnectionCallbacks(
                        new GoogleApiClient.ConnectionCallbacks() {
                            @Override
                            public void onConnected(Bundle bundle) {
                                Log.i(TAG, "Connected!!!");
                                // Now you can make calls to the Fitness APIs.  What to do?
                                // Look at some data!!
                                new InsertAndVerifyDataTask().execute();
                            }

                            @Override
                            public void onConnectionSuspended(int i) {
                                // If your connection to the sensor gets lost at some point,
                                // you'll be able to determine the reason and react to it here.
                                if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
                                    Log.i(TAG, "Connection lost.  Cause: Network Lost.");
                                } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
                                    Log.i(TAG, "Connection lost.  Reason: Service Disconnected");
                                }
                            }
                        }
                )
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        Log.i(TAG, "Google Play services connection failed. Cause: " +
                                connectionResult.toString());
                    }
                })
                .build();
    }


    private class InsertAndVerifyDataTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {

            DataReadRequest readRequest = queryFitnessData();

            // [START read_dataset]
            // Invoke the History API to fetch the data with the query and await the result of
            // the read request.
            DataReadResult dataReadResult =
                    Fitness.HistoryApi.readData(mClient, readRequest).await(1, TimeUnit.MINUTES);
            // [END read_dataset]

            // For the sake of the sample, we'll print the data so we can see what we just added.
            // In general, logging fitness information should be avoided for privacy reasons.
            printData(dataReadResult);

            return null;
        }
    }


    /**
     * Return a {@link DataReadRequest} for all step count changes in the past week.
     */
    public static DataReadRequest queryFitnessData() {
        // [START build_read_data_request]
        // Setting a start and end date using a range of 1 week before this moment.
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        long startTime = cal.getTimeInMillis();

        java.text.DateFormat dateFormat = getDateInstance();
        Log.i(TAG, "Range Start: " + dateFormat.format(startTime));
        Log.i(TAG, "Range End: " + dateFormat.format(endTime));

        DataReadRequest readRequest = new DataReadRequest.Builder()
                // The data request can specify multiple data types to return, effectively
                // combining multiple data queries into one call.
                // In this example, it's very unlikely that the request is for several hundred
                // datapoints each consisting of a few steps and a timestamp.  The more likely
                // scenario is wanting to see how many steps were walked per day, for 7 days.
                .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
                        // Analogous to a "Group By" in SQL, defines how data should be aggregated.
                        // bucketByTime allows for a time span, whereas bucketBySession would allow
                        // bucketing by "sessions", which would need to be defined in code.
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build();
        // [END build_read_data_request]

        return readRequest;
    }




    public static void printData(DataReadResult dataReadResult) {
        // [START parse_read_data_result]
        // If the DataReadRequest object specified aggregated data, dataReadResult will be returned
        // as buckets containing DataSets, instead of just DataSets.
        if (dataReadResult.getBuckets().size() > 0) {
            Log.i(TAG, "Number of returned buckets of DataSets is: "
                    + dataReadResult.getBuckets().size());
            for (Bucket bucket : dataReadResult.getBuckets()) {
                List<DataSet> dataSets = bucket.getDataSets();
                for (DataSet dataSet : dataSets) {
                    dumpDataSet(dataSet);
                }
            }
        }
    }

    private static void dumpDataSet(DataSet dataSet) {
        //com.pro.gen.common.logger.Log.i(TAG, "Data returned for Data type: " + dataSet.getDataType().getName());
        DateFormat dateFormat = getTimeInstance();

        for (DataPoint dp : dataSet.getDataPoints()) {
            //com.pro.gen.common.logger.Log.i(TAG, "Data point:");
            //com.pro.gen.common.logger.Log.i(TAG, "\tType: " + dp.getDataType().getName());
            Log.i(TAG, "\tdate" + new SimpleDateFormat("yyyy-MM-dd H:m:s").format(new Date(dp.getStartTime(TimeUnit.MILLISECONDS))));
            Log.i(TAG, "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            Log.i(TAG, "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)));
            for(Field field : dp.getDataType().getFields()) {
                Log.i(TAG, "\tField: " + field.getName() +
                        " Value: " + dp.getValue(field));
            }
        }
    }




    @Override
    protected void onResume() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "Jim");
        DatabaseManager.getInstance().makeDBCall(DatabaseManager.OPENED, params, null);
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtils.Log("ANDROID PAUSE");
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "Jim");
        DatabaseManager.getInstance().makeDBCall(DatabaseManager.CLOSED, params, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                LogUtils.Log("FINISHED CLOSING");
                finished = true;
            }

            @Override
            public void failed(Throwable t) {

            }

            @Override
            public void cancelled() {

            }
        });
        while (!finished){
            //LogUtils.com.pro.gen.common.logger.Log(finished);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
