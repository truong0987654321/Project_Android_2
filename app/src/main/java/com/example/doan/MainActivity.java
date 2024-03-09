package com.example.doan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.doan.utils.NetworkUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button btnchonAnh;
    private Button btnUpload;
    private TextView tv_show;
    private ImageView img;
    private ProgressBar progressBar;

    private Uri uri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private StorageTask storageTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!NetworkUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, "No network available", Toast.LENGTH_SHORT).show();
        }

//        btnchonAnh = findViewById(R.id.button_chonAnh);
//        btnUpload = findViewById(R.id.button_upload);
//        tv_show = findViewById(R.id.textview);
//        img = findViewById(R.id.image);
//        progressBar = findViewById(R.id.progress_circular);
//
//        storageReference = FirebaseStorage.getInstance().getReference("USER");
//        databaseReference = FirebaseDatabase.getInstance().getReference("user");
//        btnchonAnh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openFileChooser();
//            }
//        });
//
//        btnUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(storageTask != null &&storageTask.isInProgress()) {
//                    Toast.makeText(MainActivity.this,"upload is progress",Toast.LENGTH_SHORT).show();
//                }else {
//                    UploadFile();
//
//                }
//            }
//        });
//        tv_show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }
//
//    private void openFileChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            uri = data.getData();
//
//            // Tạo một ContentResolver để đọc dữ liệu từ Uri
//            ContentResolver contentResolver = getContentResolver();
//
//            try {
//                // Sử dụng ContentResolver để mở đầu vào dữ liệu từ Uri
//                InputStream inputStream = contentResolver.openInputStream(uri);
//
//                // Chuyển đổi InputStream thành Bitmap (hoặc sử dụng thư viện hỗ trợ như Picasso, Glide)
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//
//                // Hiển thị hình ảnh hoặc làm gì đó với nó
//                img.setImageBitmap(bitmap);
//
//                // Đóng InputStream sau khi sử dụng
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private String getFileExtension(Uri uri) {
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
//
//    private void UploadFile() {
//        if (uri != null) {
//            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
//            storageTask = fileReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            progressBar.setProgress(0);
//                        }
//                    }, 500);
//                    // Lấy đường dẫn download của tệp đã tải lên
//                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri downloadUri) {
//                            // Thực hiện các hành động sau khi lấy đường dẫn thành công
//                            Toast.makeText(MainActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
//
//                            // Sử dụng đường dẫn để tạo đối tượng upload và đẩy lên database
//                            String uploadId = databaseReference.push().getKey();
//                            upload upload = new upload(uploadId,downloadUri.toString());
//                            databaseReference.child(uploadId).setValue(upload);
//                        }
//                    });
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                    Double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
//                    progressBar.setProgress(progress.intValue());
//                }
//            });
//        } else {
//            Toast.makeText(this, "no file selected", Toast.LENGTH_SHORT).show();
//        }
    }

}