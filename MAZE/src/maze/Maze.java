package maze;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Maze {

    public Cell[][] gridMaze;
    private int width;
    private int height;
    private DisjointSet disjointSet;
    public Set<Wall> walls = new LinkedHashSet<>();


    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        buildMazeGrid();
        buildVerticalWalls();
        buildHorizontalWalls();
    }


    //Method to build Maze Grid
    public void buildMazeGrid (){
        int id = 0;
        gridMaze = new Cell[this.width][this.height];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                gridMaze[j][i] = new Cell(id);
                id++;
            }
        }
    }

    //Method to build Vertical walls
    public void buildVerticalWalls(){
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width-1; j++){
                walls.add(new Wall(gridMaze[j][i], gridMaze[j+1][i]));
            }
        }
    }

    //Method to build horizontal walls
    public void buildHorizontalWalls(){
        for(int i = 0; i < height-1; i++){
            for (int j = 0; j < width; j++){
                walls.add(new Wall(gridMaze[j][i], gridMaze[j][i+1],  true));
            }
        }
    }


    // Used pseudo code in the following link to implement this method.
    //https://en.wikipedia.org/wiki/Maze_generation_algorithm
    public void decimateWalls() {
        disjointSet = new DisjointSet(walls.size());
        List<Wall> sortingList = new ArrayList<>(walls);
        Collections.shuffle(sortingList);

        for(Wall c : sortingList){
            int cell1 = disjointSet.find(c.getX().getId());
            int cell2 = disjointSet.find(c.getY().getId());
            if(cell1 != cell2){
                disjointSet.union(cell1, cell2);
                walls.remove(c);
            }
        }
    }

    public void drawWalls() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(100, 100, 900, 750);
        window.getContentPane().add(new MazeCanvas(this.width, this.height, this.walls));
        window.setVisible(true);
    }


}

