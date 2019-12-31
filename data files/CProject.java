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
		List<Integer> elfID = new ArrayList<Integer>();
		List<Integer> toys = new ArrayList<Integer>();
		List<Integer> start = new ArrayList<Integer>();
		List<Integer> end = new ArrayList<Integer>();
		List<Integer> wage = new ArrayList<Integer>();
		String[] input = {""};
		String temp;

		while(info.hasNext())
		{
			temp = info.nextLine();
			input = (temp.split(" "));
			elfID.add(Integer.parseInt(input[0]));
			toys.add(Integer.parseInt(input[1]));
			start.add(Integer.parseInt(input[2]));
			end.add(Integer.parseInt(input[3]));
			wage.add(Integer.parseInt(input[4]));
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
	public static void fullPay(List<Integer> start, List<Integer> end, List<Integer> wage)
	{

		int hours = 0;
		int elfPay = 0;
		for(int i = 0; i < 5; i++)
		{
			hours = end.get(i) - start.get(i);
			elfPay = wage.get(i) * hours;
			System.out.println("Elf pay: " + elfPay);
		}
	}
}
