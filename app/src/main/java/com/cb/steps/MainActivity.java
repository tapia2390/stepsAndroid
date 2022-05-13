package com.cb.steps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cb.steps.fragment.OneFragment;
import com.cb.steps.fragment.ThreeFragment;
import com.cb.steps.fragment.TwoFragment;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //https://github.com/shuhart/StepView

    StepView stepView;
    private int currentStep = 0;
    private int currentStep2 = 0;
    FragmentTransaction fragmentTransaction;
    Fragment oneFragment, twoFragment,threeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContent,oneFragment).commit();
        stepView = (StepView)findViewById(R.id.step_view);

        stepView.getState()
                .selectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .animationType(StepView.ANIMATION_LINE)
                .selectedCircleColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .selectedCircleRadius(getResources().getDimensionPixelSize(R.dimen.dp14))
                .selectedStepNumberColor(ContextCompat.getColor(this, R.color.colorPrimary))
                // You should specify only stepsNumber or steps array of strings.
                // In case you specify both steps array is chosen.
                /*.steps(new ArrayList<String>() {{
                    add("First step");
                    add("Second step");
                    add("Third step");
                }})*/
                // You should specify only steps number or steps array of strings.
                // In case you specify both steps array is chosen.
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(getResources().getDimensionPixelSize(R.dimen.dp1))
                .textSize(getResources().getDimensionPixelSize(R.dimen.sp14))
                .stepNumberTextSize(getResources().getDimensionPixelSize(R.dimen.sp16))
                .typeface(ResourcesCompat.getFont(getApplicationContext(), R.font.roboto_italic))
                // other state methods are equal to the corresponding xml attributes
                .commit();



        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                Toast.makeText(getApplicationContext(), "step "+ step,Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep < stepView.getStepCount() - 1) {
                    currentStep++;
                    stepView.go(currentStep, true);
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    switch(currentStep) {
                        case 0:
                            fragmentTransaction.replace(R.id.fragmentContent,oneFragment).commit();
                            break;
                        case 1:
                            fragmentTransaction.replace(R.id.fragmentContent,twoFragment).commit();
                            break;

                        case 2:
                            fragmentTransaction.replace(R.id.fragmentContent,threeFragment).commit();
                            break;
                    }
                } else {
                    stepView.done(true);
                }
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep > 0) {
                    currentStep--;
                }
                stepView.done(false);
                stepView.go(currentStep, true);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch(currentStep) {
                    case 0:
                        fragmentTransaction.replace(R.id.fragmentContent,oneFragment).commit();
                        break;
                    case 1:
                        fragmentTransaction.replace(R.id.fragmentContent,twoFragment).commit();
                        break;

                    case 2:
                        fragmentTransaction.replace(R.id.fragmentContent,threeFragment).commit();
                        break;
                }
            }
        });
    }
}