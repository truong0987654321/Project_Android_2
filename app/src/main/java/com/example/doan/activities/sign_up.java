package com.example.doan.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.utils.IdGeneratorHelper;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.doan.R;
import com.example.doan.models.user;
import com.example.doan.utils.NetworkUtils;
import com.example.doan.utils.PasswordHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.makeramen.roundedimageview.RoundedImageView;

public class sign_up extends AppCompatActivity {

    private AppCompatImageView appCompatImageView_back;
    private EditText editText_exemail;
    private EditText editText_exusername;
    private EditText editText_expassword;
    private EditText editText_exConfirmpassword;
    private TextView textAdImage;
    private Button buttonsignup;
    private RoundedImageView roundedImageView_imageProfile;
    private Uri uri;
    private StorageReference storageReference;
    private StorageTask storageTask;
    private DatabaseReference databaseReference;
    private FrameLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        appCompatImageView_back = findViewById(R.id.back);
        editText_exemail = findViewById(R.id.exemail);
        editText_exusername = findViewById(R.id.exusername);
        editText_expassword = findViewById(R.id.expassword);
        editText_exConfirmpassword = findViewById(R.id.exConfirmpassword);
        roundedImageView_imageProfile = findViewById(R.id.imageProfile);
        textAdImage = findViewById(R.id.textAdImage);
        buttonsignup = findViewById(R.id.buttonsignup);
        progressBar = findViewById(R.id.progressBar);

        editText_exemail.addTextChangedListener(textWatcher);
        editText_expassword.addTextChangedListener(textWatcher);
        editText_exConfirmpassword.addTextChangedListener(textWatcher);

        storageReference = FirebaseStorage.getInstance().getReference("USER");
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        appCompatImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK) {
                            Intent dataI = o.getData();
                            uri = dataI.getData();
                            roundedImageView_imageProfile.setImageURI(uri);
                        } else {
                            Toast.makeText(sign_up.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        roundedImageView_imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_photo = new Intent(Intent.ACTION_PICK);
                intent_photo.setType("image/*");
                intentActivityResultLauncher.launch(intent_photo);
                textAdImage.setVisibility(View.GONE);
            }
        });
        if (uri ==null){
            textAdImage.setVisibility(View.VISIBLE);
        }
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!NetworkUtils.isNetworkAvailable(sign_up.this)) {
                    Toast.makeText(sign_up.this, "No internet connection. Please check your network settings.", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    if (isValidSignInDetails()) {

                        UploadFile();

                    }

                }


            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void UploadFile() {
        if (uri != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
            storageTask = fileReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Lấy đường dẫn download của tệp đã tải lên
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri downloadUri) {
                            Toast.makeText(sign_up.this, "Sign up successful", Toast.LENGTH_SHORT).show();


                            String uploadId = databaseReference.push().getKey();
                            int id = IdGeneratorHelper.generateAutomaticId();
                            String avatarUrl = downloadUri.toString();
                            String email = editText_exemail.getText().toString();
                            String password = editText_expassword.getText().toString();
                            String hashPassword = PasswordHelper.hashPassword(password);
                            String username = editText_exusername.getText().toString();

                            user user = new user(id, avatarUrl, email, hashPassword, username, uploadId);
                            databaseReference.child(uploadId).setValue(user);
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(sign_up.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            updateButtonState();
        }
    };

    // Hàm kiểm tra và cập nhật trạng thái của nút
    private void updateButtonState() {
        String email = editText_exemail.getText().toString();
        String password = editText_expassword.getText().toString();
        String confirmPassword = editText_exConfirmpassword.getText().toString();
        // Kiểm tra các điều kiện để cập nhật trạng thái nút
        boolean isButtonEnabled = !email.isEmpty() &&
                !password.isEmpty() && !confirmPassword.isEmpty() &&
                uri != null;
        // Cập nhật trạng thái và mức độ mờ của nút
        buttonsignup.setEnabled(isButtonEnabled);
        buttonsignup.setAlpha(isButtonEnabled ? 1.0f : 0.5f);
    }

    private Boolean isValidSignInDetails() {
        String username = editText_exusername.getText().toString().trim();
        String email = editText_exemail.getText().toString();
        String password = editText_expassword.getText().toString();
        String Confirmpassword = editText_exConfirmpassword.getText().toString();
        if (!isValidEmail(email)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return false;
        } else if (username.length() <= 6) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(sign_up.this, "Username must be longer than 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isUsernameValid(username)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(sign_up.this, "Invalid characters in username", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(Confirmpassword)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(sign_up.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() < 8 || !isStrongPassword(password)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(sign_up.this, "Password must be at least 8 characters long and contain at least one special character, one digit, and one uppercase letter.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }

    private boolean isUsernameValid(String username) {
        String regex = "^[a-zA-Z0-9]+$"; // Chỉ chấp nhận ký tự chữ cái và số
        return username.matches(regex);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isStrongPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
        return password.matches(regex);
    }
}