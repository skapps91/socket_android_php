package com.check.androidsocketapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.NotYetConnectedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private WebSocketClient mWebSocketClient;
    Button btn;
    TextView msgTxt;

    //private String URL = "ws://192.168.1.8:9000/chat_test/demo1/server.php";
    private String URL = "ws://192.168.1.13:9000/chat_test/demo1/server.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        msgTxt = findViewById(R.id.msgTxt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //connectToSocket();

                if (mWebSocketClient == null) {
                    connectToSocket();
                } else {
                    sendHitToAdmin();
                }

            }
        });

        connectToSocket();


    }


    private void connectToSocket() {
        URI uri;
        try {
            uri = new URI(URL);


        } catch (URISyntaxException e) {

            Log.d("Websocket", "connectToSocket: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri /*, new Draft_17()*/) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {

                Log.d("Websocket", "Opened");

                msgTxt.setText(msgTxt.getText() + "\nHTTP Status: " + serverHandshake.getHttpStatus()+"\nHTTP Msg: "+serverHandshake.getHttpStatusMessage() );


            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        msgTxt.setText(msgTxt.getText() + "\n" + message);
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {

                Log.d("Websocket", "Closed " + s);

                msgTxt.setText(msgTxt.getText() + "\nString: " +s+"\nBoolean "+b  );

            }

            @Override
            public void onError(Exception e) {

                Log.d("Websocket", "Error " + e.getMessage());

            }
        };
        mWebSocketClient.connect();


        //sendHitToAdmin();


    }


    private void sendHitToAdmin() {
        try {

/*
            ArrayList<HashMap<String, String>> itemCodeList = new ArrayList<>();
            HashMap map_code = new HashMap();

            map_code.put("name", "saad - Andr");
            map_code.put("message", "hello world");

            itemCodeList.add(map_code);

            String itemCodeJSON = new Gson().toJson(itemCodeList);
*/

            String msg = "{\"message\": \""+"HELLO WORLD"+"\", \"name\": \""+"SAAD ANDR"+"\", \"color\": \"#15E25F\"}";

            Log.d("Websocket", "msgSent:\n" + msg );

            mWebSocketClient.send( msg );

            //mWebSocketClient.send("Hello World" );

        } catch (NotYetConnectedException e) {
            Log.d("Websocket", "sendHitToAdmin ERROR: \n" + e.getMessage());
            e.printStackTrace();
        }

    }
}

