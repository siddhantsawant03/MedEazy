package com.siddhantsawant.healthcareapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainDisplayScreen extends AppCompatActivity {

//    BottomNavigationView nav;
    private ViewPager viewPager;
    private ImagePagerAdapter adapter;
    private ArrayList<Integer> images;
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 1000; // Delay in milliseconds
    private final long PERIOD_MS = 3000;
    private final int GALLERY_REQ_CODE = 1000;

    ImageView imgGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display_screen);

//        nav = findViewById(R.id.nav);
//        navigation();
        Button btnUser = findViewById(R.id.btnUser);
        Button btnDoctor = findViewById(R.id.btnDoctor);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainDisplayScreen.this,LoginActivity.class);
                startActivity(i1);
            }
        });

        btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainDisplayScreen.this,DoctorLogin.class);
                startActivity(i1);
            }
        });

        imgGallery = findViewById(R.id.imgGallery);
        Button btn1 = (Button) findViewById(R.id.btn1);
        viewPager = findViewById(R.id.viewPager);
        images = new ArrayList<>();

        //adding behaviour to pick pics from gallery on button press
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GALLERY_REQ_CODE);
            }
        });

        // Add your image resource IDs to the 'images' ArrayList
        images.add(R.drawable.img1);
        images.add(R.drawable.img3);
        images.add(R.drawable.img4);
        adapter = new ImagePagerAdapter(this, images);
        viewPager.setAdapter(adapter);

        // Set up a timer to change images automatically
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == images.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(update);
            }
        }, DELAY_MS, PERIOD_MS);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK) {
            if (requestCode==GALLERY_REQ_CODE) {
                //for gallery
                imgGallery.setImageURI(data.getData());
            }
        }
    }

    public class ImagePagerAdapter extends PagerAdapter {

        private Context context;
        private ArrayList<Integer> images;

        public ImagePagerAdapter(Context context, ArrayList<Integer> images) {
            this.context = context;
            this.images = images;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.view_pager_item, container, false);

            ImageView imageView = itemView.findViewById(R.id.imageView);
            imageView.setImageResource(images.get(position));

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

//    public void navigation() {
//        nav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                Intent intent = null;
//
//                if (item.getItemId() == R.id.menu_home) {
//                    intent = new Intent(MainDisplayScreen.this, MainDisplayScreen.class);
//                } else if (item.getItemId() == R.id.menu_about) {
//                    intent = new Intent(MainDisplayScreen.this, About.class);
//                } else if (item.getItemId() == R.id.menu_settings) {
//                    intent = new Intent(MainDisplayScreen.this, Profile.class);
//                }
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });
//    }
}