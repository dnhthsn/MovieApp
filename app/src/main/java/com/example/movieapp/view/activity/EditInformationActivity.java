package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityEditInformationBinding;
import com.example.movieapp.model.Users;
import com.example.movieapp.util.Const;
import com.example.movieapp.viewmodel.UserViewModel;

public class EditInformationActivity extends AppCompatActivity {
    private ActivityEditInformationBinding binding;
    private UserViewModel userViewModel;

    public static void starter(Context context, Bundle bundle) {
        Intent intent = new Intent(context, EditInformationActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_information);
        userViewModel = new UserViewModel(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            binding.inputName.setText(bundle.getString(Const.Sender.name));
            binding.inputPassword.setText(bundle.getString(Const.Sender.password));
            binding.inputPhone.setText(bundle.getString(Const.Sender.phone));
            binding.inputAddress.setText(bundle.getString(Const.Sender.address));
        }

        binding.clickBack.setOnClickListener(view -> finish());

        binding.clickUpdate.setOnClickListener(view -> {
            String name = binding.inputName.getText().toString();
            String phone = binding.inputPhone.getText().toString();
            String password = binding.inputPassword.getText().toString();
            String address = binding.inputAddress.getText().toString();

            int genderGrID = binding.genderGroup.getCheckedRadioButtonId();
            RadioButton genderRad = findViewById(genderGrID);
            String gender = genderRad.getText().toString();

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(EditInformationActivity.this, Const.Error.name, Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(phone)) {
                Toast.makeText(EditInformationActivity.this, Const.Error.phone, Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(EditInformationActivity.this, Const.Error.password, Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(address)) {
                Toast.makeText(EditInformationActivity.this, Const.Error.address, Toast.LENGTH_SHORT).show();
            } else {
                Users users = new Users(name, phone, password, address, gender);
                userViewModel.updateUser(users);
                LoginActivity.starter(EditInformationActivity.this);
                Toast.makeText(EditInformationActivity.this, Const.Success.update, Toast.LENGTH_SHORT).show();
            }
        });
    }
}