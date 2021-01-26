package usantatecla.mastermind.views.console;

import usantatecla.mastermind.models.Board;
import usantatecla.mastermind.views.Message;
import usantatecla.mastermind.views.WithBoardView;

public class StartView extends WithBoardView {

    StartView(Board board) {
        super(board);
    }

    void interact() {
        Message.TITLE.writeln();
        new BoardView().write(this.board);
    }

}
