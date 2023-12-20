package br.com.hiago640.splitdumb;

import java.util.Vector;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

class Person {
	String name;
	double balance;

	public Person(String name) {
		this.name = name;
		this.balance = 0;
	}
}

@TestInstance(Lifecycle.PER_CLASS)
class DivisaoEntrePessoasTest {

	static final int N = 4;
	private Vector<Person> people = new Vector<>();

	@BeforeAll
	void populaPessoas() {
		people.add(new Person("Hiago"));
		people.add(new Person("Leandro"));
		people.add(new Person("Antônio"));
		people.add(new Person("Tayna"));
	}

	@BeforeAll
	void populaSaldo() {
		// Definir os débitos e créditos
		people.get(0).balance -= 10;
		people.get(0).balance -= 30;
		people.get(0).balance -= 20;
		people.get(0).balance += 30;
		people.get(0).balance += 30;
		people.get(0).balance += 30;

		people.get(1).balance -= 30;
		people.get(1).balance -= 30;
		people.get(1).balance += 10;
		people.get(1).balance += 10;
		people.get(1).balance += 10;

		people.get(2).balance -= 30;
		people.get(2).balance -= 10;
		people.get(2).balance += 30;
		people.get(2).balance += 30;

		people.get(3).balance -= 30;
		people.get(3).balance -= 10;
		people.get(3).balance += 20;
	}

	// Função utilitária para encontrar o índice do valor mínimo em um array
	static Person getMin(Vector<Person> people) {
		return people.stream().min((p1, p2) -> Double.compare(p1.balance, p2.balance)).orElse(null);
	}

	// Função utilitária para encontrar o índice do valor máximo em um array
	static Person getMax(Vector<Person> people) {
		return people.stream().max((p1, p2) -> Double.compare(p1.balance, p2.balance)).orElse(null);
	}

	// Função utilitária para retornar o mínimo entre dois valores
	static double minOf2(double x, double y) {
		return (x < y) ? x : y;
	}

	// Função recursiva para calcular o fluxo de caixa mínimo
	static void minCashFlowRec(Vector<Person> people) {
		// Encontrar os índices da pessoa que deve pagar o valor máximo (mxCredit)
		// e da pessoa que deve receber o valor máximo (mxDebit)
		Person personWithMaxCredit = getMax(people);
		Person personWithMaxDebit = getMin(people);

		// Se ambos os valores são zero, todas as dívidas foram liquidadas
		if (personWithMaxCredit.balance == 0 && personWithMaxDebit.balance == 0)
			return;

		// Calcular o mínimo entre os dois valores e atualizar os saldos das pessoas
		double min = minOf2(-personWithMaxDebit.balance, personWithMaxCredit.balance);
		personWithMaxCredit.balance -= min;
		personWithMaxDebit.balance += min;

		// Imprimir os detalhes da transação
		System.out.println(personWithMaxDebit.name + " paga " + min + " para " + personWithMaxCredit.name);

		// Recursão para o array de pessoas
		minCashFlowRec(people);
	}

	// Função principal para encontrar o fluxo de caixa mínimo
	static void minCashFlow(Vector<Person> people) {
		// Chamar a função recursiva para encontrar o fluxo de caixa mínimo
		minCashFlowRec(people);
	}

	@Test
	void test() {
		minCashFlow(people);
	}

}
