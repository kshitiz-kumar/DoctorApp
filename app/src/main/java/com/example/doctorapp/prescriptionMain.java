package com.example.doctorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class prescriptionMain extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ArrayList<String> matches ;
    List<String> categories;
    ArrayAdapter<String> dataAdapter;


    private ImageView nameSpinner, symptomSpinner, diagnosisSpinner,prescriptionSpinner,followupSpinner;
    public CardView nameRecorder, namestopRecorder, symptomRecorder, diagnosisRecorder,
            prescriptionRecorder, followupRecorder, symptomstopRecorder,diagnosisstopRecorder,prescriptionstopRecorder,followupstopRecorder;
    EditText editText;
    SpeechRecognizer mSpeechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Create Prescription");

        nameSpinner = (ImageView) findViewById(R.id.nameSpinner);
        symptomSpinner = (ImageView) findViewById(R.id.symptomSpinner);
        diagnosisSpinner = (ImageView) findViewById(R.id.diagnosisSpinner);
        prescriptionSpinner = (ImageView) findViewById(R.id.prescriptionSpinner);
        followupSpinner = (ImageView) findViewById(R.id.followupSpinner);

        nameRecorder = (CardView) findViewById(R.id.imageViewAudioname);
        namestopRecorder =(CardView)findViewById(R.id.imageViewStopname);
        symptomRecorder = (CardView) findViewById(R.id.imageViewAudiosymptoms);
        symptomstopRecorder = (CardView) findViewById(R.id.imageStopsymptom);
        diagnosisRecorder = (CardView) findViewById(R.id.imageViewAudiodiagnosis);
        diagnosisstopRecorder = (CardView) findViewById(R.id.diagnosisViewStopname);
        prescriptionRecorder = (CardView) findViewById(R.id.imageViewAudioprescription);
        prescriptionstopRecorder = (CardView) findViewById(R.id.prescriptionViewStopname);
        followupRecorder = (CardView) findViewById(R.id.imageViewAudiofollowup);
        followupstopRecorder = (CardView) findViewById(R.id.followupViewStopname);

        setListener();

//        setSpinner();

    }

//    private void setSpinner() {
//
//        categories = new ArrayList<String>();
//        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
//
//        categories.add("Delete");
//
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        nameSpinner.setAdapter(dataAdapter);
//        symptomSpinner.setAdapter(dataAdapter);
//        diagnosisSpinner.setAdapter(dataAdapter);
//        prescriptionSpinner.setAdapter(dataAdapter);
//        followupSpinner.setAdapter(dataAdapter);
//    }

    private void setListener() {

        namestopRecorder.setOnClickListener(this);
        nameRecorder.setOnClickListener(this);
        symptomRecorder.setOnClickListener(this);
        symptomstopRecorder.setOnClickListener(this);
        diagnosisRecorder.setOnClickListener(this);
        diagnosisstopRecorder.setOnClickListener(this);
        prescriptionRecorder.setOnClickListener(this);
        prescriptionstopRecorder.setOnClickListener(this);
        followupRecorder.setOnClickListener(this);
        followupstopRecorder.setOnClickListener(this);

//        nameSpinner.setOnItemSelectedListener(this);
//        symptomSpinner.setOnItemSelectedListener(this);
//        diagnosisSpinner.setOnItemSelectedListener(this);
//        prescriptionSpinner.setOnItemSelectedListener(this);
//        followupSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void onClick(View view) {

        int id = view.getId();

        switch(id)
        {
            case R.id.imageViewAudioname:
                nameRecorder.setVisibility(View.GONE);
                namestopRecorder.setVisibility(View.VISIBLE);

                recognizeSpeech();
                break;

            case R.id.imageViewStopname:
                namestopRecorder.setVisibility(View.GONE);
                nameRecorder.setVisibility(View.VISIBLE);
                mSpeechRecognizer.stopListening();
                break;

            case R.id.imageViewAudiosymptoms:
                symptomRecorder.setVisibility(View.GONE);
                symptomstopRecorder.setVisibility(View.VISIBLE);

                recognizeSpeech();
                break;

            case R.id.imageStopsymptom:
                symptomstopRecorder.setVisibility(View.GONE);
                symptomRecorder.setVisibility(View.VISIBLE);
                mSpeechRecognizer.stopListening();
                break;

            case R.id.imageViewAudiodiagnosis:
                diagnosisRecorder.setVisibility(View.GONE);
                diagnosisstopRecorder.setVisibility(View.VISIBLE);

                recognizeSpeech();
                break;

            case R.id.diagnosisViewStopname:
                diagnosisstopRecorder.setVisibility(View.GONE);
                diagnosisRecorder.setVisibility(View.VISIBLE);
                mSpeechRecognizer.stopListening();
                break;

            case R.id.imageViewAudioprescription:
                prescriptionRecorder.setVisibility(View.GONE);
                prescriptionstopRecorder.setVisibility(View.VISIBLE);

                recognizeSpeech();
                break;

            case R.id.prescriptionViewStopname:
                prescriptionstopRecorder.setVisibility(View.GONE);
                prescriptionRecorder.setVisibility(View.VISIBLE);
                mSpeechRecognizer.stopListening();
                break;

            case R.id.imageViewAudiofollowup:
                followupRecorder.setVisibility(View.GONE);
                followupstopRecorder.setVisibility(View.VISIBLE);

                recognizeSpeech();
                break;

            case R.id.followupViewStopname:
                followupstopRecorder.setVisibility(View.GONE);
                followupRecorder.setVisibility(View.VISIBLE);
                mSpeechRecognizer.stopListening();
                break;
        }

    }

    private void recognizeSpeech() {

        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);


        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());

        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);


        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {
                
                namestopRecorder.setVisibility(View.GONE);
                nameRecorder.setVisibility(View.VISIBLE);

                symptomstopRecorder.setVisibility(View.GONE);
                symptomRecorder.setVisibility(View.VISIBLE);

                diagnosisstopRecorder.setVisibility(View.GONE);
                diagnosisRecorder.setVisibility(View.VISIBLE);

                prescriptionstopRecorder.setVisibility(View.GONE);
                prescriptionRecorder.setVisibility(View.VISIBLE);

                followupstopRecorder.setVisibility(View.GONE);
                followupRecorder.setVisibility(View.VISIBLE);

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

//                if (matches != null)
//                    editText.setText(matches.get(0));
//
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
