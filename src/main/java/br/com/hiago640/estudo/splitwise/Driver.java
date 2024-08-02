package br.com.hiago640.estudo.splitwise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.hiago640.estudo.splitwise.models.EqualSplit;
import br.com.hiago640.estudo.splitwise.models.ExpenseType;
import br.com.hiago640.estudo.splitwise.models.Split;
import br.com.hiago640.estudo.splitwise.models.User;

public class Driver {
	public static void main(String[] args) {

		DespesaManager expenseManager = new DespesaManager();

		expenseManager.addUser(new User("hia", "Hiago", "gaurav@workat.tech", "9876543210"));
		expenseManager.addUser(new User("lea", "Leandro", "sagar@workat.tech", "9876543210"));
		expenseManager.addUser(new User("tay", "Tayna", "hi@workat.tech", "9876543210"));
		expenseManager.addUser(new User("oto", "Otoniel", "mock-interviews@workat.tech", "9876543210"));

		Scanner scanner = new Scanner(System.in);
		while (true) {
			// EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
			String command = scanner.nextLine();
			String[] commands = command.split(" ");
			String commandType = commands[0];

			switch (commandType) {
			
			case "SHOW":
				if (commands.length == 1) {
					expenseManager.showBalances();
				} else {
					expenseManager.showBalance(commands[1]);
				}
				break;
				
			case "EXPENSE":
				
				String paidBy = commands[1];
				Double amount = Double.parseDouble(commands[2]);
				int noOfUsers = Integer.parseInt(commands[3]);
				String expenseType = commands[4 + noOfUsers];
				
				List<Split> splits = new ArrayList<>();
				
				switch (expenseType) {
				case "EQUAL":
					for (int i = 0; i < noOfUsers; i++) {
						splits.add(new EqualSplit(expenseManager.userMap.get(commands[4 + i])));
					}
					expenseManager.addDespesa(ExpenseType.EQUAL, amount, paidBy, splits, null);
					break;
				/*
				 * case "EXACT": for (int i = 0; i < noOfUsers; i++) { splits.add(new
				 * ExactSplit(expenseManager.userMap.get(commands[4 + i]),
				 * Double.parseDouble(commands[5 + noOfUsers + i]))); }
				 * expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits, null);
				 * break; case "PERCENT": for (int i = 0; i < noOfUsers; i++) { splits.add(new
				 * PercentSplit(expenseManager.userMap.get(commands[4 + i]),
				 * Double.parseDouble(commands[5 + noOfUsers + i]))); }
				 * expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits, null);
				 * break;
				 */
				}
				break;
			}
		}
	}
}