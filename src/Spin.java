import java.util.Objects;

public class Spin extends CelebrateDecorator {
    //
    // SPIN DECORATION
    //
    CelebrateDecorator decorator;
    public Spin(CelebrateDecorator decorator) {
        this.decorator = decorator;
    }
    public String getCelebrations() {
        if (Objects.equals(decorator.getCelebrations(), null)) {
            return "Spun";
        } else {
            return decorator.getCelebrations() + ", and Spun";
        }
    }
}
