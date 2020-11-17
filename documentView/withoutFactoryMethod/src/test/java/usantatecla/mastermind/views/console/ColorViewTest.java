package usantatecla.mastermind.views.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usantatecla.mastermind.models.Color;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ColorViewTest {

    ColorView colorView;

    @BeforeEach
    void before() {
        this.colorView = new ColorView();
    }

    @Test
    void testGivenStaticMethodAllInitialsWhenCallingThatMethodThenReturnsCorrectString() {
        String reset_color = "\u001B[0m";
        assertThat(this.colorView.allInitials(), is("\u001B[31mr" + reset_color +
                "\u001B[34mb" + reset_color +
                "\u001B[33my" + reset_color +
                "\u001B[32mg" + reset_color +
                "\u001B[37mo" + reset_color +
                "\u001B[35mp" + reset_color));
    }

    @Test
    void testGivenNotMatchingInitialColorCharacterWhenGetInstanceThenReturnsNullColor() {
        assertThat(ColorView.getInstance('n'), is(Color.NULL));
    }

    @Test
    void testGivenMatchingInitialColorCharacterWhenGetInstanceThenReturnsCorrectColor() {
        assertThat(ColorView.getInstance('g'), is(Color.GREEN));
    }
}