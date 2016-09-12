/**
 * Created by Bogdan on 12/09/2016.
 */

import java.time.LocalDate;
import java.util.Scanner;

public class Input {

    Scanner scan = new Scanner(System.in);

    System.out.println("Introduceti suma incasata:");
    Double cashInValue = scan.nextDouble();

    Scanner scan2 = new Scanner(System.in);
         System.out.println("Introduceti ziua incasarii:");
    int day = scan2.nextInt();

    Scanner scan3 = new Scanner(System.in);
        System.out.println("Introduceti luna incasarii:");

    int month = scan3.nextInt();

    Scanner scan4 = new Scanner(System.in);
            System.out.println("Introduceti anul incasarii:");
    int year = scan4.nextInt();

    LocalDate date = LocalDate.of(int year, int month, int day);

}
