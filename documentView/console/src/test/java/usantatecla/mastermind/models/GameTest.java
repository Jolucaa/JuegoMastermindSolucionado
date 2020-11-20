package usantatecla.mastermind.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GameTest {

    private Game game;

    private void setGame(String... proposedCombinationStrings) {
        this.game = new GameBuilder().proposedCombinations(proposedCombinationStrings).build();
    }

    private void setGame(List<String> proposedCombinationStrings) {
        this.game = new GameBuilder().proposedCombinations(proposedCombinationStrings).build();
    }

    @Test
    void testGivenEmptyGameInstanceWhenIsLooserOrWinnerThenIsFalse() {
        this.setGame();
        assertThat(this.game.isLooser(), is(equalTo(false)));
        assertThat(this.game.isWinner(), is(equalTo(false)));
    }

    @Test
    void testGivenEmptyGameInstanceWhenGetAttemptsThenIs0() {
        this.setGame();
        assertThat(this.game.getAttempts(), is(0));
    }


    @Test
    void testGivenNineAttemptsGameWhenPlaceAnotherProposedCombinationThenIsLooser() {
        this.setGame("rbgy", "rbgy", "rbgy",
                     "rbgy", "rbgy", "rbgy",
                     "rbgy", "rbgy", "rbgy");

        assertThat(this.game.isLooser(), is(false));

        this.setGame("rbgy", "rbgy", "rbgy",
                    "rbgy", "rbgy", "rbgy",
                    "rbgy", "rbgy", "rbgy",
                    "rbgy");

        assertThat(this.game.isLooser(), is(true));
    }

    @Test
    void testGivenGameWith1ProposedCombinationWhenGetProposedCombinationAtPosition0ThenReturnsThatProposedCombination() {
        this.setGame("rbgy");

        ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW));
        assertThat(this.game.getProposedCombination(0).getColors(), is(colors));
    }

    @Test
    void testGivenGameWith3ProposedCombinationsWhenGetAttemptsThenReturns3() {
        this.setGame("rbgy", "rbgy", "rbgy");

        assertThat(this.game.getAttempts(), is(3));
    }

    @Test
    void testGivenGameWith1ProposedCombinationWhenGetResultAt0ThenReturnsValidResult() {
        this.setGame("rbgy");

        assertThat(this.game.getResult(0), notNullValue(Result.class));
    }

    @Test
    void testGivenGameInAnyStateWhenResetThenIsEmpty() {
        ArrayList<String> strings = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < random.nextInt(10); i++) {
            strings.add("rbgy");
        }
        this.setGame(strings);

        this.game.reset();

        assertThat(this.game.getAttempts(), is(0));
        assertThat(this.game.isLooser(), is(false));
        // assertThat(this.game.isWinner(), is(false));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> this.game.getProposedCombination(0));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> this.game.getResult(0));
    }
}
