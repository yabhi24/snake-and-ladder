package org.company.services;

import org.company.models.*;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class GameService {

    private final GameManager gameManager;
    private final BoardManager boardManager;
    private final PlayerManager playerManager;
    private final BoardEntityManager<Snake>  snakeManager;
    private final BoardEntityManager<Ladder>  ladderManager;
    private final DiceManager diceManager;

    public GameService(GameManager gameManager,
                       BoardManager boardManager,
                       PlayerManager playerManager, BoardEntityManager<Snake> snakeManager, BoardEntityManager<Ladder> ladderManager,
                       DiceManager diceManager) {
        this.gameManager = gameManager;
        this.boardManager = boardManager;
        this.playerManager = playerManager;
        this.snakeManager = snakeManager;
        this.ladderManager = ladderManager;
        this.diceManager = diceManager;
    }

    void start(String id) {
        // get the game
        Optional<Game> existingGame = gameManager.get(id);

        if (existingGame.isEmpty()) {
            throw new IllegalArgumentException("Game not found");
        }

        Game game = existingGame.get();

        // get the board
        Optional<Board> existingBoard = boardManager.getByGameId(game.getId());

        if (existingBoard.isEmpty()) {
            throw new IllegalArgumentException("Board not found");
        }

        Board board = existingBoard.get();

        // get the players
        List<Player> players = playerManager.getAllPlayers(game.getId());

        // get the dice
        List<Dice> dices = diceManager.get(game.getId());

        // push players in queue
        Queue<Player> playersQueue = new LinkedList<>(players);

        while(true) {
            // get the top player
            Player currentPlayer = playersQueue.poll();

            // roll the dice
            int rolledNumber = dices.stream()
                    .map(dice -> diceManager.roll(game.getId(), dice.getId()))
                    .mapToInt(Integer::intValue)
                    .sum();

            int currentPosition = currentPlayer.getPosition();

            int updatedPosition = Math.min(currentPosition + rolledNumber, board.getSize());

            //get snake at this position
            Optional<Snake> snake = snakeManager.getByBoardIdAndStart(board.getId(), updatedPosition);

            if (snake.isPresent()) {
                updatedPosition = snake.get().getEnd();
            }

            //get ladder at this position
            Optional<Ladder> ladder = ladderManager.getByBoardIdAndStart(board.getId(), updatedPosition);

            if (ladder.isPresent()) {
                updatedPosition = ladder.get().getEnd();
            }

            // update its position
            playerManager.move(game.getId(), currentPlayer.getId(), updatedPosition);

            // check if it has reached end of board
            if (board.getSize() == updatedPosition) {
                System.out.println("Player %s has won" + currentPlayer.getName());
                break;
            }

            System.out.println("Player %s moves from %s to %s" + currentPlayer.getName() + currentPosition + updatedPosition);

            // if no, push him in back of queue and continue
            playersQueue.offer(currentPlayer);
        }
    }
}
