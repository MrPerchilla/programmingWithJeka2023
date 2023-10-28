import java.util.Scanner;

public class WeightliftingCalculator {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------");
		System.out.println("Welcome to WeightlifitingCalculator!");
		System.out.println("-----------------------------------------");
		System.out.print("To start enter a num of exercises to count: ");
		int exercises = scanner.nextInt();
		System.out.println("-----------------------------------------");
		int i = 0;
		double totalWeight = 0;
		while (i < exercises) {

			System.out.print("|Exercise " + (i + 1) + "|" + " Sets: ");
			int numSets = scanner.nextInt();

			for (int j = 1; j <= numSets; j++) {

				System.out.print("|Exercise " + (i + 1) + "|" + " Weight #" + j + ": ");
				double weight = scanner.nextDouble();
				System.out.print("|Exercise " + (i + 1) + "|" + " How many reps #" + j + ": ");
				int reps = scanner.nextInt();

				double setWeight = weight * reps;

				totalWeight += setWeight;
				System.out.println("------------------------------------");
			}
			i++;

		}
		System.out.println("Total weight " + totalWeight + " kg");
	}

}
