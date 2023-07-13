//package com.example.ourrecipe;
//
//import android.content.Context;
//import android.content.Intent;
//import android.media.Image;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.HorizontalScrollView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import androidx.activity.result.ActivityResult;
//import androidx.activity.result.ActivityResultCallback;
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EditGallery extends AppCompatActivity {
//    ArrayList<Integer> newImageId = new ArrayList<>();
//    ArrayList<Uri> newImageURI = new ArrayList<>();
//    ImageView close_button;
//    Button add_image;
//    Button remove_image;
//    HorizontalScrollView view_gallery_bottom;
//    LinearLayout layout_edit_gallery_bottom;
//    Button save;
//    private ActivityResultLauncher<Intent> openGalleryResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == RESULT_OK){
//                        Intent getGalleryImage = result.getData();
//                        ImageView addIMG = new ImageView(EditGallery.this);
//                        addIMG.setImageURI(getGalleryImage.getData());
//                        addIMG.setId(View.generateViewId());
//                        newImageId.add(addIMG.getId());
//                        newImageURI.add(getGalleryImage.getData());
//                        addView(addIMG,300, 300);
//                    }
//                }
//            });
//
//    public void addView(ImageView imageView, int width, int height) {
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
//        layoutParams.setMargins(10,10,10,0);
//        imageView.setLayoutParams(layoutParams);
//        layout_edit_gallery_bottom.addView(imageView,width,height);
//    }
//
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.edit_gallery_bottom);
//
//        close_button = findViewById(R.id.imgV_close_edit_gallery_bottom);
//        add_image = findViewById(R.id.btn_add_image_edit_gallery_bottom);
//        remove_image = findViewById(R.id.btn_remove_image_edit_gallery_bottom);
//        save = findViewById(R.id.btn_save_edit_gallery_bottom);
//        view_gallery_bottom = findViewById(R.id.scrlV_edit_gallery_bottom);
//        layout_edit_gallery_bottom = findViewById(R.id.layout_edit_gallery_bottom);
//
//        close_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(EditGallery.this, EditRecipeScreen.class);
//                startActivity(intent);
//            }
//        });
//        add_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent openGallery = new Intent();
//                openGallery.setAction(Intent.ACTION_GET_CONTENT);
//                openGallery.setType("image/*");
//                openGalleryResultLauncher.launch(openGallery);
//            }
//        });
//        remove_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ImageView imageView = findViewById(newImageId.get(newImageId.size() -1));
//                newImageId.remove(newImageId.size() - 1);
//                newImageURI.remove(newImageURI.size() - 1);
//                ((ViewGroup)imageView.getParent()).removeView(imageView);
//            }
//        });
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(EditGallery.this, EditRecipeScreen.class);
//                i.putParcelableArrayListExtra("savedImages", newImageURI);
//                i.putExtra("imageLength",newImageURI.size());
//                startActivity(i);
//            }
//        });
//    }
//}
