package model;

public class Group {
    private int Group_main;
    private int Group_id;
    private String Group_name;
    private String Group_head;


    public void setGroup_id(int group_id) {
        Group_id = group_id;
    }

    public void setGroup_name(String group_name) {
        Group_name = group_name;
    }

    public void setGroup_head(String group_head) {
        Group_head = group_head;
    }

    public void setGroup_main(int group_main) {
        Group_main = group_main;
    }

    public int getGroup_main() {
        return Group_main;
    }

    public int getGroup_id() {
        return Group_id;
    }

    public String getGroup_name() {
        return Group_name;
    }

    public String getGroup_head() {
        return Group_head;
    }
}
