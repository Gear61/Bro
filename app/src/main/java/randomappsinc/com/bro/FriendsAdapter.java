package randomappsinc.com.bro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


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
        this.friends = FriendManager.getInstance().getFriends();
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
        TextView friendName;

        public ViewHolder(View view) {
            friendName = (TextView) view.findViewById(R.id.contact_name);
        }
    }

    public void updateContentWithPrefix(String prefix) {
        this.friends = FriendManager.getInstance().matchPrefix(prefix);
        notifyDataSetChanged();
    }

    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.contact_cell, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.friendName.setText(friends.get(position).getName());
        return view;
    }
}
