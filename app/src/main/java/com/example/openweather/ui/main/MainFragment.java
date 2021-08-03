package com.example.openweather.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.openweather.R;
import com.example.openweather.domain.Weather;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;


import kotlin.Lazy;

import static org.koin.android.compat.SharedViewModelCompat.sharedViewModel;

public class MainFragment extends Fragment {
    private static final String LAST_REQUEST = "LAST_REQUEST";
    private MaterialButton button;
    private TextInputEditText editText;
    private TextView tvCity;
    private TextView tvTemperature;
    private TextView tvWindSpeed;
    private TextView tvHumidity;
    private TextView tvVisibility;
    private TextView tvSunrise;
    private TextView tvSunset;
    final Lazy<MainViewModel> viewModel = sharedViewModel(this, MainViewModel.class);

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
        initViewModel();
        if(savedInstanceState == null){
            viewModel.getValue().fetchWeather(LAST_REQUEST);
        }
        setSearchButtonClickListener();
    }

    private void setSearchButtonClickListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = editText.getText().append(",ru").toString();
                editText.setText("");
                viewModel.getValue().fetchWeather(result);
            }
        });
    }

    private void initViewModel() {
        viewModel.getValue().getWeatherLiveData().observe(getViewLifecycleOwner(), new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                 tvCity.setText(weather.getCityName());
                 tvTemperature.setText(String.valueOf(weather.getTemperature()));
                 tvWindSpeed.setText(String.valueOf(weather.getWindSpeed()));
                 tvHumidity.setText(String.valueOf(weather.getHumidity()));
                 tvVisibility.setText(String.valueOf(weather.getVisibility()));
                 tvSunrise.setText(String.valueOf(weather.getTimeOfSunrise()));
                 tvSunset.setText(String.valueOf(weather.getTimeOfSunset()));
            }
        });
        viewModel.getValue().getErrorLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("TAG",s);
            }
        });
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
        button = view.findViewById(R.id.search_btn);
    }
}
