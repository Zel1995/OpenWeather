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

import com.example.openweather.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import kotlin.Lazy;

import static org.koin.android.compat.SharedViewModelCompat.sharedViewModel;

public class MainFragment extends Fragment {
    private static final String LAST_REQUEST = "LAST_REQUEST";
    private static final String COUNTRY_CODE = ",ru";
    private static final String MILES_PER_HOUR = " mph";
    private static final String PERCENT = " %";
    private static final String FAHRENHEIT = " F";
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
        if (savedInstanceState == null) {
            viewModel.getValue().fetchWeather(LAST_REQUEST);
        }
        setSearchButtonClickListener();
    }

    private void setSearchButtonClickListener() {
        button.setOnClickListener(v -> {
            String result = Objects.requireNonNull(editText.getText()).append(COUNTRY_CODE).toString();
            editText.setText("");
            viewModel.getValue().fetchWeather(result);
        });
    }

    private void initViewModel() {
        viewModel.getValue().getWeatherLiveData().observe(getViewLifecycleOwner(), weather -> {
            tvCity.setText(weather.getCityName());
            String temperature = weather.getTemperature() + FAHRENHEIT;
            tvTemperature.setText(temperature);
            String windSpeed = weather.getWindSpeed() + MILES_PER_HOUR;
            tvWindSpeed.setText(windSpeed);
            String humidity = weather.getHumidity() + PERCENT;
            tvHumidity.setText(humidity);
            tvVisibility.setText(convertVisibility(weather.getVisibility()));
            tvSunrise.setText(convertTime(weather.getTimeOfSunrise()));
            tvSunset.setText(convertTime(weather.getTimeOfSunset()));
        });
        viewModel.getValue().getErrorLiveData().observe(getViewLifecycleOwner(), s -> Log.d("TAG", s));
    }

    private String convertVisibility(int visibility) {
        if (visibility >= 10000) {
            return "clear";
        }
        if (visibility > 4000) {
            return "average";
        } else {
            return "low";
        }
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

    private String convertTime(Long time) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a z");
        return timeFormatter.format(new Date(time * 1000L));
    }
}
