package com.idee.courseshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.idee.courseshare.activities.LectureDetailsActivity;
import com.idee.courseshare.adapters.LectureAdapter;
import com.idee.courseshare.model.Lecture;

public class MainActivity extends AppCompatActivity implements LectureAdapter.OnLectureClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView testRV = (RecyclerView) findViewById(R.id.test_rv);
        testRV.setLayoutManager(new LinearLayoutManager(this));
        testRV.setAdapter(new LectureAdapter(FirebaseDatabase.getInstance().getReference("lectures"), this));
    }

    @Override
    public void onLectureClick(String lectureId) {
        Toast.makeText(this, "Click na lecture " + lectureId, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, LectureDetailsActivity.class);
        intent.putExtra(LectureDetailsActivity.LECTURE_ID_EXTRA, lectureId);
        startActivity(intent);
    }
}
