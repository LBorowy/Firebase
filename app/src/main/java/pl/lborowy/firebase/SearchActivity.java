package pl.lborowy.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.getWorkerId)
    TextView getWorkerId;
    @BindView(R.id.search_id)
    TextView workerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
