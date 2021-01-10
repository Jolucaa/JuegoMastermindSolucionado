package usantatecla.mastermind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import usantatecla.utils.ColorCode;
import usantatecla.utils.Console;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProposedCombinationTest {

    private ProposedCombination proposedCombination;

    @Mock
    Console console;

    @BeforeEach
    void before() {
        this.proposedCombination = new ProposedCombination();
    }

    @Test
    void testGivenEmptyProposedCombinationWhenReadValidCombinationThenContainsColors(){
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readString()).thenReturn("rgby");
            this.proposedCombination.read();
            assertThat(this.proposedCombination.contains(ColorCode.RED),is(true));
            assertThat(this.proposedCombination.contains(ColorCode.GREEN),is(true));
            assertThat(this.proposedCombination.contains(ColorCode.BLUE),is(true));
            assertThat(this.proposedCombination.contains(ColorCode.YELLOW),is(true));
        }
    }

    @Test
    void testGivenEmptyProposedCombinationWhenReadNotValidCombinationThenRequestCombination(){
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readString()).thenReturn("rgbl","rgby");
            this.proposedCombination.read();
            verify(this.console,times(2)).readString();
        }
    }

    @Test
    void testGivenEmptyProposedCombinationWhenReadNotValidSizeCombinationThenRequestCombination(){
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readString()).thenReturn("rgbym","rgby");
            this.proposedCombination.read();
            verify(this.console,times(2)).readString();
        }
    }

    @Test
    void testGivenEmptyProposedCombinationWhenReadRepeatCombinationThenRequestCombination(){
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readString()).thenReturn("rrrr","rgby");
            this.proposedCombination.read();
            verify(this.console,times(2)).readString();
        }
    }

    @Test
    void testGivenEmptyProposedCombinationWhenGetColorsLengthThenReturns0() {
        assertThat(this.proposedCombination.colorCodes.size(), is(0));
    }

    @Test
    void testGivenColorsInProposedCombinationWhenIsEqualsToOtherColorArrayThenIsTrue() {
        ArrayList<ColorCode> colors = new ArrayList<>(Arrays.asList(ColorCode.RED,ColorCode.GREEN,ColorCode.YELLOW,ColorCode.CYAN));
        this.proposedCombination.colorCodes = colors;
        assertThat(this.proposedCombination.colorCodes, is(colors));
    }

    @Test
    void testGivenColorsInProposedCombinationWhenColorIsNotContainedThenIsFalse() {
        this.proposedCombination.colorCodes = new ArrayList<>(Arrays.asList(ColorCode.RED,ColorCode.GREEN,ColorCode.YELLOW,ColorCode.CYAN));
        assertThat(this.proposedCombination.contains(ColorCode.MAGENTA), is(false));
    }

    @Test
    void testGivenColorsInProposedCombinationWhenColorIsContainedThenIsTrue() {
        this.proposedCombination.colorCodes = new ArrayList<>(Arrays.asList(ColorCode.RED,ColorCode.GREEN,ColorCode.YELLOW,ColorCode.CYAN));
        assertThat(this.proposedCombination.contains(ColorCode.RED), is(true));
    }

    @Test
    void testGivenColorsInProposedCombinationWhenColorIsContainedByPositionThenIsTrue() {
        this.proposedCombination.colorCodes = new ArrayList<>(Arrays.asList(ColorCode.RED,ColorCode.GREEN,ColorCode.YELLOW,ColorCode.CYAN));
        assertThat(this.proposedCombination.contains(ColorCode.RED,0),is(true));
    }

    @Test
    void testGivenColorsInProposedCombinationWhenColorIsContainedByPositionOutOfSizeThenIsAssert() {
        this.proposedCombination.colorCodes = new ArrayList<>(Arrays.asList(ColorCode.RED,ColorCode.GREEN,ColorCode.YELLOW,ColorCode.CYAN));
        Assertions.assertThrows(AssertionError.class, () -> this.proposedCombination.contains(ColorCode.RED,10));
    }
}
