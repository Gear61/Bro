package randomappsinc.com.bro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText searchInput;

    private FriendsAdapter friendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchInput = (EditText) findViewById(R.id.search_input);
        searchInput.addTextChangedListener(searchInputListener);

        ListView friends = (ListView) findViewById(R.id.friends);
        friendsAdapter = new FriendsAdapter(this);
        friends.setAdapter(friendsAdapter);
        friends.setOnItemClickListener(friendsListListener);
    }

    public void clearSearch(View view) {
        searchInput.setText("");
    }

    TextWatcher searchInputListener = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void afterTextChanged(Editable s) {
            friendsAdapter.updateContentWithPrefix(s.toString());
        }
    };

    AdapterView.OnItemClickListener friendsListListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
        {
            Friend friend = friendsAdapter.getItem(position);
            showConfirmationDialog(friend);
        }
    };

    public void showConfirmationDialog(final Friend friend) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(R.string.confirm_bro);
        String message = "Are you sure you want to Bro " + friend.getFriendName() + "?";

        // Set dialog message and buttons
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SmsManager.getDefault().sendTextMessage(friend.getPhoneNumber(), null, "Bro", null, null);
                        Toast.makeText(MyApplication.getAppContext(), "You bro-ed " + friend.getFriendName() + ".", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // Create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // Show it
        alertDialog.show();
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
