public class GameManager {

    Board board;
    int gridSize;
    int Score=0;
    boolean GameOver=false;

    public GameManager(int gridSize){
        this.board=new Board(gridSize,this);
        this.gridSize=gridSize;
       board.start();
        showBoard();
    }

    public void getInput(String userInput,boolean gameOver){
        if (gameOver){
            System.out.println("Game Over");
            return;
        }

        switch (userInput) {
            case "w", "W" -> {
                board.moveUP();
                ValidMove();
            }
            case "s", "S" -> {
                board.moveDown();
                ValidMove();
            }
            case "a", "A" -> {
                board.moveLeft();
                ValidMove();
            }
            case "d", "D" -> {
                board.moveRight();
                ValidMove();
            }
            default -> System.out.println("Blogas pasirinkimas");
        }

    }
    public void ValidMove(){
        board.randomTile();
        showBoard();
        board.setAllTilesNotMerged();
    }

    public void showBoard(){
        Tile [][] currentBoard=this.board.getBoard();
        for (int x=0;x<this.gridSize;x++){
            for (int y=0;y<this.gridSize;y++){System.out.print(" | "+currentBoard[x][y].getValue()+" |");
            }
            System.out.println();
        }
        System.out.println("Score: "+this.Score);
    }

    public void addScore(int value){
        this.Score+=value;
    }

}
