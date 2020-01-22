package com.example.doctorapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.example.doctorapp.prescriptionMain;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.doctorapp.prescriptionMain.diagnosis;
import static com.example.doctorapp.prescriptionMain.diagnosisRecorder;
import static com.example.doctorapp.prescriptionMain.diagnosisstopRecorder;
import static com.example.doctorapp.prescriptionMain.followup;
import static com.example.doctorapp.prescriptionMain.followupRecorder;
import static com.example.doctorapp.prescriptionMain.followupstopRecorder;
import static com.example.doctorapp.prescriptionMain.name;
import static com.example.doctorapp.prescriptionMain.nameRecorder;
import static com.example.doctorapp.prescriptionMain.namestopRecorder;
import static com.example.doctorapp.prescriptionMain.prescription;
import static com.example.doctorapp.prescriptionMain.prescriptionRecorder;
import static com.example.doctorapp.prescriptionMain.prescriptionstopRecorder;
import static com.example.doctorapp.prescriptionMain.symptom;
import static com.example.doctorapp.prescriptionMain.symptomRecorder;
import static com.example.doctorapp.prescriptionMain.symptomstopRecorder;


public class textView extends Fragment {


    public static final String Name_Flag = "name";
    public static final String Symptom_Flag = "symptom";
    public static final String Diagnosis_Flag = "diagnosis";
    public static final String Prescription_Flag = "prescription";
    public static final String Followup_Flag = "followup";
    private View view;

    Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

    Handler checkForUserPauseAndSpeak = new Handler();
    Boolean speechResultsFound = false;


    EditText editText;
    SpeechRecognizer mSpeechRecognizer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_text_view, container, false);

        editText = (EditText) view.findViewById(R.id.speakzone);
        String Mode = ProgramModeActivity.getMode();

        recognizeSpeech(Mode);

        return view;
    }

    private void recognizeSpeech(final String Flag) {

        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());


        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 5000);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 5000);


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

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                        Intent intent = new Intent(getContext(),prescriptionMain.class);
                        startActivity(intent);

                    }
                }, 1500);


            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {

                switch (Flag){

                    case Name_Flag:
                        name = bundle
                                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                        name = bundle
                                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                        if (name != null)
                            editText.setText(name.get(0));

                        break;

                    case Symptom_Flag:
                        symptom = bundle
                                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                        symptom = bundle
                                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                        if (symptom != null)
                            editText.setText(symptom.get(0));

                        break;

                    case Diagnosis_Flag:
                        diagnosis = bundle
                                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                        diagnosis = bundle
                                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                        if (diagnosis != null)
                            editText.setText(diagnosis.get(0));

                        break;

                    case Prescription_Flag:
                        prescription = bundle
                                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                        prescription = bundle
                                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                        if (prescription != null)
                            editText.setText(prescription.get(0));

                        break;

                    case Followup_Flag:
                        followup = bundle
                                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                        followup = bundle
                                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                        if (followup != null)
                            editText.setText(followup.get(0));

                        break;
                }

            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

    }


}
