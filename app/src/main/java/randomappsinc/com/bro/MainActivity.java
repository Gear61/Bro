package randomappsinc.com.bro;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

public class MainActivity extends Activity {
    @Bind(R.id.search_input) EditText searchInput;
    @Bind(R.id.friends) ListView friends;

    private FriendsAdapter friendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        friendsAdapter = new FriendsAdapter(this);
        friends.setAdapter(friendsAdapter);
    }

    @OnClick(R.id.clear_icon)
    public void clearSearch(View view) {
        searchInput.setText("");
    }

    @OnTextChanged(value = R.id.search_input, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void updateWithPrefix(Editable editable) {
        friendsAdapter.updateContentWithPrefix(editable.toString());
    }

    @OnItemClick(R.id.friends)
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
        Friend friend = friendsAdapter.getItem(position);
        SmsManager.getDefault().sendTextMessage(friend.getPhoneNumber(), null, "Bro", null, null);
        Toast.makeText(this, "You have bro-ed " + friend.getFriendName() + ".", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
