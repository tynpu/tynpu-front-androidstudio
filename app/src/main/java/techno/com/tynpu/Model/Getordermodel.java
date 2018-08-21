package techno.com.tynpu.Model;

public class Getordermodel {
    private String  sub_item_food_name;

    public String getSub_item_food_name() {
        return sub_item_food_name;
    }

    public void setSub_item_food_name(String sub_item_food_name) {
        this.sub_item_food_name = sub_item_food_name;
    }

    @Override
    public String toString() {
        return "Getordermodel{" +
                "sub_item_food_name='" + sub_item_food_name + '\'' +
                '}';
    }
}
