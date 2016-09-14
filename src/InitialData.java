/**
 * Created by Bogdan on 12/09/2016.
 */

import java.util.Calendar;
import java.util.Scanner;

public class InitialData {

    private double cashInValue;
    private Calendar calendar;

    public double getCashInValue() {
        return cashInValue;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public InitialData(){
        System.out.print("Introduceti suma incasata: ");
        Scanner scan = new Scanner(System.in);
        cashInValue = scan.nextDouble();

        calendar = Calendar.getInstance();

        System.out.print ("Introduceti ziua incasarii: ");
        int day = scan.nextInt();
        calendar.set(Calendar.DAY_OF_MONTH, day);

        System.out.print ("Introduceti luna incasarii: ");
        int month = scan.nextInt();
        calendar.set(Calendar.MONTH, month);

        System.out.print ("Introduceti anul incasarii: ");
        int year = scan.nextInt();
        calendar.set(Calendar.YEAR, year);
    }
}
