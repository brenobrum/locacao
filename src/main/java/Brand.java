import java.util.ArrayList;

public class Brand {
    String brandDescription;
    ArrayList<Model> models;

    public Brand(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public void addModel(Model model) {
        models.add(model);
    }

    public ArrayList<Model> getModels() {
        return models;
    }
}
