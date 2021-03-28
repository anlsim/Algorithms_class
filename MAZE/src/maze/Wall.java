package maze;


import java.awt.*;

public class Wall {

    private Cell x;
    private Cell y;
    private boolean isHorizontal;

    public Wall(Cell x, Cell y){
        this.x= x;
        this.y = y;
        isHorizontal = false;
    }

    public Wall(Cell x, Cell y, boolean isHorizontal){
        this.x= x;
        this.y = y;
        this.isHorizontal = isHorizontal;
    }

    public Cell getX(){
        return this.x;
    }
    public Cell getY(){
        return this.y;
    }

    public boolean isHorizontal() {
        return this.isHorizontal;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}