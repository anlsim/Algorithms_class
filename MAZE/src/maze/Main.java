package maze;

public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(30, 30);

        maze.decimateWalls();
        maze.drawWalls();

    }
}
