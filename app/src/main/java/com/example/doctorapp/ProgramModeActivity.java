package com.example.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class ProgramModeActivity extends AppCompatActivity {

    private static final int nameFlag = 0;
    private static final int symptomFlag = 1;
    private final static int diagnosisFlag = 2;
    private static final int prescriptionFlag = 3;
    private static final int followupFlag = 4;

    private int mode;
    static String modeFlag;
    public static final String INTENT_KEY_PROGRAM_MODE = "MODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_program_mode);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        modeFlag = bundle.getString(INTENT_KEY_PROGRAM_MODE);

        if(modeFlag.equals(textView.Name_Flag))
            mode = nameFlag;

        else if(modeFlag.equals(textView.Symptom_Flag))
            mode = symptomFlag;

        else if(modeFlag.equals(textView.Diagnosis_Flag))
            mode = diagnosisFlag;

        else if(modeFlag.equals(textView.Prescription_Flag))
            mode = prescriptionFlag;

        else if(modeFlag.equals(textView.Followup_Flag))
            mode = followupFlag;

        Fragment fragment;

        switch (mode)
        {
            case nameFlag:
                fragment = new textView();
                if (bundle.getString(textView.Name_Flag) != null)
                    fragment.setArguments(bundle);
                moveToFragment(fragment);
                break;

            case symptomFlag:
                fragment = new textView();
                if (bundle.getString(textView.Symptom_Flag) != null)
                    fragment.setArguments(bundle);
                moveToFragment(fragment);
                break;

            case diagnosisFlag:
                fragment = new textView();
                if (bundle.getString(textView.Diagnosis_Flag) != null)
                    fragment.setArguments(bundle);
                moveToFragment(fragment);
                break;

            case prescriptionFlag:
                fragment = new textView();
                if (bundle.getString(textView.Prescription_Flag) != null)
                    fragment.setArguments(bundle);
                moveToFragment(fragment);
                break;

            case followupFlag:
                fragment = new textView();
                if (bundle.getString(textView.Followup_Flag) != null)
                    fragment.setArguments(bundle);
                bundle.putString("Mode","followup");
                moveToFragment(fragment);
                break;
        }

    }

    public static String getMode() {

        return modeFlag;
    }


    private void moveToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName()).commit();
    }
}
