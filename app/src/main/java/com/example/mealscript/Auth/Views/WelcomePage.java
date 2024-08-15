package com.example.mealscript.Auth.Views;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mealscript.Auth.Presenters.WelcomePresenter;
import com.example.mealscript.R;

public class WelcomePage extends Fragment implements  WelcomePageInterface {
    VideoView videoViewWelcome;
    TextView textViewLoginbtn;
    Button btnWelcomeSignupEmail, btnWelcomeGoogle;
    ImageButton btnWelcomePause;
    WelcomePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = WelcomePresenter.getInstance(this);
        videoViewWelcome = view.findViewById(R.id.videoViewWelcome);
        textViewLoginbtn = view.findViewById(R.id.textViewLoginbtn);
        btnWelcomeSignupEmail = view.findViewById(R.id.btnWelcomeSignupEmail);
        btnWelcomeGoogle = view.findViewById(R.id.btnWelcomeGoogle);
        btnWelcomePause = view.findViewById(R.id.btnWelcomePause);
        StartVideo(view);


        btnWelcomePause.setOnClickListener((e)->{
            if(videoViewWelcome.isPlaying()){
                videoViewWelcome.pause();
                btnWelcomePause.setImageResource(R.drawable.play);
            }
            else{
                videoViewWelcome.start();
                btnWelcomePause.setImageResource(R.drawable.pause);
            }
        });

        textViewLoginbtn.setOnClickListener((e)->{
        Navigation.findNavController(view).navigate(R.id.action_welcomePage_to_loginPage);
        });


        btnWelcomeSignupEmail.setOnClickListener((e)->{
            Navigation.findNavController(view).navigate(R.id.action_welcomePage_to_signupPage);
        });

        btnWelcomeGoogle.setOnClickListener((e)->{
           presenter.SignInUpWithGoogle();
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        videoViewWelcome.pause();
        btnWelcomePause.setImageResource(R.drawable.play);
    }

    @Override
    public void onResume() {
        super.onResume();
        videoViewWelcome.resume();
        btnWelcomePause.setImageResource(R.drawable.pause);
    }

    @Override
    public void onStop() {
        super.onStop();
        videoViewWelcome.stopPlayback();
    }

    private  void StartVideo(View view){
        Uri video = Uri.parse("android.resource://" + view.getContext().getPackageName() + "/" + R.raw.welcomescreen); // Replace with your video file name
        videoViewWelcome.setVideoURI(video);
        videoViewWelcome.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true); // Set the video to loop
                videoViewWelcome.start();
            }
        });
    }


    @Override
    public void showGoogleLoginError() {

    }

}