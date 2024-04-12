package com.example.Project_android_2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.Project_android_2.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;

public class AccountManagement extends AppCompatActivity {
    private AppCompatImageView appCompatImageView_exit;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Button butonsignin;
    private Button buttongoogle;
    private Button buttonfacebook;
    CallbackManager callbackManager;

    private ShapeableImageView imageView;
    private TextView nameTextView, emailTextView;
    private FrameLayout progressBar;

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Xử lý kết quả ở đây
                    if (result.getResultCode() == RESULT_OK) {
                        progressBar.setVisibility(View.VISIBLE);
                        Intent data = result.getData();
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        try {
                            GoogleSignInAccount account = task.getResult(ApiException.class);
                            firebaseAuthWithGoogle(account.getIdToken());
                            progressBar.setVisibility(View.VISIBLE);
                        } catch (ApiException e) {
                            Log.w("GoogleSignIn", "signInResult:failed code=" + e.getStatusCode());
                            Toast.makeText(AccountManagement.this, "Google sign in failed", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountmanagement);

        setupUI();


        //facebook
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        FacebookSdk.sdkInitialize(AccountManagement.this);

        callbackManager = CallbackManager.Factory.create();

        //google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        boolean logoutRequested = getIntent().getBooleanExtra("logout", false);
        if (logoutRequested) {
            // Thực hiện đăng xuất
            signOut();
        }
        handleclickbutton();
//        setupFaceBook();
        // Nếu đã đăng nhập, chuyển hướng trực tiếp đến trang Home
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }

    }

    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        signInLauncher.launch(signInIntent);

    }

    public void setupUI() {
        appCompatImageView_exit = findViewById(R.id.exit);
        progressBar = findViewById(R.id.progressBar);
        butonsignin = findViewById(R.id.butonsignin);
        buttongoogle = findViewById(R.id.buttongoogle);
        buttonfacebook = findViewById(R.id.buttonfacebook);
    }

    private void setupFaceBook() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(AccountManagement.this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Xử lý khi đăng nhập thành công
                Toast.makeText(AccountManagement.this, "login success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // Xử lý khi người dùng hủy đăng nhập
                Toast.makeText(AccountManagement.this, "login cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // Xử lý khi có lỗi xảy ra
                Toast.makeText(AccountManagement.this, "login error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleclickbutton() {
        //thực hiện click button
        buttongoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        appCompatImageView_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        butonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountManagement.this, SignIn.class);
                startActivity(intent);
            }
        });

        buttonfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
            }
        });
    }

    private void signOut() {
        mAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AccountManagement.this, "Signed out successfully", Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        });
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(AccountManagement.this, "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }


    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(AccountManagement.this, Home.class);
            // Truyền thông tin người dùng đến trang Home
            intent.putExtra("user_name", user.getDisplayName());
            intent.putExtra("user_email", user.getEmail());
            intent.putExtra("user_photo_url", String.valueOf(user.getPhotoUrl()));
            startActivity(intent);
            finish(); // Kết thúc activity hiện tại để ngăn người dùng quay lại màn hình đăng nhập
        }
    }

    //facebook
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}