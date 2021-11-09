package Thainam.inlove;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Comparable<User>{
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String username;
    private String address;

    public User(String username, String address) {
        this.username = username;
        this.address = address;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(User o) {
        return this.Id - o.getId();
    }
}
