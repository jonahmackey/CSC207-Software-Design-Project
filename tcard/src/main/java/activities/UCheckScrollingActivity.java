package activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import activities.databinding.ActivityUcheckScrollingBinding;
import controllers.UserManager;
import usecases.UCheckCommands;
import java.util.ArrayList;

/**
 * This is the main dashboard for UCheck. This will display a UI interface for UCheck containing the status of UCheck,
 * and a webpage for additional resources.
 */
public class UCheckScrollingActivity extends AppCompatActivity {

private ActivityUcheckScrollingBinding binding;

    UserManager myManager;
    UCheckCommands myUCheckCommands;
    /**
     * @param savedInstanceState for any information that was saved previously.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // We get the user information from the USER object by using a controller (myManager)
        myManager = (UserManager) getIntent().getSerializableExtra("manager");
        binding = ActivityUcheckScrollingBinding.inflate(getLayoutInflater());
        myUCheckCommands=new UCheckCommands();
        setContentView(binding.getRoot());
        //This button brings USER into the questionnaire activity.
        binding.startSelfAssessment.setOnClickListener(view -> {
                    Intent intent = new Intent(getApplicationContext(), UCheckQuestionnaireActivity.class);
                    startActivityForResult(intent,001);
        });
        //the button sends us back to dashboard.
        binding.imgBack.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
            intent.putExtra("manager", myManager);
            startActivity(intent);
        });
        // Prompts UofT external website for USER.
        binding.imageViewBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.utoronto.ca/utogether"));
            startActivity(intent);
        });
        showScreen();
    }

    /**
     *
     * The Activity UCheck APIs provide components for registering for a result, launching the result, and handling the
     * result once it is dispatched by the system.
     * @param requestCode integer target value from result of interacting activity, 001 = pass
     * @param resultCode integer result
     * @param data Intent ...
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 001) {
            if(resultCode == RESULT_OK) {
                assert data != null;
                boolean isAllowed = data.getBooleanExtra("isAllowed", false);
                // This updates from the results of next activity.
                myUCheckCommands.setResult(this, myManager.getUser().getId(), isAllowed?1:2);
                showScreen();
            }
        }
    }

    /**
     * Display correct screen from the questionnaire with time completed and USER full name. //add usecase method isValid() UCheck
     */

    private void showScreen() {
        //Name of current USER.
        ArrayList<String> info = myManager.getinfo();
        String legalFirstName = info.get(2);
        String legalLastName = info.get(3);
        String Name = legalFirstName + " " + legalLastName;
        // Once a questionnaire is completed, this method sets the UCheck results
        myUCheckCommands.populateResult(this, myManager.getUser().getId());
        //UCheck of questionnaire of USER.
        int layout = myUCheckCommands.getLayout();
        LayoutInflater inflater = getLayoutInflater();
        View myLayout = inflater.inflate(layout, binding.linearLayout, false);
        TextView txtName = myLayout.findViewById(R.id.txtName);
        txtName.setText(Name);
        if(myUCheckCommands.getState()!= 0) {
            TextView txtDate = myLayout.findViewById(R.id.txtDate);
            txtDate.setText(myUCheckCommands.getDate());
        }
        binding.linearLayout.removeAllViews();
        binding.linearLayout.addView(myLayout);
}
}