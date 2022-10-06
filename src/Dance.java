import java.util.Objects;

public class Dance extends CelebrateDecorator {
    //
    // DANCE (decorator pattern)
    //
    CelebrateDecorator decorator;
    public Dance(CelebrateDecorator decorator) {
        this.decorator = decorator;
    }
    public String getCelebrations() {
        if (Objects.equals(decorator.getCelebrations(), null)) {
            return "Danced";
        } else {
            return decorator.getCelebrations() + ", and Danced";
        }
    }
}
