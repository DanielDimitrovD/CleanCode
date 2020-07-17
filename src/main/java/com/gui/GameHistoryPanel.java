package com.gui;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.moves.Move;
import com.engine.player.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameHistoryPanel extends JPanel {

    private static final Dimension HISTORY_PANEL_DIMENSION = new Dimension(100, 40);
    private final DataModel model;
    private final JScrollPane scrollPane;

    GameHistoryPanel() {
        this.setLayout(new BorderLayout());
        this.model = new DataModel();
        final JTable table = new JTable(model);
        table.setRowHeight(15);
        this.scrollPane = new JScrollPane(table);
        scrollPane.setColumnHeaderView(table.getTableHeader());
        scrollPane.setPreferredSize(HISTORY_PANEL_DIMENSION);

        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void redo(final Board board, final Table.MoveLog moveHistory) {
        int currentRow = 0;
        this.model.clear();
        for (final Move move : moveHistory.getMoves()) {
            final String moveText = move.toString();
            final Alliance movedPieceAlliance = move.getMovedPiece().getPieceAlliance();
            if (movedPieceAlliance.isWhite()) {
                this.model.setValueAt(moveText, currentRow, 0);
            } else if (movedPieceAlliance.isBlack()) {
                this.model.setValueAt(moveText, currentRow, 1);
                currentRow++;
            }
        }

        // check if king is in check
        if (moveHistory.getMoves().size() > 0) {
            final Move lastMove = moveHistory.getMoves().get(moveHistory.size() - 1);
            final String moveText = lastMove.toString();
            final Alliance movedPieceAlliance = lastMove.getMovedPiece().getPieceAlliance();

            if (movedPieceAlliance.isWhite()) {
                this.model.setValueAt(moveText + calculateCheckAndMateHash(board), currentRow, 0);
            } else if (movedPieceAlliance.isBlack()) {
                this.model.setValueAt(moveText + calculateCheckAndMateHash(board), currentRow - 1, 1);
            }

        }

        final JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    private String calculateCheckAndMateHash(final Board board) {
        final Player currentPlayer = board.getCurrentPlayer();

        if (currentPlayer.isInCheckMate()) {
            return "#";
        } else if (currentPlayer.isInCheck()) {
            return "+";
        }
        return "";
    }


    private static class DataModel extends DefaultTableModel {

        private static final String[] NAMES = {"White", "Black"};
        private final List<Row> values;

        DataModel() {
            this.values = new ArrayList<>();
        }

        public void clear() {
            this.values.clear();
            setRowCount(0);
        }

        @Override
        public int getRowCount() {
            if (this.values == null) {
                return 0;
            }
            return this.values.size();
        }

        @Override
        public int getColumnCount() {
            return NAMES.length;
        }

        @Override
        public Object getValueAt(final int row, final int column) {
            final Row currentRow = this.values.get(row);
            if (column == 0) {
                return currentRow.getWhiteMove();
            } else if (column == 1) {
                return currentRow.getBlackMove();
            }
            return null;
        }

        @Override
        public void setValueAt(final Object aValue, final int row, final int column) {
            final Row currentRow;
            if (this.values.size() <= row) {
                currentRow = new Row();
                this.values.add(currentRow);
            } else {
                currentRow = this.values.get(row);
            }

            if (column == 0) {
                currentRow.setWhiteMove((String) aValue);
                fireTableRowsInserted(row, row);
            } else if (column == 1) {
                currentRow.setBlackMove((String) aValue);
                fireTableCellUpdated(row, column);
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Move.class;
        }

        @Override
        public String getColumnName(int column) {
            return NAMES[column];
        }
    }

    private static class Row {

        private String whiteMove;
        private String blackMove;

        Row() { }

        public String getWhiteMove() {
            return whiteMove;
        }

        public void setWhiteMove(String whiteMove) {
            this.whiteMove = whiteMove;
        }

        public String getBlackMove() {
            return blackMove;
        }

        public void setBlackMove(String blackMove) {
            this.blackMove = blackMove;
        }
    }
}
