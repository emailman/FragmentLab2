package edu.dtcc.klochel.fragmentlab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class InputFragment extends Fragment
{
    private static EditText etShippingInput;

    // Interface
    InputFragmentListener activityCommander;

    // Variables
    static final double BASE = 6.00;
    static final double ADDED = .50;
    static final int BASE_WEIGHT = 16;
    static final double EXTRA_OUNCES = 1.0;

    // input variables
    private int weight;
    private double baseCost;
    private double addedCost;
    private double totalCost;

    public interface InputFragmentListener
    {
        public void createShippingCosts(String base, String added, String total);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            activityCommander = (InputFragmentListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.input_fragment, container, false);
        etShippingInput = (EditText) view.findViewById(R.id.etShippingInput);
        return view;
    }

    // Constructors created for the input fragment
    public InputFragment()
    {
        weight = 0;
        addedCost = 0.0;
        baseCost = BASE;
        totalCost = 0.0;
    }

    public void setWeight (int weight)
    {
        this.weight = weight;
        computeShippingCosts();
    }

    private void computeShippingCosts()
    {
        addedCost = 0.00;
        baseCost = BASE;

        if (weight <= 0)
            baseCost = 0.00;
        else if (weight > BASE_WEIGHT)
            addedCost = Math.ceil((double)(weight - BASE_WEIGHT) / EXTRA_OUNCES) * ADDED;

        totalCost = baseCost + addedCost;
    }

    public double getBaseCost()
    {
        return baseCost;
    }

    public double getAddedCost()
    {
        return addedCost;
    }

    public double getTotalCost()
    {
        return totalCost;
    }
}