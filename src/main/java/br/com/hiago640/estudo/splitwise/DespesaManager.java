package br.com.hiago640.estudo.splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.hiago640.estudo.splitwise.models.Despesa;
import br.com.hiago640.estudo.splitwise.models.ExpenseMetadata;
import br.com.hiago640.estudo.splitwise.models.ExpenseType;
import br.com.hiago640.estudo.splitwise.models.Split;
import br.com.hiago640.estudo.splitwise.models.User;

public class DespesaManager {
	
	List<Despesa> expenses;
	Map<String, User> userMap;
	Map<String, Map<String, Double>> balanceSheet;

	public DespesaManager() {
		expenses = new ArrayList<>();
		userMap = new HashMap<>();
		balanceSheet = new HashMap<>();
	}

	public void addUser(User user) {
		userMap.put(user.getId(), user);
		balanceSheet.put(user.getId(), new HashMap<>());
	}

	public void addDespesa(ExpenseType expenseType, double amount, String comprador, List<Split> splits,
			ExpenseMetadata expenseMetadata) {

		Despesa expense = ExpenseService.createExpense(expenseType, amount, userMap.get(comprador), splits,
				expenseMetadata);
		expenses.add(expense);
		for (Split split : expense.getSplits()) {
			String devedor = split.getUser().getId();
			Map<String, Double> balances = balanceSheet.get(comprador);
			if (!balances.containsKey(devedor)) {
				balances.put(devedor, 0.0);
			}
			balances.put(devedor, balances.get(devedor) + split.getAmount());

			balances = balanceSheet.get(devedor);
			if (!balances.containsKey(comprador)) {
				balances.put(comprador, 0.0);
			}
			balances.put(comprador, balances.get(comprador) - split.getAmount());
		}
	}

	public void showBalance(String userId) {
		boolean isEmpty = true;
		for (Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()) {
			if (userBalance.getValue() != 0) {
				isEmpty = false;
				printBalance(userId, userBalance.getKey(), userBalance.getValue());
			}
		}

		if (isEmpty) {
			System.out.println("No balances");
		}
	}

	public void showBalances() {
		boolean isEmpty = true;
		for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
			for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
				if (userBalance.getValue() > 0) {
					isEmpty = false;
					printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
				}
			}
		}

		if (isEmpty) {
			System.out.println("No balances");
		}
	}

	private void printBalance(String user1, String user2, double amount) {
		String user1Name = userMap.get(user1).getName();
		String user2Name = userMap.get(user2).getName();
		if (amount < 0) {
			System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
		} else if (amount > 0) {
			System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
		}
	}
}
