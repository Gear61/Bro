package randomappsinc.com.bro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex Chiou on 9/29/15.
 */
public class FriendManager {
    private List<Friend> friends;
    private static FriendManager instance;

    public static FriendManager getInstance() {
        if (instance == null) {
            instance = new FriendManager();
        }
        return instance;
    }

    private FriendManager() {
        this.friends = PhoneBookUtils.getPhoneFriends();
    }

    public List<Friend> getFriends() {
        return friends;
    }
}