package com.example.openweather.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.openweather.R;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

public class MainFragment extends Fragment {
    private TextInputEditText editText;
    private TextView tvCity;
    private TextView tvTemperature;
    private TextView tvWindSpeed;
    private TextView tvHumidity;
    private TextView tvVisibility;
    private TextView tvSunrise;
    private TextView tvSunset;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        tvCity = view.findViewById(R.id.info_city_name);
        tvTemperature = view.findViewById(R.id.info_temperature);
        tvWindSpeed = view.findViewById(R.id.info_wind_speed);
        tvHumidity = view.findViewById(R.id.info_humidity);
        tvVisibility = view.findViewById(R.id.info_visibility);
        tvSunrise = view.findViewById(R.id.info_sunrise);
        tvSunset = view.findViewById(R.id.info_sunset);
        editText = view.findViewById(R.id.edit_text);
    }
}
