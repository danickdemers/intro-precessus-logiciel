package ulaval.glo2003.application.dtos;

import java.util.ArrayList;
import java.util.List;

public class ProductViewingsDto {
    public String id;
    public String createdAt;
    public String title;
    public String description;
    public float suggestedPrice;
    public int viewings;
    public List<String> categories = new ArrayList<>();
}
