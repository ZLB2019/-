package model;

public class User {
    private  int Id = 0;
    private  String Password;
    private  String Mail;
    private  String Signature;
    private  String HeadPhoto;
    private  String Sex;
    private  String Born;
    private  String NetName;

    public void setId(int id) {
        Id = id;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public void setHeadPhoto(String headPhoto) {
        HeadPhoto = headPhoto;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public void setBorn(String born) {
        Born = born;
    }

    public void setNetName(String netName) {
        NetName = netName;
    }

    public int getId() {
        return Id;
    }

    public String getPassword() {
        return Password;
    }

    public String getMail() {
        return Mail;
    }

    public String getSignature() {
        return Signature;
    }

    public String getHeadPhoto() {
        return HeadPhoto;
    }

    public String getSex() {
        return Sex;
    }

    public String getBorn() {
        return Born;
    }

    public String getNetName() {
        return NetName;
    }
}
