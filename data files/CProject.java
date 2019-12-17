import java.util.*;
import java.io.*;
//Jose Was here

public class CProject
{
	public static void main(String args[]) throws FileNotFoundException
	{
		Scanner r = new Scanner(System.in);
		Scanner info = new Scanner(new FileReader("ElfInfo.txt"));
		List<String> elf = new ArrayList<String>();
		String[] input = {""};
		String temp;

		while(info.hasNext())
		{
			temp = info.nextLine();
			input = (temp.split(" "));
			for(int i = 0; i < input.length; i++)
				elf.add(input[i]);
		}
		System.out.println("Elf\tGifts\t\tStart\tEnd\tPay Per Hour");
		for(int i = 0; i < 25; i++)
		{
			System.out.print(elf.get(i) + "\t");
			if((i+1) % 5 == 0)
				System.out.println();
		}
	}
}