package app.model;

import com.google.gson.annotations.SerializedName;

import java.util.*;

/**
 * Represents the leaderboard.
 */
public class Leaderboard {
    private transient static final int LIMIT = 5;
    private final Set<LeaderboardEntry> entries;

    /**
     * Create a new leaderboard.
     */
    public Leaderboard() {
        entries = new HashSet<>();
    }

    /**
     * Create a new leaderboard.
     */
    public Leaderboard(Set<LeaderboardEntry> entries) {
        this.entries = entries;
    }

    /**
     * Add an entry to the leaderboard.
     *
     * @param entry the entry
     */
    public void addEntry(LeaderboardEntry entry) {
        if (entries.size() == LIMIT)
            entries.remove(0);
        entries.add(entry);
    }

    /**
     * Get the list of entries.
     *
     * @return array list of entries.
     */
    public Set<LeaderboardEntry> getEntries() {
        return entries;
    }

    /**
     * Represents a leaderboard entry.
     */
    public static class LeaderboardEntry {
        private final Date gameDate;
        private final String player1;
        private final String player2;
        private final String winner;
        private final int turns;

        /**
         * Create a new leaderboard entry
         *
         * @param gameDate the date of the game
         * @param player1  name of player 1
         * @param player2  name of player 2
         * @param winner   name of the winner
         * @param turns    number of turns played
         */
        public LeaderboardEntry(Date gameDate, String player1, String player2, String winner, int turns) {
            this.gameDate = gameDate;
            this.player1 = player1;
            this.player2 = player2;
            this.winner = winner;
            this.turns = turns;
        }

        /**
         * Get the game date
         *
         * @return the game date
         */
        public Date getGameDate() {
            return gameDate;
        }

        /**
         * Get the name of the player 1
         *
         * @return the name of the player 1
         */
        public String getPlayer1() {
            return player1;
        }

        /**
         * Get the name of the player 2
         *
         * @return the name of the player 2
         */
        public String getPlayer2() {
            return player2;
        }

        /**
         * Get the name of the winner
         *
         * @return the name of the winner
         */
        public String getWinner() {
            return winner;
        }

        /**
         * Get the number of turns played
         *
         * @return the number of turns played
         */
        public int getTurns() {
            return turns;
        }
    }
}
