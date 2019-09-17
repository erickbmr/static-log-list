public class Logins {

    //criação do vetor de objetos (lista) e a variavel de controle
    private Login[] list = new Login[100];
    private int indexControl = 0;

    public void addEnd(Login user)  //adição de usuário no fim da lista
    {
        if(testFullList()) { createNew(); }

        this.list[this.indexControl] = user;
        this.indexControl++;
    }

    public void printList() //impressão da lista completa com usuarios e horario
    {
        //variavel de controle para ajudar na busca dos horarios do mesmo usuario
        int control = 0;

        for(int i = 0; i < this.indexControl; i++)
        {
            if (!searchUser(this.list[i].userName))
            {
                System.out.println(this.list[i].getUserName() + ": " + this.list[i].getLoginHr() + "h until " + this.list[i].getLogoutHr()
                        + "h");
            }
            else
            {
                if(!testRepetition(this.list[i].userName, control))
                {
                    System.out.print(this.list[i].getUserName() + ": ");
                    returnTime(this.list[i].userName);
                }
                else
                {
                    continue;
                }

            }
            control++;
        }
    }

    private boolean testRepetition(String user, int tamVet) //teste de repeticao de usuario em um determinado tamanho
    {
        for(int i = 0; i < tamVet; i++)
        {
            if(user.equals(this.list[i].userName))
            {
                return true;
            }
        }
        return false;
    }

    private void returnTime(String name)    //imprime o horario de algum usuário repetido
    {
        for(int i = 0; i < this.indexControl; i++)
        {
            if(name.equals(this.list[i].userName))
            {
                System.out.println(this.list[i].getLoginHr() + "h until " + this.list[i].getLogoutHr() + "h");
            }
        }
    }

    private boolean searchUser(String user) //verifica a repeticao de um usuario na lista toda
    {
        for(int i = 0; i < this.indexControl; i++)
        {
            if(this.list[i].userName.equals(user))
            {
                return true;
            }
        }
        return false;
    }

    private boolean testFullList()  //testa se a lista está cheia
    {
        return this.indexControl == this.list.length;
    }

    private void createNew()    //cria um novo objeto caso a lista esteja cheia
    {
        Login[] secondList = new Login[this.list.length * 2];
        for (int i = 0; i < this.indexControl; i++)
        {
            secondList[i] = this.list[i];
        }

        this.list = secondList;
    }

    public void printHourMin(int hour)  // imprime os usuarios que acessaram após o horario determinado
    {
        for(int i = 0; i < this.indexControl; i++)
        {
            int aux = this.list[i].getLoginHr();
            if(aux > hour)
            {
                System.out.println(this.list[i].getUserName());
            }
        }
    }

    public void showAccess(String user) //mostra os acessos de um usuário
    {
        for(int i = 0; i < this.indexControl; i++)
        {
            if(this.list[i].userName.equals(user))
            {
                System.out.println(this.list[i].loginHr + "h until " + this.list[i].logoutHr + "h");
            }
        }
    }

    //analisa se uma posicao existe
    private boolean analisaPosicao(int posicao) { return (posicao < this.indexControl) && (posicao >= 0); }

    private void removeItem(int posicao)    //remove um usuario a partir de uma posicao
    {
        if(analisaPosicao(posicao)){
            for(int i = posicao; i < this.indexControl; i++)
            {
                this.list[i] = this.list[i + 1];
            }
            this.indexControl--;
        }
        else {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    public void removeUser(int time)    //remove usuario com tempo de acesso menor que o determinado
    {
        for(int i = 0; i < this.indexControl; i++)
        {
            if(this.list[i].logTime < time)
            {
                removeItem(i);
            }
        }
    }

    public void descendingOrder()   //ordena os logs em ordem decrescente
    {
        Login temp = new Login();
        for(int i = 1; i < this.indexControl; i++)
        {
            for(int j = 0; j < i; j++)
            {
                if(this.list[i].logTime > this.list[j].logTime)
                {
                    temp = new Login();
                    temp = this.list[i];
                    this.list[i] = this.list[j];
                    this.list[j] = temp;
                }
            }
        }
    }
}
