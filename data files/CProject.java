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
		List<String> elfID = new ArrayList<String>(); //Bit String
		List<String> toysWorkedOn = new ArrayList<String>(); //Bit String
		List<Integer> start = new ArrayList<Integer>(); //Time (/24)
		List<Integer> end = new ArrayList<Integer>(); //Time (/24)
		List<Integer> wage = new ArrayList<Integer>(); //Int

		List<String> items = new ArrayList<String>(); //ToyList (String)
		List<Integer> toyTime = new ArrayList<Integer>();//ToyConstructionTime
		List<Integer> numOfItemsCompleted = new ArrayList<Integer>(); //Calculated
		List<Integer> hoursWorkedOnEachToy = new ArrayList<Integer>();
		String[] input = {""};
		String temp;

		while(info.hasNext())
		{
			temp = info.nextLine();
			input = (temp.split(" "));
			elfID.add(input[0]);
			toysWorkedOn.add(input[1]);
			start.add(Integer.parseInt(input[2]));
			end.add(Integer.parseInt(input[3]));
			wage.add(Integer.parseInt(input[4]));
		}

		while(time.hasNext())
		{
			temp = time.nextLine();
			temp = temp.replace(" ", "");
			input = temp.split(",");

		}
		while(list.hasNext())
		{
			temp = list.nextLine();
			items.add(temp);
		}
		for(int i = 0; i < 10; i++)
		{
			toyTime.add(Integer.parseInt(input[i]));
		}


		List<Integer> weeklyPay = fullPay(start, end, wage);
		hoursWorkedOnEachToy = calcHoursWorked(start, end, toysWorkedOn, toyTime);
		for(int i = 0; i < hoursWorkedOnEachToy.size(); i++){
			System.out.println(hoursWorkedOnEachToy.get(i));
		}
		//numOfItemsCompleted = calcItemsMade(toysWorkedOn, hoursWorkedOnEachToy);

		//tTime(toyTime, items);
		//numElves(items);

	}

	//Returns how much each elf is paid per week with the hours workd
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

	//Calculates the hours worked on each toy for all elves
	public static List<Integer> calcHoursWorked(List<Integer> start, List<Integer> end, List<String> toysWorkedOn, List<Integer> toyTime){
		List<Integer> results = new ArrayList<Integer>();
		List<Integer> hoursLeftPerElf = new ArrayList<Integer>();

		//Finds the hours each elf works
		for(int i = 0; i < start.size(); i++){
			int hours = end.get(i) - start.get(i);
			hoursLeftPerElf.set(i, hours);
		}


		for(int i = 0; i < start.size(); i++){
			for(int j = 0; j < toysWorkedOn.get(i).length(); j++){

				if(toysWorkedOn.get(i).charAt(j) == '1'){ //Elf works on this item

					if(hoursLeftPerElf.get(j) >= toyTime.get(i)){ //Has enough time to make it
						if(results.get(j) != null) //Already had time spent on it
							results.set(j, results.get(j) + toyTime.get(j));
						else
							results.set(j, toyTime.get(j)); //First time working on item

						hoursLeftPerElf.set(j, hoursLeftPerElf.get(j) - toyTime.get(j));
					}
				}
			}
		}
		return results;
	}

	public static void tTime(List<Integer> toyTime, List<String> items)  //Just prints the time to the screen of each item
	{
		for(int i = 0; i < 10; i++)
			System.out.println(items.get(i) + "\t" + toyTime.get(i));
	}

	public static void numElves(List<String> toys)
	{
		List<Integer> total = new ArrayList<Integer>();
		char[] temp;
		String tem;
		for(int i = 0; i < 10; i++)
			total.add(0);
		for(int i = 0; i < 4; i++){
			tem = (toys.get(i));
			temp = tem.toCharArray();

			for(int j = 0; j < 10; j++)
				total.set(j, total.get(j) + Integer.parseInt(String.valueOf(temp[j])));

		}
		System.out.println(total);
	}
}
