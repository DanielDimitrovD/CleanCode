package com.gui;

import com.engine.moves.Move;
import com.engine.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.gui.Table.MoveLog;

public class TakenPiecesPanel extends JPanel {

    private static final EtchedBorder PANEL_BORDER = new EtchedBorder(EtchedBorder.RAISED);
    private static final Color PANEL_COLOR = Color.decode("0xFDFE6");
    private static final Dimension TAKEN_PIECES_DIMENSION = new Dimension(40, 80);
    private static String defaultImgPath = "img/misc/";

    private final JPanel northPanel;
    private final JPanel southPanel;

    public TakenPiecesPanel() {
        super(new BorderLayout());

        this.setBackground(PANEL_COLOR);
        this.setBorder(PANEL_BORDER);
        this.setPreferredSize(TAKEN_PIECES_DIMENSION);

        this.northPanel = new JPanel(new GridLayout(8, 2));
        this.northPanel.setBackground(PANEL_COLOR);
        this.add(this.northPanel, BorderLayout.NORTH);

        this.southPanel = new JPanel(new GridLayout(8, 2));
        this.southPanel.setBackground(PANEL_COLOR);
        this.add(this.southPanel, BorderLayout.SOUTH);
    }

    public void redo(final MoveLog moveLog) {
        this.southPanel.removeAll();
        this.northPanel.removeAll();

        final List<Piece> whiteTakenPieces = new ArrayList<>();
        final List<Piece> blackTakenPieces = new ArrayList<>();

        for (final Move move : moveLog.getMoves()) {
            if (move.isAttack()) {
                final Piece takenPiece = move.getAttackedPiece();
                if (takenPiece.getPieceAlliance().isWhite()) {
                    whiteTakenPieces.add(takenPiece);
                } else if (takenPiece.getPieceAlliance().isBlack()) {
                    blackTakenPieces.add(takenPiece);
                } else {
                    throw new RuntimeException("piece is not black or white!");
                }
            }
        }

        Collections.sort(whiteTakenPieces, (Comparator.comparingInt(Piece::getPieceValue)));
        Collections.sort(blackTakenPieces, (Comparator.comparingInt(Piece::getPieceValue)));

        addFiguresToMoveLogPanel(whiteTakenPieces, this.northPanel);
        addFiguresToMoveLogPanel(blackTakenPieces, this.southPanel);

        validate();
    }


    private void addFiguresToMoveLogPanel(final List<Piece> takenPieces, final JPanel panel) {
        for (final Piece takenPiece : takenPieces) {
            try {
                String imgFilePath = String.format("%s%s.gif", defaultImgPath, takenPiece.getPieceAlliance().toString().substring(0, 1)
                        + takenPiece.toString());
                final BufferedImage image = ImageIO.read(new File(imgFilePath));
                final ImageIcon icon = new ImageIcon(image);
                final JLabel imageLabel = new JLabel(icon);
                panel.add(imageLabel);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}