package cn.deliver.domain;

import java.sql.Date;

public class UserInfo {
    private Integer id;

    private Integer uid;

    private String name;

    private String gender;

    private Date birthday;

    private String identityCard;

    private String identityCardPicture;

    private String avatar;

    public UserInfo() {
    }

    public UserInfo(Integer id, Integer uid, String name, String gender, Date birthday, String identityCard, String identityCardPicture, String avatar) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.identityCard = identityCard;
        this.identityCardPicture = identityCardPicture;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", identityCard='" + identityCard + '\'' +
                ", identityCardPicture='" + identityCardPicture + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
    }

    public String getIdentityCardPicture() {
        return identityCardPicture;
    }

    public void setIdentityCardPicture(String identityCardPicture) {
        this.identityCardPicture = identityCardPicture == null ? null : identityCardPicture.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }
}