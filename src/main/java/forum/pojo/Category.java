package forum.pojo;
import lombok.Data;
@Data
public class Category {
    int id;
    String category;
    String img;
    int level;

    public Category(int id, String category, String img, int level) {
        this.id = id;
        this.category = category;
        this.img = img;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
