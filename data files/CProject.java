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
			elfID.add(input[0]);
			toys.add(Integer.parseInt(input[1]));
			start.add(Integer.parseInt(input[2]));
			end.add(Integer.parseInt(input[3]));
			wage.add(Integer.parseInt(input[4]));
		}

		List<Integer> weeklyPay = fullPay(start, end, wage);


	}
	public static List<Integer> fullPay(List<Integer> start, List<Integer> end, List<Integer> wage)
	{
		List<Integer> total = new ArrayList<Integer>();
		int hours = 0;
		int elfPay = 0;
		for(int i = 0; i < 5; i++)
		{
			hours = end.get(i) - start.get(i);
			elfPay = wage.get(i) * hours;
			total.add(elfPay);
		}
		return total;
	}
}
