package program;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Aplication {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Employee> list = new ArrayList<>();
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		System.out.print("Enter salary: ");
		Double salary = sc.nextDouble();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			List<String> emails = list.stream()
								.filter(x -> x.getSalary() > salary)
								.map(x -> x.getEmail())
								.sorted((x,y) -> x.toUpperCase().compareTo(y.toUpperCase()))
								.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f" , salary) + ":");
			emails.forEach(x -> System.out.println(x));
			
			double sum = list.stream()
							.filter(x -> x.getName().charAt(0) == 'M')
							.map(x -> x.getSalary())
							.reduce(0.0, (x,y) -> x + y);							 
			
			System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		sc.close();
	}

}
