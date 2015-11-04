package randomappsinc.com.bro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.search_input) EditText searchInput;
    @Bind(R.id.friends) ListView friends;
    @Bind(R.id.coordinator_layout) View parent;
    @BindColor(R.color.black) int black;
    @BindColor(R.color.white) int white;

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
        showConfirmationDialog(friend);
    }

    public void showConfirmationDialog(final Friend friend) {
        new MaterialDialog.Builder(this)
                .title(R.string.confirm_bro)
                .content("Are you sure you want to Bro " + friend.getFriendName() + "?")
                .positiveText(android.R.string.yes)
                .negativeText(android.R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        SmsManager.getDefault().sendTextMessage(friend.getPhoneNumber(), null, "Bro", null, null);
                        showBroSnackbar(parent, friend, black, white);
                    }
                })
                .show();
    }

    public static void showBroSnackbar(View parent, Friend friend, int backgroundColor, int textColor) {
        String message = "You bro-ed " + friend.getFriendName() + ".";
        Snackbar snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(backgroundColor);
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(textColor);
        snackbar.show();
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
