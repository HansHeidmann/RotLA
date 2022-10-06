import java.util.Objects;

public class Jump extends CelebrateDecorator {
    //
    // JUMP (decorator pattern)
    //
    CelebrateDecorator decorator;
    public Jump(CelebrateDecorator decorator) {
        this.decorator = decorator;
    }
    public String getCelebrations() {
        if (Objects.equals(decorator.getCelebrations(), null)) {
            return "Jumped";
        } else {
            return decorator.getCelebrations() + ", and Jumped";
        }
    }
}
