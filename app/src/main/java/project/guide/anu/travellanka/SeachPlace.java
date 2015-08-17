package project.guide.anu.travellanka;

/**
 * Created by Anu on 6/24/2015.
 */
public class SeachPlace {

    private String name;
    private String address;
    private  String phone_no;
    private String description;
    private int image;


    public SeachPlace(String n, int i){
        setName(n);
        setImage(i);
    }

    public SeachPlace(String n,String add, String pn, String desc, int i){
        setName(n);
        setAddress(add);
        setPhone_no(pn);
        setDescription(desc);
        setImage(i);
    }

    public SeachPlace(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
