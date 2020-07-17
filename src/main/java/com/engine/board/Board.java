package com.engine.board;

import com.engine.Alliance;
import com.engine.moves.Move;
import com.engine.moves.moveType.MoveFactory;
import com.engine.pieces.*;
import com.engine.player.BlackPlayer;
import com.engine.player.Player;
import com.engine.player.WhitePlayer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private final Map<Integer, Piece> boardConfig;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;

    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;

    private final Player currentPlayer;

    private final Pawn enPassantPawn;
    private final Move transitionMove;


    Board(final BoardBuilder boardBuilder) {
        this.boardConfig = Collections.unmodifiableMap(boardBuilder.boardConfig);
        this.enPassantPawn = boardBuilder.enPassantPawn;

        this.whitePieces = calculateActivePieces(boardBuilder, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(boardBuilder, Alliance.BLACK);

        final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
        this.blackPlayer = new BlackPlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);

        this.currentPlayer = boardBuilder.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);

        this.transitionMove = boardBuilder.transitionMove != null ? boardBuilder.transitionMove : MoveFactory.getNullMove();

    }

    private static String prettyPrint(final Piece piece) {
        if (piece != null) {
            return piece.getPieceAlliance().isBlack() ?
                    piece.toString().toLowerCase() : piece.toString();
        }
        return "-";
    }

    public static Board createStandardBoard() {
        final BoardBuilder boardBuilder = new BoardBuilder();
        initializeRooks(boardBuilder);
        initializeKnights(boardBuilder);
        initializeBishops(boardBuilder);
        initializePawns(boardBuilder);
        initializeQueens(boardBuilder);
        initializeKings(boardBuilder);

        boardBuilder.setMoveMaker(Alliance.WHITE);

        return boardBuilder.build();
    }

    private static Collection<Piece> calculateActivePieces(final BoardBuilder builder, final Alliance alliance) {
        return builder.boardConfig.values().stream()
                .filter(piece -> piece.getPieceAlliance() == alliance)
                .collect(Collectors.toList());
    }

    private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
        return pieces.stream().flatMap(piece -> piece.calculateLegalMoves((this)).stream())
                .collect(Collectors.toList());
    }

    public Collection<Piece> getAllPieces() {
        return Stream.concat(this.whitePlayer.getActivePieces().stream(), this.blackPlayer.getActivePieces().stream())
                .collect(Collectors.toList());
    }

    private static void initializeKings(BoardBuilder boardBuilder) {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, true, true));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, true, true));
    }

    private static void initializeQueens(BoardBuilder boardBuilder) {
        boardBuilder.setPiece(new Queen(3, Alliance.BLACK));
        boardBuilder.setPiece(new Queen(59, Alliance.WHITE));
    }

    private static void initializePawns(BoardBuilder boardBuilder) {
        final int BLACK_ALLIANCE_PAWN_STARTING_TILE = 8;
        final int BLACK_ALLIANCE_PAWN_ENDING_TILE = 15;

        final int WHITE_ALLIANCE_PAWN_STARING_TILE = 48;
        final int WHITE_ALLIANCE_PAWN_ENDING_TILE = 55;

        for (int i = BLACK_ALLIANCE_PAWN_STARTING_TILE; i <= BLACK_ALLIANCE_PAWN_ENDING_TILE; i++) {
            boardBuilder.setPiece(new Pawn(i, Alliance.BLACK));
        }

        for (int j = WHITE_ALLIANCE_PAWN_STARING_TILE; j <= WHITE_ALLIANCE_PAWN_ENDING_TILE; j++) {
            boardBuilder.setPiece(new Pawn(j, Alliance.WHITE));
        }
    }

    private static void initializeBishops(BoardBuilder boardBuilder) {
        boardBuilder.setPiece(new Bishop(2, Alliance.BLACK));
        boardBuilder.setPiece(new Bishop(5, Alliance.BLACK));

        boardBuilder.setPiece(new Bishop(58, Alliance.WHITE));
        boardBuilder.setPiece(new Bishop(61, Alliance.WHITE));
    }

    private static void initializeKnights(BoardBuilder boardBuilder) {
        boardBuilder.setPiece(new Knight(1, Alliance.BLACK));
        boardBuilder.setPiece(new Knight(6, Alliance.BLACK));

        boardBuilder.setPiece(new Knight(57, Alliance.WHITE));
        boardBuilder.setPiece(new Knight(62, Alliance.WHITE));
    }

    private static void initializeRooks(BoardBuilder boardBuilder) {
        boardBuilder.setPiece(new Rook(0, Alliance.BLACK));
        boardBuilder.setPiece(new Rook(7, Alliance.BLACK));

        boardBuilder.setPiece(new Rook(56, Alliance.WHITE));
        boardBuilder.setPiece(new Rook(63, Alliance.WHITE));

    }

    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    public Player getBlackPlayer() {
        return this.blackPlayer;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            final String tileText = prettyPrint(this.boardConfig.get(i));
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public Piece getPiece(final int coordinate) {
        return this.boardConfig.get(coordinate);
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Collection<Move> getAllLegalMoves() {
        return Stream.concat(this.whitePlayer.getLegalMoves().stream(), this.blackPlayer.getLegalMoves().stream()).collect(Collectors.toList());
    }

    public Pawn getEnPassantPawn() {
        return this.enPassantPawn;
    }

}
