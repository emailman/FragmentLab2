package edu.dtcc.klochel.fragmentlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements InputFragment.InputFragmentListener {
    private InputFragment inputFragment;

    // View objects
    private EditText etShippingInput;
    private TextView tvBaseCost;
    private TextView tvAddedCost;
    private TextView tvTotalShippingCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create object
        inputFragment = new InputFragment();

        // Set value for EditText
        etShippingInput = (EditText) findViewById(R.id.etShippingInput);

        // Set values to TextView's
        tvBaseCost = (TextView) findViewById(R.id.tvBaseCost);
        tvAddedCost = (TextView) findViewById(R.id.tvAddedCost);
        tvTotalShippingCost = (TextView) findViewById(R.id.tvTotalShippingCost);

        // Add listener to EditText for text changes
        etShippingInput.addTextChangedListener(weightTextWatcher);
    }

    private TextWatcher weightTextWatcher = new TextWatcher()
    {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            try {
                inputFragment.setWeight((int) Double.parseDouble(s.toString()));
            } catch (NumberFormatException e) {
                inputFragment.setWeight(0);
            }
            displayShippingCosts();
        }

        public void afterTextChanged(Editable s) {}
    };

    private void displayShippingCosts()
    {
        tvBaseCost.setText("$" + String.format("%.02f", inputFragment.getBaseCost()));
        tvAddedCost.setText("$" + String.format("%.02f", inputFragment.getAddedCost()));
        tvTotalShippingCost.setText("$" + String.format("%.02f", inputFragment.getTotalCost()));
    }

    @Override
    public void createShippingCosts(String base, String added, String total)
    {
        OutputFragment outputFragment = (OutputFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment2);
        outputFragment.setCostText(base, added, total);
    }
}