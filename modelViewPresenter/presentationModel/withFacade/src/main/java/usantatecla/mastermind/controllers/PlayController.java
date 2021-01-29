package usantatecla.mastermind.controllers;

import usantatecla.mastermind.models.Board;
import usantatecla.mastermind.models.ProposedCombination;

public class PlayController extends Controller {

    public PlayController(Board board) {
        super(board);
    }

    public void add(ProposedCombination proposedCombination) {
            this.board.add(proposedCombination);
    }

    public boolean isFinished() {
        return this.board.isFinished();
    }

    public boolean isWinner() {
        return this.board.isWinner();
    }

    public int getAttempts() {
        return this.board.getAttempts();
    }

    public ProposedCombination getProposedCombination(int position) {
        return this.board.getProposedCombination(position);
    }

    public int getBlacks(int position) {
        return this.board.getBlacks(position);
    }

    public int getWhites(int position) {
        return this.board.getWhites(position);
    }

}
