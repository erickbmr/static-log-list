import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        //criação dos objetos
        Logins staticList = new Logins();
        Login tempUser = new Login();

        //atribuição das variaveis de leitura e do nome do arquivo
        String line = " ", fileName = "testLogins.txt";

        //atribuição das classes para leitura automática do arquivo
        FileReader file = new FileReader(fileName);
        BufferedReader readFile = new BufferedReader(file);

        //tratamento da ocorrência de erros na leitura (try/catch)
        try
        {
            while (!(line.equals("fim")))
            {
                line = readFile.readLine();
                if (!(line.equals("fim")))
                {
                    //instanciação do objeto a cada leitura e troca de caracter para ser mais preciso na leitura
                    tempUser = new Login();
                    line = line.replace("\t", ",");
                    String[] data = line.split(",");
                    tempUser.setUserName(data[0]);
                    tempUser.setLoginHr(Integer.parseInt(data[1]));
                    tempUser.setLogoutHr(Integer.parseInt(data[2]));
                    tempUser.setLogTime();

                    //adição do usuario no final da lista
                    staticList.addEnd(tempUser);
                }
                else
                {
                    break;
                }
            }
            //fechamento da classe de leitura para melhor uso de memória
            readFile.close();
        }
        catch (IOException e)
        {
            //evita a quebra de execução do programa
            System.out.println("Error");
            e.printStackTrace();
        }

        //criação e atribuição das variáveis do loop principal e de informações requeridas no mesmo
        int option = 0;
        int timeAdm, timeRemove;
        String userAdm;

        while(option != 1) {

            //menu principal
            System.out.println("\n\n[1] Sair\n[2] Mostra logs\n[3] Determina Horario");
            System.out.println("[4] Todos logins de um usuario\n[5] Remove usuario\n[6] Ordena usuarios");

            option = input.nextInt();

            switch (option) {
                case 1:
                    //para sair do programa
                    break;
                case 2:
                    //para imprimir a lista de logins com usuários repetidos
                    staticList.printList();
                    break;
                case 3:
                    //para mostrar usuários que fizeram login a partir de um horario
                    System.out.print("Insira um horario para mostrar os usuarios que fizeram login: ");
                    timeAdm = input.nextInt();
                    staticList.printHourMin(timeAdm);
                    break;
                case 4:
                    //para mostrar os acessos de um unico usuario
                    System.out.print("Insira um usuario para ver seus logins: ");
                    userAdm = input.next();
                    staticList.showAccess(userAdm);
                    break;
                case 5:
                    //para remover usuarios com tempo de acesso menor que o determinado
                    System.out.print("Insira um horario minimo para permanencia nos logins: ");
                    timeRemove = input.nextInt();
                    staticList.removeUser(timeRemove);
                    break;
                case 6:
                    //para ordenar os usuarios em ordem decrescente de tempo de acesso
                    System.out.println("Ordenando usuarios em ordem decrescente...");
                    staticList.descendingOrder();
                    break;
                default:
                    System.out.println("Error");
                    break;
            }

        }
        //fechamento do scanner para melhor gerenciamento de memória
        input.close();
    }
}
