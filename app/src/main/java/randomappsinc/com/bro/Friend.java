package randomappsinc.com.bro;

/**
 * Created by Alex Chiou on 11/3/15.
 */
public class Friend implements Comparable<Friend> {
    String name;
    String phoneNumber;

    public Friend(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Friend another) {
        return name.compareTo(another.name);
    }
}
