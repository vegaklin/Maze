package backend.academy.maze.factory;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.generation.kruskal.KruskalsGenerator;
import backend.academy.maze.algorithm.generation.prim.PrimsGenerator;

public class MazeGeneratorFactory {
    public static Generator createMazeGenerator(GeneratorType type) {
        return switch (type) {
            case KRUSKAL -> new KruskalsGenerator();
            case PRIM -> new PrimsGenerator();
        };
    }
}
