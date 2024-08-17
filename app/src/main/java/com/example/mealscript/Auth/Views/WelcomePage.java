package com.example.mealscript.Auth.Views;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mealscript.Auth.Presenters.WelcomePresenter;
import com.example.mealscript.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class WelcomePage extends Fragment implements  WelcomePageInterface {
    private static final String TAG = "GOOGLESUCKS";
    VideoView videoViewWelcome;
    TextView textViewLoginbtn;
    Button btnWelcomeSignupEmail, btnWelcomeGoogle;
    ImageButton btnWelcomePause;
    WelcomePresenter presenter;
    private GoogleSignInClient googleSignInClient;
    private ActivityResultLauncher<Intent> signInLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
        signInLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.i(TAG, "onCreate: Done ");
                        Intent data = result.getData();
                        handleSignInResult(data);
                    } else {
                        Log.e(TAG, "Sign-in result failed with result code: " + result.getResultCode());
                    }
                }
        );
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
          // presenter.SignInUpWithGoogle();
            googleSignInClient.signOut().addOnCompleteListener(task -> {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                signInLauncher.launch(signInIntent);
            });
        });
        
        
    }
    private void handleSignInResult(Intent data) {
        GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Get the GoogleSignInAccount from the task result
                        GoogleSignInAccount account = task.getResult();
                        if (account != null) {
                            // Get the ID token from the GoogleSignInAccount
                            String idToken = account.getIdToken();

                            // Authenticate with Firebase using the ID token
                            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
                            FirebaseAuth.getInstance().signInWithCredential(credential)
                                    .addOnCompleteListener(authTask -> {
                                        if (authTask.isSuccessful()) {
                                            // Sign-in successful, handle the Firebase user
                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                            Log.i(TAG, "handleSignInResult: Firebase sign-in successful");
                                            // Proceed with your logic
                                        } else {
                                            // Sign-in failed, handle the error
                                            Log.e(TAG, "handleSignInResult: Firebase sign-in failed", authTask.getException());
                                        }
                                    });
                        } else {
                            Log.e(TAG, "handleSignInResult: GoogleSignInAccount is null");
                        }
                    } else {
                        // Handle sign-in failure
                        Log.e(TAG, "handleSignInResult: Google sign-in failed", task.getException());
                    }
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