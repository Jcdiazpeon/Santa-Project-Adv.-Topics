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
		List<String> pay = new ArrayList<String>();
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
			pay.add(input[4]);

		}
		System.out.println("Elf\tGifts\t\tStart\tEnd\tPay");
		for(int i = 0; i < 5; i++)
		{
			if((i+1) % 5 == 0)
				System.out.println();
		}

	}
	//public static void
}
