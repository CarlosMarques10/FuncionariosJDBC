package application;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		char opcao, respostaMenu;
		
		do {
			System.out.println("---------------BEM-VINDO---------------");
			System.out.println("Escolha uma opção: ");
			System.out.println("1 = Adicionar um novo Empregado");
			System.out.println("2 = Adicionar um Departamento");
			System.out.println("3 = Mostrar lista de Empregados");
			System.out.println("4 = Mostrar lista de Departamentos");
			System.out.println("5 = Buscar Empregado pelo ID");
			System.out.println("6 = Buscar Empregado pelo departamento");
			opcao = scan.next().charAt(0);
			
			switch(opcao) {
			
			case 1:
				break;
				
			case 2:
				break;
				
			case 3:
				break;
				
			case 4:
				break;
				
			case 5:
				break;
				
			case 6:
				break;
				
			}
			
			
			
			System.out.println("Mostrar o menu novamente? (y/n)");
			respostaMenu = scan.next().charAt(0);
			
		} while(respostaMenu == 'y');
		
		

	}

}
