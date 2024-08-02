package br.com.hiago640.estudo.splitwise;

import java.util.List;

import br.com.hiago640.estudo.splitwise.models.Despesa;
import br.com.hiago640.estudo.splitwise.models.EqualExpense;
import br.com.hiago640.estudo.splitwise.models.ExactExpense;
import br.com.hiago640.estudo.splitwise.models.ExpenseMetadata;
import br.com.hiago640.estudo.splitwise.models.ExpenseType;
import br.com.hiago640.estudo.splitwise.models.PercentExpense;
import br.com.hiago640.estudo.splitwise.models.PercentSplit;
import br.com.hiago640.estudo.splitwise.models.Split;
import br.com.hiago640.estudo.splitwise.models.User;

public class ExpenseService {

	public static Despesa createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits,
			ExpenseMetadata expenseMetadata) {

		switch (expenseType) {
		case EXACT:
			return new ExactExpense(amount, paidBy, splits, expenseMetadata);
		case PERCENT:
			for (Split split : splits) {
				PercentSplit percentSplit = (PercentSplit) split;
				split.setAmount((amount * percentSplit.getPercent()) / 100.0);
			}
			return new PercentExpense(amount, paidBy, splits, expenseMetadata);

		case EQUAL:
			int totalSplits = splits.size();
			double splitAmount = ((double) Math.round(amount * 100 / totalSplits)) / 100.0;
			for (Split split : splits) {
				split.setAmount(splitAmount);
			}

			/*
			 * primeiro devedor = quantidade + (valor total - quantidade * quantidade de
			 * devedores) Essa express�o calcula a diferen�a entre o valor total da despesa
			 * (amount) e o valor que foi dividido igualmente (splitAmount * totalSplits).
			 * 
			 * Se o valor total da despesa n�o for exatamente divis�vel pelo n�mero de
			 * pessoas, essa diferen�a � o "restante" que deve ser ajustado para garantir
			 * que a soma das contribui��es iguale o total da despesa.
			 */
			splits.get(0).setAmount(splitAmount + (amount - splitAmount * totalSplits));
			return new EqualExpense(amount, paidBy, splits, expenseMetadata);
		default:
			return null;
		}
	}
}