package model;

public class User {
    private static int Id = 0;
    private static String Password;
    private static String Mail;
    private static String Signature;
    private static String HeadPhoto;

    public static String getPassword() {
        return Password;
    }

    public static void setId(int id) {
        Id = id;
    }

    public static void setPassword(String password) {
        Password = password;
    }

    public static void setMail(String mail) {
        Mail = mail;
    }

    public static void setSignature(String signature) {
        Signature = signature;
    }

    public static void setHeadPhoto(String headPhoto) {
        HeadPhoto = headPhoto;
    }

    public static String getMail() {
        return Mail;
    }

    public static String getSignature() {
        return Signature;
    }

    public static String getHeadPhoto() {
        return HeadPhoto;
    }

    public static int getId() {
        return Id;
    }
}
