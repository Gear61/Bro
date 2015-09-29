package randomappsinc.com.bro;

/**
 * Created by Alex Chiou on 9/29/15.
 */
public class Friend implements Comparable<Friend>{
    private String friendName;
    private String phoneNumber;

    public Friend(String friendName, String phoneNumber) {
        this.friendName = friendName;
        this.phoneNumber = phoneNumber;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int compareTo(Friend another) {
        return friendName.compareTo(another.friendName);
    }
}
