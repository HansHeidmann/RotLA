import java.util.Objects;

public class Shout extends CelebrateDecorator {
    //
    // SHOUT DECORATION
    //
    CelebrateDecorator decorator;
    public Shout(CelebrateDecorator decorator) {
        this.decorator = decorator;
    }
    public String getCelebrations() {
        if (Objects.equals(decorator.getCelebrations(), null)) {
            return "Shouted";
        } else {
            return decorator.getCelebrations() + ", and Shouted";
        }
    }
}
