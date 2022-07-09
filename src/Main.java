import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        basedict bd = new basedict();

        System.out.println("===================================================================================================================================\n");
        System.out.println();

        int choice,ch,op,op1,op2;
        String actualPassword = "ImOwner";
        String password;
        do {
            System.out.print("You want to login as owner or user? (owner - 1, user - 2) : ");
            ch = sc.nextInt();
            System.out.println();
            switch(ch) {
                case 1 :
                    System.out.print("Enter password : ");
                    password = sc.next();

                    System.out.println();

                    if(password.equals(actualPassword))
                    {
                        do {
                            System.out.println("*** Menu ***");
                            System.out.println("1. Add new word to dictionary");
                            System.out.println("2. Update existing word in dictionary");
                            System.out.println("3. Display all words in dictionary");
                            System.out.println("4. Search word in dictionary");
                            System.out.print("\nEnter choice : ");
                            op1=sc.nextInt();
                            switch(op1)
                            {
                                case 1:
                                    bd.d.Create();
                                    break;
                                case 2:
                                    bd.d.update();
                                    break;
                                case 3 :
                                    bd.d.Display();
                                    break;
                                case 4:
                                    bd.d.search();
                                    break;
                            }
                            System.out.println("\n===================================================================================================================================\n");
                            System.out.println("Do owner want to perform any operation again? (0/1) :  ");
                            op=sc.nextInt();
                        }while(op==1);
                    }
                    else
                        System.out.println("Password is wrong !!!");
                    break;
                case 2 :
                    do {
                        System.out.println("*** Menu ***");
                        System.out.println("1. Display all words in dictionary");
                        System.out.println("2. Search word in dictionary");
                        System.out.print("\nEnter choice : ");
                        choice=sc.nextInt();
                        System.out.println();

                        switch(choice) {
                            case 1 : bd.d.Display();
                                break;
                            case 2 : bd.d.search();
                                break;
                        }
                        System.out.println("\n===================================================================================================================================\n");
                        System.out.println("Do user want to perform any operation again? (0/1) :  ");
                        op=sc.nextInt();
                    }while(op==1);
                    break;
            }
            System.out.println("\n===================================================================================================================================\n");
            System.out.println("Want to perform more operations as a user or owner? (0/1) : ?");
            op2=sc.nextInt();
            System.out.println("\n===================================================================================================================================\n");
        }while(op2==1);
    }
}