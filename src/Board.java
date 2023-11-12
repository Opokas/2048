import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private Tile[][] board;
    private int gridSize;

    GameManager gameManager;

    public Board(int gridSize, GameManager gameManager) {
        this.board = new Tile[gridSize][gridSize];
        this.gridSize = gridSize;
        this.gameManager = gameManager;
    }


    public void start() {
        for (int x = 0; x < this.gridSize; x++) {
            for (int y = 0; y < this.gridSize; y++) {
                this.board[x][y] = new Tile(x, y, 0);
            }
        }
        randomTile();//Sukuria pirma atsitiktinti tile
        randomTile();
       // testTiles();
    }

    public void testTiles() {
//        this.board[0][2].setValue(2);
//        this.board[1][2].setValue(2);
//        this.board[2][2].setValue(4);
//        this.board[3][2].setValue(8);
//        this.board[3][0].setValue(2);
//        this.board[3][1].setValue(2);
//    this.board[5][2].setValue(16);
    }

    public int findFurthestByColUp(int row, int col) {
        int furthestY = -1;
        if (row == 0) {
            return furthestY;
        }
        for (int i = row - 1; i >= 0; i--) {
            if (this.board[i][col].getValue() != 0) {
                furthestY = i;
                break;
            }
        }
        return furthestY;
    }

    public void moveUP() {

        for (int col = 0; col < this.gridSize; col++) {
            for (int row = this.gridSize - 1; row > 0; row--) {
                if (this.board[row][col].getValue() != 0) {
                    int furthestY = findFurthestByColUp(row, col);
                    if (furthestY != -1) {
                        if (this.board[row][col].getValue() == this.board[furthestY][col].getValue() && !this.board[row][col].isMerged() && !this.board[furthestY][col].isMerged()) {
                            this.board[furthestY][col].addValue(this.board[row][col].getValue());
                            this.board[row][col].setValue(0);
                            gameManager.addScore(this.board[furthestY][col].getValue());
                        }
                    }

                }
            }
        }

//        System.out.println("PO MERGO");
//        gameManager.showBoard();
//        System.out.println("-----------------------------");
        for (int col = 0; col < this.gridSize; col++) {
            int avaliablePos = 0;
            for (int startPos = 0; startPos < this.gridSize; startPos++) {//surasti artimiausia tuscia laukeli paslinkimui
                if (this.board[avaliablePos][col].getValue() != 0) {
                    avaliablePos++;
                }
            }
            for (int startPos = 1; startPos < this.gridSize; startPos++) {
                if (this.board[startPos][col].getValue() != 0 && this.board[startPos - 1][col].getValue() == 0) {
                    this.board[avaliablePos][col].addValue(this.board[startPos][col].getValue());
                    this.board[startPos][col].setValue(0);
                    avaliablePos++;
                }
            }
        }
    }


public int findFurthestByColDown(int row,int col) {
    int furthestY = -1;
    if (row == this.gridSize - 1) {
        return furthestY;
    }
    for (int i = row + 1; i < this.gridSize; i++) {
        if (this.board[i][col].getValue() != 0) {
            furthestY = i;
            break;
        }
    }
    return furthestY;
}

    public void moveDown() {
        for (int col = 0; col < this.gridSize; col++) {
            for (int row = 0; row < this.gridSize - 1; row++) {//netikrinti uzribio
                if (this.board[row][col].getValue() != 0) {
                    int furthestY = findFurthestByColDown(row, col);
                    if (furthestY != -1) {
                        if (this.board[row][col].getValue() == this.board[furthestY][col].getValue() && !this.board[row][col].isMerged() && !this.board[furthestY][col].isMerged()) {
                            this.board[furthestY][col].addValue(this.board[row][col].getValue());
                            this.board[row][col].setValue(0);
                            gameManager.addScore(this.board[furthestY][col].getValue());
                        }
                    }

                }
            }
        }

        for (int col = 0; col < this.gridSize; col++) {
            int avaliablePos = this.gridSize - 1;
            for (int startPos = this.gridSize - 1; startPos >= 0; startPos--) {//surasti artimiausia tuscia laukeli paslinkimui
                if (this.board[avaliablePos][col].getValue() != 0) {
                    avaliablePos--;
                }
            }
            for (int startPos = this.gridSize - 2; startPos >= 0; startPos--) {//start nuo apacios
                if (this.board[startPos][col].getValue() != 0 && this.board[startPos + 1][col].getValue() == 0) {
                    this.board[avaliablePos][col].setValue(this.board[startPos][col].getValue());
                    this.board[startPos][col].setValue(0);
                    avaliablePos--;
                }
            }
        }
    }


    public void moveLeft(){
        for (int row = 0; row < this.gridSize; row++) {
            for (int col = this.gridSize - 1; col > 0; col--) {//netikrinti uzribio
                if (this.board[row][col].getValue() != 0) {
                    int furthestX = findFurthestByRowLeft(row, col);
                    if (furthestX != -1) {
                        if (this.board[row][col].getValue() == this.board[row][furthestX].getValue() && !this.board[row][col].isMerged() && !this.board[row][furthestX].isMerged()) {
                            this.board[row][furthestX].addValue(this.board[row][col].getValue());
                            this.board[row][col].setValue(0);
                            gameManager.addScore(this.board[row][furthestX].getValue());
                        }
                    }

                }
            }
        }

        for (int row = 0; row < this.gridSize; row++) {
            int avaliablePos = 0;
            for (int startPos = 0; startPos < this.gridSize; startPos++) {//surasti artimiausia tuscia laukeli paslinkimui
                if (this.board[row][avaliablePos].getValue() != 0) {
                    avaliablePos++;
                }
            }
            for (int startPos = 1; startPos < this.gridSize; startPos++) {
                if (this.board[row][startPos].getValue() != 0 && this.board[row][startPos - 1].getValue() == 0) {
                    this.board[row][avaliablePos].setValue(this.board[row][startPos].getValue());
                    this.board[row][startPos].setValue(0);
                    avaliablePos++;
                }
            }
        }
    }

public int findFurthestByRowLeft(int row,int col) {
    int furthestX = -1;
    if (col == 0) {
        return furthestX;
    }
    for (int i = col - 1; i >= 0; i--) {
        if (this.board[row][i].getValue() != 0) {
            furthestX = i;
            break;
        }
    }
    return furthestX;
}

public void moveRight(){
    for (int row = 0; row < this.gridSize; row++) {
        for (int col = 0; col < this.gridSize - 1; col++) {//netikrinti uzribio
            if (this.board[row][col].getValue() != 0) {
                int furthestX = findFurthestByRowRight(row, col);
                if (furthestX != -1) {
                    if (this.board[row][col].getValue() == this.board[row][furthestX].getValue() && !this.board[row][col].isMerged() && !this.board[row][furthestX].isMerged()) {
                        this.board[row][furthestX].addValue(this.board[row][col].getValue());
                        this.board[row][col].setValue(0);
                        gameManager.addScore(this.board[row][furthestX].getValue());
                    }
                }

            }
        }
    }

    for (int row = 0; row < this.gridSize; row++) {
        int avaliablePos = this.gridSize - 1;
        for (int startPos = this.gridSize - 1; startPos >= 0; startPos--) {//surasti artimiausia tuscia laukeli paslinkimui
            if (this.board[row][avaliablePos].getValue() != 0) {
                avaliablePos--;
            }
        }
        for (int startPos = this.gridSize - 2; startPos >= 0; startPos--) {
            if (this.board[row][startPos].getValue() != 0 && this.board[row][startPos + 1].getValue() == 0) {
                this.board[row][avaliablePos].setValue(this.board[row][startPos].getValue());
                this.board[row][startPos].setValue(0);
                avaliablePos--;
            }
        }
    }
}
   public int findFurthestByRowRight(int row,int col) {
    int furthestX = -1;
    if (col == this.gridSize - 1) {
        return furthestX;
    }
    for (int i = col + 1; i < this.gridSize; i++) {
        if (this.board[row][i].getValue() != 0) {
            furthestX = i;
            break;
        }
    }
    return furthestX;
}
    public void randomTile(){
        Random random = new Random();
        List<Tile> emptyTiles=findRandomEmptyTile();
        if (emptyTiles.isEmpty()){
            System.out.println("GAME OVER");
            gameManager.GameOver=true;
            return;
        }
        int randomint=random.nextInt(0,emptyTiles.size());
        Tile currentTile=emptyTiles.get(randomint);

        if(random.nextInt(10)>8){
            currentTile.setValue(4);
        }else {
            currentTile.setValue(2);
        }
    }

public List<Tile> findRandomEmptyTile(){
    List<Tile> emptyTiles=new ArrayList<>();
    for (int x=0;x<this.gridSize;x++){
        for (int y=0;y<this.gridSize;y++){
            if (this.board[x][y].getValue()==0){
                emptyTiles.add(this.board[x][y]);
            }
        }
    }

    return emptyTiles;
}


    public void setAllTilesNotMerged(){
        for (int x=0;x<this.gridSize;x++){
            for (int y=0;y<this.gridSize;y++){
                this.board[x][y].setMerged(false);
            }
        }
    }
    public Tile getTile(int x,int y){
        return this.board[x][y];
    }

    public void setTile(Tile tile){
        this.board[tile.getY()][tile.getX()]=tile;
    }
    public Tile[][] getBoard() {
        return board;
    }
}
