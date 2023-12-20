package br.com.hiago640.splitdumb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

class Person {
    String name;
    int balance;

    public Person(String name) {
        this.name = name;
        this.balance = 0;
    }
}

@TestInstance(Lifecycle.PER_CLASS)
class DivisaoEntrePessoasTest {

	static final int N = 4;
	private Person[] people = new Person[N];
	
	@BeforeAll
	void populaPessoas() {
        people[0] = new Person("Hiago");
        people[1] = new Person("Leandro");
        people[2] = new Person("Antônio");
        people[3] = new Person("Tayna");
	}
	
	@BeforeAll
	void populaSaldo() {
        // Definir os débitos e créditos
        people[0].balance -= 10;
        people[0].balance -= 30;
        people[0].balance -= 20;
        people[0].balance += 30;
        people[0].balance += 30;
        people[0].balance += 30;

        people[1].balance -= 30;
        people[1].balance -= 30;
        people[1].balance += 10;
        people[1].balance += 10;
        people[1].balance += 10;

        people[2].balance -= 30;
        people[2].balance -= 10;
        people[2].balance += 30;
        people[2].balance += 30;

        people[3].balance -= 30;
        people[3].balance -= 10;
        people[3].balance += 20;
	}
	
    // Função utilitária para encontrar o índice do valor mínimo em um array
    static int getMin(Person[] people) {
        int minInd = 0;
        for (int i = 1; i < N; i++)
            if (people[i].balance < people[minInd].balance)
                minInd = i;
        return minInd;
    }

    // Função utilitária para encontrar o índice do valor máximo em um array
    static int getMax(Person[] people) {
        int maxInd = 0;
        for (int i = 1; i < N; i++)
            if (people[i].balance > people[maxInd].balance)
                maxInd = i;
        return maxInd;
    }

    // Função utilitária para retornar o mínimo entre dois valores
    static int minOf2(int x, int y) {
        return (x < y) ? x : y;
    }

    // Função recursiva para calcular o fluxo de caixa mínimo
    static void minCashFlowRec(Person[] people) {
        // Encontrar os índices da pessoa que deve pagar o valor máximo (mxCredit)
        // e da pessoa que deve receber o valor máximo (mxDebit)
        int mxCredit = getMax(people), mxDebit = getMin(people);

        // Se ambos os valores são zero, todas as dívidas foram liquidadas
        if (people[mxCredit].balance == 0 && people[mxDebit].balance == 0)
            return;

        // Calcular o mínimo entre os dois valores e atualizar os saldos das pessoas
        int min = minOf2(-people[mxDebit].balance, people[mxCredit].balance);
        people[mxCredit].balance -= min;
        people[mxDebit].balance += min;

        // Imprimir os detalhes da transação
        System.out.println(people[mxDebit].name + " paga " + min + " para " + people[mxCredit].name);

        // Recursão para o array de pessoas
        minCashFlowRec(people);
    }

    // Função principal para encontrar o fluxo de caixa mínimo
    static void minCashFlow(Person[] people) {
        // Chamar a função recursiva para encontrar o fluxo de caixa mínimo
        minCashFlowRec(people);
    }
	
	@Test
	void test() {
        minCashFlow(people);
	}

}
