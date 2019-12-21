import java.util.*;
import java.io.*;

public class CProject
{
	public static void main(String args[]) throws FileNotFoundException
	{
		Scanner r = new Scanner(System.in);
		Scanner info = new Scanner(new FileReader("ElfInfo.txt"));
		Scanner time = new Scanner(new FileReader("ToyConstructionTime.txt"));
		Scanner list = new Scanner(new FileReader("ToyList.txt"));
		Scanner orders = new Scanner(new FileReader("ToyOrders.txt"));
		List<String> elfID = new ArrayList<String>();
		List<String> toys = new ArrayList<String>();
		List<String> start = new ArrayList<String>();
		List<String> end = new ArrayList<String>();
		List<String> wage = new ArrayList<String>();
		String[] input = {""};
		String temp;

		while(info.hasNext())
		{
			temp = info.nextLine();
			input = (temp.split(" "));
			elfID.add(input[0]);
			toys.add(input[1]);
			start.add(input[2]);
			end.add(input[3]);
			wage.add(input[4]);
		}
		System.out.println("Elf\tGifts\t\tStart\tEnd\tPay");
		for(int i = 0; i < 5; i++)
		{
			System.out.print(elfID.get(i) + "\t");
			System.out.print(toys.get(i) + "\t");
			System.out.print(start.get(i) + "\t");
			System.out.print(end.get(i) + "\t");
			System.out.print(wage.get(i));
			System.out.println();
		}
		fullPay(start, end, wage);
	}
	public static void fullPay(List<String> start, List<String> end, List<String> wage)
	{

		int hours = 0;
		int elfPay = 0;
		for(int i = 0; i < 5; i++)
		{
			hours = Integer.parseInt(end.get(i)) - Integer.parseInt(start.get(i));
			elfPay = Integer.parseInt(wage.get(i)) * hours;
			System.out.println("Elf pay rate: " + elfPay);
		}
	}
}
