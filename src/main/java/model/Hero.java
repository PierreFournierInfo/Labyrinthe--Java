package main.java.model;

public class Hero {

    Labyrinthe laby;
    boolean[][] path;
    int x, y;

    public Hero(){
        laby = new Labyrinthe();
        path = new boolean[laby.getX()][laby.getY()];
        placeHero();
    }

    public void placeHero(){
        int i = 0;
        do{
            i++;
            this.x = i;
            this.y = 0;
            if(laby.getVal(i,0) == 1) path[i][0] = true;
        }while(laby.getVal(i,0) == 0 && i < laby.getX());
    }

    public void moveNext(boolean genre, char c){
        int count;
        if(genre){
            count = 2;
        }else{
            count = 1;
        }
        path[this.x][this.y] = false;
        switch (c){
            case 'z':
            if (this.x - count >= 0){
                if(laby.getVal(this.x - count, y) != 0) this.x -= count;
            }
            break;
            case 's':
            if (this.x + count < laby.getX()){
                if(laby.getVal(this.x + count, y) != 0) this.x += count;
            }
            break;
            case 'q':
                if (this.y - count >= 0){
                if(laby.getVal(this.x, y - count) != 0) this.y -= count;
            }
            break;
            case 'd':
            if (this.x - count < laby.getY()){
                if(laby.getVal(this.x, y + count) != 0) this.y += count;
            }
            break;
        }
        path[this.x][this.y] = true;
    }

    public boolean getVal(int x, int y){
        return this.path[x][y];
    }

}
