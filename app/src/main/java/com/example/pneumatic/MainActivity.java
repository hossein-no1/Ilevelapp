package com.example.pneumatic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    Button btnAllUp, btnAllDown, btnBothForwardUp, btnBothForwardDown, btnRightForwardUp, btnRightForwardDown, btnLeftForwardUp, btnLeftForwardDown, btnBothBackUp, btnBothBackDown, btnRightBackUp, btnRightBackDown, btnLeftBackUp, btnLeftBackDown, btnNormal, btnSetIp, btnCheckConnec, btnCancel;
    Toolbar toolBarMain;
    MenuItem itemWifi;

    View parentLayout;

    private static String IP_GLOBAL = "192.168.4.1";
    private static int PORT_GLOBAL = 9700;

    Socket socket;
    private PrintWriter output;
    Thread mainThread = null;
    Boolean connect = false;
    Boolean firstConnect;

    WifiManager wifiManager;


    private final static String allOff = "0";
    private final static String btAllUp = "1";
    private final static String btAllDown = "2";
    private final static String btBothForwardUp = "3";
    private final static String btBothForwardDown = "4";
    private final static String btBothBackUp = "5";
    private final static String btBothBackDown = "6";
    private final static String btRightForwardUp = "7";
    private final static String btRightForwardDown = "8";
    private final static String btLeftForwardUp = "9";
    private final static String btLeftForwardDown = "10";
    private final static String btRightBackUp = "11";
    private final static String btRightBackDown = "12";
    private final static String btLeftBackUp = "13";
    private final static String btLeftBackDown = "14";


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_main_activity, menu);
        itemWifi = (MenuItem) menu.findItem(R.id.itemWifiMenuMainMA);
        try {
            if (connect == true)
                statConnecting(true);
        } catch (Exception e) {
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find view by ids all elements
        findViewByIds();

        //Load tool bar
        setUpToolBar();


        //Click all buttons
        clickButton();

        //Start to work main threade for set up connecion


        try {

            mainThread = new Thread(new setUpMainThread());
            mainThread.start();


        } catch (Exception e) {
        }


    }

    private void clickButton() {


        btnAllUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btAllUp, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, R.string.nothing_yet, Toast.LENGTH_SHORT).show();
            }
        });


        btnAllDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btAllDown, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnBothForwardUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btBothForwardUp, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnBothForwardDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btBothForwardDown, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnBothBackUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btBothBackUp, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnBothBackDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btBothBackDown, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnLeftForwardUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btLeftForwardUp, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnLeftForwardDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btLeftForwardDown, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnRightForwardUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btRightForwardUp, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnRightForwardDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btRightForwardDown, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnLeftBackUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btLeftBackUp, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnLeftBackDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btLeftBackDown, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnRightBackUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btRightBackUp, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        btnRightBackDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage(btRightBackDown, false);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    sendMessage(allOff, false);

                return false;
            }
        });


        toolBarMain.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int resId = item.getItemId();

                if (resId == R.id.itemAbutUsMenuMainMA) {
                    startActivity(new Intent(MainActivity.this, AboutUs.class));
                } else if (resId == R.id.itemExitMenuMainMA) {
                    finish();
                } else if (resId == R.id.itemSettingMenuMainMA) {
                    popUpSettingItem();
                } else if (resId == R.id.itemWifiMenuMainMA) {

                    try {

                        firstConnect = socket.isConnected();

                        if (!firstConnect && connect) {
                            try {
                                mainThread = new Thread(new setUpMainThread());
                                mainThread.start();
                                loadingDialogForConnect("Please waiting...",2000);

                            } catch (UnknownError e) {
                            }
                        } else {
                            Toast.makeText(context, R.string.restart_app, Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        try {

                            mainThread = new Thread(new setUpMainThread());
                            mainThread.start();
                            loadingDialogForConnect("Please waiting...",2000);

                        } catch (UnknownError newE) {
                        }
                    }

                }

                return false;
            }
        });


    }

    private void findViewByIds() {
        //Three big button left screen
        btnAllUp = (Button) findViewById(R.id.btnAllUpMA);
        btnAllDown = (Button) findViewById(R.id.btnAllDownMA);
        btnNormal = (Button) findViewById(R.id.btnAllNormalMA);

        //Four big button right screen
        btnBothForwardUp = (Button) findViewById(R.id.btnBothForwardUpMA);
        btnBothForwardDown = (Button) findViewById(R.id.btnBothForwardDownMA);
        btnBothBackUp = (Button) findViewById(R.id.btnBothBackUpMA);
        btnBothBackDown = (Button) findViewById(R.id.btnBothBackDownMA);

        //twelve small button right screen
        btnRightForwardUp = (Button) findViewById(R.id.btnRightForwardUpMA);
        btnRightForwardDown = (Button) findViewById(R.id.btnRightForwardDownMA);
        btnLeftForwardUp = (Button) findViewById(R.id.btnLeftForwardUpMA);
        btnLeftForwardDown = (Button) findViewById(R.id.btnLeftForwardDownMA);
        btnRightBackUp = (Button) findViewById(R.id.btnRightBackUpMA);
        btnRightBackDown = (Button) findViewById(R.id.btnRightBackDownMA);
        btnLeftBackUp = (Button) findViewById(R.id.btnLeftBackUpMA);
        btnLeftBackDown = (Button) findViewById(R.id.btnLeftBackDownMA);


        //tool bar
        toolBarMain = (Toolbar) findViewById(R.id.toolBarMainMA);


        //wifi manager
        wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);


    }

    private void setUpToolBar() {
        setSupportActionBar(toolBarMain);
        toolBarMain.inflateMenu(R.menu.menu_main_main_activity);
        toolBarMain.getOverflowIcon().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
    }

    private void sendMessage(final String msg, Boolean userPass) {


        try {

            if (connect == true) {

                if (userPass)
                    new Thread(new sendData(msg)).start();
                else {
                    new Thread(new sendData(msg + "-")).start();
                }

            } else
                Toast.makeText(context, R.string.no_connect, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
        }
        ;


    }

    class setUpMainThread implements Runnable {

        @Override
        public void run() {


            try {
                socket = new Socket(IP_GLOBAL, PORT_GLOBAL);
                output = new PrintWriter(socket.getOutputStream(), true);

                connect = true;

            } catch (Exception e) {
                Log.e("hossein", e + "");
                connect = false;
            }
        }
    }

    class sendData implements Runnable {
        private String message;

        sendData(String message) {
            this.message = message;
        }

        @Override
        public void run() {

            try {
                output.write(message);
                output.flush();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        connect = true;
                        statConnecting(true);
                    }
                });

            } catch (Exception e) {
                connect = false;
                statConnecting(false);
            }


        }

    }

    public void popUpSettingItem() {

        final AlertDialog.Builder alBuilder = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.layout_setting_popup, null, false);

        alBuilder.setView(v);

        Button btSetPassword, btResetPassword;
        final EditText edtPassword, edtUserName;
        final TextView txtStateLenghUserName, txtStateLenghPassword;

        btSetPassword = (Button) v.findViewById(R.id.btnSetPasswordSL);
        btResetPassword = (Button) v.findViewById(R.id.btnResetPasswordSL);
        edtPassword = (EditText) v.findViewById(R.id.edtPasswordSL);
        edtUserName = (EditText) v.findViewById(R.id.edtUserNameSL);
        txtStateLenghUserName = (TextView) v.findViewById(R.id.txtStatLenghUserNameSL);
        txtStateLenghPassword = (TextView) v.findViewById(R.id.txtStatLenghPasswordSL);

        final AlertDialog popUpSettingWifi = alBuilder.create();

        btSetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edtUserName.getText().toString().equals("")) {
                    Toast.makeText(context, R.string.set_userName, Toast.LENGTH_SHORT).show();
                } else if (edtPassword.getText().toString().equals("")) {
                    Toast.makeText(context, R.string.set_password, Toast.LENGTH_SHORT).show();
                } else if (edtPassword.getText().length() < 8) {
                    Toast.makeText(context, R.string.short_password, Toast.LENGTH_SHORT).show();
                } else if (edtUserName.getText().length() < 4) {
                    Toast.makeText(context, R.string.short_userName, Toast.LENGTH_SHORT).show();
                } else {
                    if (connect == true) {
                        String userPas = edtUserName.getText().toString().trim() + "$" + edtPassword.getText().toString().trim() + "-";
                        sendMessage(userPas, true);
                        popUpSettingWifi.dismiss();
                    } else {
                        Toast.makeText(context, R.string.no_connect, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        btResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (connect == true) {

                    final AlertDialog alSure;
                    final AlertDialog.Builder alBuilder = new AlertDialog.Builder(context);
                    alBuilder.setCancelable(false);
                    alBuilder.setTitle("Danger");
                    alBuilder.setMessage("Are you sure for reset WIFI ?");
                    alBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sendMessage("rs-", true);
                            dialog.dismiss();
                            popUpSettingWifi.dismiss();
                        }
                    });

                    alSure = alBuilder.create();
                    alSure.show();

                } else {
                    Toast.makeText(context, R.string.no_connect, Toast.LENGTH_SHORT).show();
                }

            }
        });

        edtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtStateLenghUserName.setText(s.length() + "/25");
                if (s.length() < 4)
                    txtStateLenghUserName.setTextColor(getResources().getColor(R.color.red));
                else
                    txtStateLenghUserName.setTextColor(getResources().getColor(R.color.yellow));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtStateLenghPassword.setText(s.length() + "/50");
                if (s.length() < 8)
                    txtStateLenghPassword.setTextColor(getResources().getColor(R.color.red));
                else
                    txtStateLenghPassword.setTextColor(getResources().getColor(R.color.yellow));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        popUpSettingWifi.show();


    }

    private void statConnecting(Boolean stat) {
        if (stat)
            itemWifi.setIcon(getResources().getDrawable(R.drawable.icon_wifi));
        else
            itemWifi.setIcon(getResources().getDrawable(R.drawable.icon_no_wifi));
    }

    private void loadingDialogForConnect(final String msg, int duration)
    {
        final ProgressDialog pbLoading = new ProgressDialog(context);
        pbLoading.setCancelable(false);
        pbLoading.setMessage(msg);
        pbLoading.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                pbLoading.dismiss();
                if (connect)
                    statConnecting(true);

            }
        },duration);

    }


}