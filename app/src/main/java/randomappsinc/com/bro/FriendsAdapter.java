package randomappsinc.com.bro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Alex Chiou on 9/29/15.
 */
public class FriendsAdapter extends BaseAdapter {
    private List<Friend> friends;
    private Context context;

    @SuppressWarnings("unchecked")
    public FriendsAdapter(Context context) {
        super();
        this.context = context;
        this.friends = FriendManager.getInstance().matchPrefix("");
    }

    public void updateContentWithPrefix(String prefix) {
        this.friends = FriendManager.getInstance().matchPrefix(prefix);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Friend getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        @Bind(R.id.friend_name) TextView friendName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.friend_cell, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.friendName.setText(friends.get(position).getFriendName());
        return view;
    }
}
