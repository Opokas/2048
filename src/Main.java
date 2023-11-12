// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);

        String input = null;
        System.out.println("Iveskite lentos dydi (Int) ");
        int gridsize= 4; //Integer.parseInt(userInput.nextLine());
        GameManager gameManager=new GameManager(gridsize);
        while (!Objects.equals(input, "q")) {
            input = userInput.nextLine();
            gameManager.getInput(input,gameManager.GameOver);
        }

        System.out.println("Ate ;)");
    }
}