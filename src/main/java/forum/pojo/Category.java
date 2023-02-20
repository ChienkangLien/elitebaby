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
}
