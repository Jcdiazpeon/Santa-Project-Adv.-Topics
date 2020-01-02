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
		List<Integer> toyOrders = new ArrayList<Integer>();

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

		while(orders.hasNext())
		{
			temp = orders.nextLine();
			temp = temp.replace(" ", "");
			input = temp.split(",");
			for(int i = 0; i < 10; i++)
				toyOrders.add(Integer.parseInt(input[i]));
		}

		List<Integer> weeklyPay = fullPay(start, end, wage);
		hoursWorkedOnEachToy = calcHoursWorked(start, end, toysWorkedOn, toyTime);

		int timeWastedByElf = hoursWorkedOnEachToy.remove(hoursWorkedOnEachToy.size() - 1);
		int totalHoursWasted = hoursWorkedOnEachToy.remove(hoursWorkedOnEachToy.size() - 1);
		int worstElf = hoursWorkedOnEachToy.remove(hoursWorkedOnEachToy.size() - 1); //removes the last elements which was the worst elf

		numOfItemsCompleted = calcItemsMade(toyTime, hoursWorkedOnEachToy); //List of items made per day

		System.out.println("Gifts in order: ");
		for(int i = 0; i < items.size(); i++){
			System.out.print(items.get(i) + "\t");
			if(i == items.size()/2 -1)
				System.out.println();
		}


		//First Order
		System.out.println("\n\nItems made on the first order: ");
		for(int i = 0; i < numOfItemsCompleted.size(); i++){
			System.out.print((numOfItemsCompleted.get(i) * 17) + "\t");
		}
		System.out.println("\nItems needed on the first order: ");
		for(int i = 0; i < 10; i++){
			System.out.print(toyOrders.get(i) + "\t");
		}

		//Second Order
		System.out.println("\n\nItems made on the second order: ");
		for(int i = 0; i < numOfItemsCompleted.size(); i++){
			System.out.print((numOfItemsCompleted.get(i) * 9) + "\t");
		}
		System.out.println("\nItems needed on the second order: ");
		for(int i = 10; i < 20; i++){
			System.out.print(toyOrders.get(i) + "\t");
		}

		//Third Order
		System.out.println("\n\nItems made on the third order: ");
		for(int i = 0; i < numOfItemsCompleted.size(); i++){
			System.out.print((numOfItemsCompleted.get(i) * 10) + "\t");
		}
		System.out.println("\nItems needed on the third order: ");
		for(int i = 20; i < 30; i++){
			System.out.print(toyOrders.get(i) + "\t");
		}

		System.out.println("\n\n\nPayment to each Elf per week: ");
		for(int i = 0; i < elfID.size(); i++)
			System.out.print(elfID.get(i) + "\t");

		System.out.println();

		for(int i = 0; i < elfID.size(); i++)
			System.out.print("$" + weeklyPay.get(i) + "\t");

		System.out.println("\n\nWorst Elf by time wasted: " + worstElf);
		System.out.println("Total Hours Wasted by all elfs per day: " + totalHoursWasted);
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
			hoursLeftPerElf.add(hours);
		}

		for(int i = 0; i < start.size(); i++){
			for(int j = 0; j < toysWorkedOn.get(i).length(); j++){
				if(toysWorkedOn.get(i).charAt(j) == '1'){ //Elf works on this item
					if(hoursLeftPerElf.get(i) >= toyTime.get(j)){ //Has enough time to make it
						if(j < results.size()) //Already had time spent on it
							results.set(j, results.get(j) + toyTime.get(j));
						else
							results.add(toyTime.get(j)); //First time working on item

						hoursLeftPerElf.set(i, hoursLeftPerElf.get(i) - toyTime.get(j));
					}
				}
			}
		}
		//Finds the worst elf by most time wasted
		float maxTimeWasted = 0;
		int indexOfWorst = 9999;
		int totalHoursWasted = 0;

		for(int i = 0; i < hoursLeftPerElf.size(); i++){  //Finds the max
			totalHoursWasted += hoursLeftPerElf.get(i);
			if(hoursLeftPerElf.get(i) >= maxTimeWasted){
				maxTimeWasted = hoursLeftPerElf.get(i);
				indexOfWorst = i;
			}
		}
		results.add(indexOfWorst);
		results.add(totalHoursWasted);
		results.add((int)maxTimeWasted);
		return results;
	}

	//returns the num of items made with the given hours worked on them PER DAY
	public static List<Integer> calcItemsMade(List<Integer> toyTime, List<Integer> hoursWorkedOnEachToy){
		List<Integer> results = new ArrayList<Integer>();

		for(int i = 0; i< hoursWorkedOnEachToy.size(); i++){
			results.add(hoursWorkedOnEachToy.get(i) / toyTime.get(i));  //Toys completed with the given time
		}

		return results;
	}

	public static List<Float> leftOverTime(List<Integer> toyTime, List<Integer> hoursWorkedOnEachToy){
			List<Float> results = new ArrayList<Float>();

			for(int i = 0; i< hoursWorkedOnEachToy.size(); i++){
				results.add((float)(hoursWorkedOnEachToy.get(i) % toyTime.get(i)));  //Toys completed with the given time
			}
			return results;
	}

	public static int mostUselessElf(List<Float> leftOvers){
		float maxTimeWasted = 0;
		int indexOfWorst = 9999;

		for(int i = 0; i < leftOvers.size(); i++){  //Finds the max
			if(leftOvers.get(i) >= maxTimeWasted){
				maxTimeWasted = leftOvers.get(i);
				indexOfWorst = i;
			}
		}
		return indexOfWorst;
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

/*
Gifts in order:
Candylocks      Crayola Chemistry Set   Baby Shark Puppet       Glam Nails      Crayola Scrubbie Pet
Giant Mystery Egg       Barbie Dream Plane      Poppin Pals     Treasure Tomb   Linkimals

Items made on the first order:
68      17      68      68      51      85      51      17      34      17
Items needed on the first order:
10      5       16      13      21      44      18      34      29      40

Items made on the second order:
36      9       36      36      27      45      27      9       18      9
Items needed on the second order:
15      16      34      5       30      12      18      13      46      25

Items made on the third order:
40      10      40      40      30      50      30      10      20      10
Items needed on the third order:
20      46      39      19      10      32      18      34      46      10


Payment to each Elf per week:
0011    1001    0111    1000    0101
$300    $380    $375    $340    $735

Worst Elf by time wasted: 4
Total Hours Wasted by all elfs per day: 24
Press any key to continue . . .
*/
