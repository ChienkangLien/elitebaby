package forum.pojo;

import lombok.Data;

@Data
public class FormBean {
    private String[] categoryNames;
    private String userName;

    public String[] getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(String[] categoryNames) {
        this.categoryNames = categoryNames;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
