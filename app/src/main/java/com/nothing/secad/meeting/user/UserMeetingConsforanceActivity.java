package com.nothing.secad.meeting.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nothing.secad.R;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment;

public class UserMeetingConsforanceActivity extends AppCompatActivity {
    TextView meetingIdText;
    ImageView shareBtn;
    String meetingID,UserID,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_meeting_consforance);
        meetingIdText = findViewById(R.id.meeting_id_textView);
        shareBtn = findViewById(R.id.share_btn);

        meetingID = getIntent().getStringExtra("meeting_ID");
        UserID = getIntent().getStringExtra("user_ID");
        name = getIntent().getStringExtra("name");

        meetingIdText.setText("Meeting ID : "+meetingID);
        addFragment();
    }
    public void addFragment() {
        long appID = 188303307;
        String appSign = "f84742229f2792cd666fe4b2f2de33f9145dd075639d0e75f5b4b803fd787104";



        ZegoUIKitPrebuiltVideoConferenceConfig config = new ZegoUIKitPrebuiltVideoConferenceConfig();
        ZegoUIKitPrebuiltVideoConferenceFragment fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(appID, appSign,UserID, name,meetingID,config);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }

}