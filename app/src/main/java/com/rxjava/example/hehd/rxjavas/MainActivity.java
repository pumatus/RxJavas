package com.rxjava.example.hehd.rxjavas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rxjava.example.hehd.rxjavas.bean.User;
import com.rxjava.example.hehd.rxjavas.presenter.UserPresenter;
import com.rxjava.example.hehd.rxjavas.view.UserView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements UserView {

    @Bind(R.id.textView)
    TextView hhdTextView;
    @Bind(R.id.button)
    Button hhdButton;
    private Context context;

    private TextView mTvShow;

    private ProgressDialog mProgressDialog;

    private UserPresenter mUserPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        mUserPresenter = new UserPresenter(this);

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("正在加载，请稍后...");

        hhdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserPresenter.getUser();
            }
        });

    }

    @Override
    public void updateView(User user) {
        if(user == null) return;
        hhdTextView.setText(user.name);
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        mProgressDialog.hide();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
