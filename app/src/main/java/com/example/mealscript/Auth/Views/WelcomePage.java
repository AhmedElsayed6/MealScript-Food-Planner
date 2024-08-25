package com.example.mealscript.Auth.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealscript.Auth.Presenters.WelcomePresenter;
import com.example.mealscript.Auth.Presenters.WelcomePresenterImpl;
import com.example.mealscript.Home.Views.HomeActivity;
import com.example.mealscript.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class WelcomePage extends Fragment implements WelcomePageInterface {
    private VideoView videoViewWelcome;
    private TextView textViewWelcomeLoginbtn, textViewWelcomeSkipBtn;
    private Button btnWelcomeSignupEmail, btnWelcomeGoogle;
    private ImageButton btnWelcomePause;
    private WelcomePresenter presenter;
    private GoogleSignInClient googleSignInClient;
    private ActivityResultLauncher<Intent> signInLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create  A Google Sign in Option with me requesting the EmailAddress
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
        //Create An Activity Launcher to lunch the google Sign in activity
        // requires a contract and a callback method to call on the result
        signInLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        presenter.SignInUpWithGoogle(data);
                    } else {
                        Toast.makeText(this.getContext(), "SignIn Failed", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = WelcomePresenterImpl.getInstance(this);
        videoViewWelcome = view.findViewById(R.id.videoViewWelcome);
        textViewWelcomeLoginbtn = view.findViewById(R.id.textViewWelcomeLoginbtn);
        btnWelcomeSignupEmail = view.findViewById(R.id.btnWelcomeSignupEmail);
        btnWelcomeGoogle = view.findViewById(R.id.btnWelcomeGoogle);
        btnWelcomePause = view.findViewById(R.id.btnWelcomePause);
        textViewWelcomeSkipBtn = view.findViewById(R.id.textViewWelcomeSkipBtn);
        StartVideo(view);


        btnWelcomePause.setOnClickListener((e) -> {
            if (videoViewWelcome.isPlaying()) {
                videoViewWelcome.pause();
                btnWelcomePause.setImageResource(R.drawable.play);
            } else {
                videoViewWelcome.start();
                btnWelcomePause.setImageResource(R.drawable.pause);
            }
        });

        textViewWelcomeLoginbtn.setOnClickListener((e) -> {
            Navigation.findNavController(view).navigate(R.id.action_welcomePage_to_loginPage);
        });


        btnWelcomeSignupEmail.setOnClickListener((e) -> {
            Navigation.findNavController(view).navigate(R.id.action_welcomePage_to_signupPage);
        });

        btnWelcomeGoogle.setOnClickListener((e) -> {
            googleSignInClient.signOut().addOnCompleteListener(task -> {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                signInLauncher.launch(signInIntent);
            });
        });

        textViewWelcomeSkipBtn.setOnClickListener((e) -> {
            ShowGuestDialog();
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

    private void StartVideo(View view) {
        Uri video = Uri.parse("android.resource://" + view.getContext().getPackageName() + "/" + R.raw.welcomescreen);
        videoViewWelcome.setVideoURI(video);
        videoViewWelcome.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoViewWelcome.start();
            }
        });
    }


    private void ShowGuestDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Wait Are you Sure?");
        builder.setMessage("You'll miss out on personalized content and saving our meals recipes");
        builder.setPositiveButton("YES,I'M SURE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                presenter.setUpGuestMode();
                Intent toHome = new Intent(getActivity(), HomeActivity.class);
                startActivity(toHome);
                getActivity().finish();
            }
        });
        builder.setNegativeButton("NO,GO BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    public void onGoogleFail(String message) {
        Toast.makeText(this.getContext(), message , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleSuccess() {
        Intent toHome = new Intent(getActivity(), HomeActivity.class);
        startActivity(toHome);
        getActivity().finish();
    }
}