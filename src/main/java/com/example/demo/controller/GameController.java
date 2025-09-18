package com.example.demo.controller;

import com.example.demo.exception.GameNotFoundException;
import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable String id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game not found with ID: " + id));
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @PutMapping("/{id}")
    public Game updateGame(@PathVariable String id, @RequestBody Game gameDetails) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game not found with ID: " + id));
        game.setName(gameDetails.getName());
        game.setType(gameDetails.getType());
        return gameRepository.save(game);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable String id) {
        gameRepository.deleteById(id);
    }
}
