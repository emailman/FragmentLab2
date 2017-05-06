package edu.dtcc.klochel.fragmentlab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OutputFragment extends Fragment
{
    // View objects
    private TextView tvBaseCost;
    private TextView tvAddedCost;
    private TextView tvTotalShippingCost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.output_fragment, container, false);
        tvBaseCost = (TextView) view.findViewById(R.id.tvBaseCost);
        tvAddedCost = (TextView) view.findViewById(R.id.tvAddedCost);
        tvTotalShippingCost = (TextView) view.findViewById(R.id.tvTotalShippingCost);
        return view;
    }

    public void setCostText(String base, String added, String total)
    {
        tvBaseCost.setText(base);
        tvAddedCost.setText(added);
        tvTotalShippingCost.setText(total);
    }
}