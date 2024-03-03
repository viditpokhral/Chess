package com.chess.engine.board;

import com.chess.engine.piece.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Tiles {   // Abstract class to represent tiles
    protected final int tileCoordinate; // Tile coordinate variable to store the tile coordinate
    private static final Map<Integer,EmptyTile> EMPTY_TILE_MAP = createAllPossibleEmptyTiles();
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap=new HashMap<>();
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);// I want the immutable map(it is a container)
        // ImmutableMap is in guava(google) library
        //return Collections.unmodifiableMap(emptyTileMap); // could also be used
    }
    public static Tiles createTile(final int tileCoordinate, final Piece piece){
        return piece!=null ? new OccupiedTile(tileCoordinate, piece): EMPTY_TILE_MAP.get(tileCoordinate);
    }
    private Tiles(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }
    public abstract boolean isTileOccupied();   // Abstract methods to check if tile is occupied and to get the piece on the tile
    public abstract Piece getPiece();        // Abstract methods to check if tile is occupied and to get the piece on the tile
    public static final class EmptyTile extends Tiles { // Empty tile class to represent empty tiles
        private EmptyTile(final int tileCoordinate) {   // Constructor for empty tile
            super(tileCoordinate);  // Call the constructor of the parent class
        }
        @Override
        public boolean isTileOccupied() {   // Overriding abstract methods to return false
            return false;
        }
        @Override
        public Piece getPiece() {   // Overriding abstract methods to return null
            return null;
        }
    }
    public static final class OccupiedTile extends Tiles {  // Occupied tile class to represent occupied tiles
        private final Piece pieceOnTile;  // Piece on the tile
        private OccupiedTile(final int tileCoordinate, final Piece pieceOnTile) {  // Constructor for occupied tile
            super(tileCoordinate);  // Call the constructor of the parent class
            this.pieceOnTile = pieceOnTile; // Set the piece on the tile
        }
        @Override
        public boolean isTileOccupied() {   // Overriding abstract methods to return true
            return true;
        }
        @Override
        public Piece getPiece() {   // Overriding abstract methods to return the piece on the tile
            return pieceOnTile;
        }
    }
}
