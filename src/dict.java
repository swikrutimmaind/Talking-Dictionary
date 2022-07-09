import java.util.*;

public class dict{
    Scanner myObj = new Scanner(System.in);
    private dictNode root;
    Speech s = new Speech();

    //default constructor
    dict(){
        root = null;
    }

    //******//height, if only 1 root, then height = 0
    int height(dictNode root) {
        int lh, rh;
        if(root == null)
            return 0;

        if(root.left == null)
            lh = 0;
        else
            lh = 1 + root.left.h;

        if(root.right == null)
            rh = 0;
        else
            rh = 1 + root.right.h;

        if(lh>rh)
            return lh;
        else
            return rh;
    }

    //*******//balance factor
    int BalanceFactor(dictNode root) {
        int bf, lh, rh;
        if(root == null)
            return 0;

        if(root.left == null)
            lh = 0;
        else
            lh = 1+height(root.left);

        if(root.right == null)
            rh = 0;
        else
            rh = 1+height(root.right);

        bf = lh-rh;

        return bf;
    }


    //********//left-left rotation
    dictNode LL(dictNode temp) {
        dictNode ptr = temp.left;
        temp.left = ptr.right;
        ptr.right = temp;

        ptr.h = height(ptr);
        temp.h = height(temp);

        return ptr;
    }


    //********//right-right rotation
    dictNode RR(dictNode temp) {
        dictNode ptr = temp.right;
        temp.right = ptr.left;
        ptr.left = temp;

        ptr.h = height(ptr);
        temp.h = height(temp);

        return ptr;
    }


    //*******//left-right rotation
    dictNode LR(dictNode temp) {
        temp.left = RR(temp.left);
        temp = LL(temp);

        return temp;
    }


    //*******//right-Left rotation
    dictNode RL(dictNode temp) {
        temp.right = LL(temp.right);
        temp = RR(temp);

        return temp;
    }


    //*****//insert//*******//
    dictNode insert(dictNode root, dictNode temp) {

        //when u have empty tree
        if(root == null) {
            root = temp;
            //root = new dictionary.dictNode(temp.word, temp.mean, temp.type, temp.synonym);
            return root;
        }

        //when temp node name < current root node name
        if(temp.word.compareToIgnoreCase(root.word) < 0) {

            root.left = insert(root.left, temp);
            int bal = BalanceFactor(root);

            //rotation required
            if(bal == 2) {

                if(temp.word.compareToIgnoreCase(root.left.word) < 0) {
                    root = LL(root);
                }
                else
                    root = LR(root);
            }
        }

        //when temp node name > current root node name
        else if(temp.word.compareToIgnoreCase(root.word) > 0) {

            root.right = insert(root.right, temp);
            int bal = BalanceFactor(root);

            //rotation required
            if(bal == -2) {

                if(temp.word.compareToIgnoreCase(root.right.word) > 0) {
                    root = RR(root);
                }
                else
                    root = RL(root);
            }
        }

        root.h = height(root);
        return(root);
    }

    public void create(String w, String m, String tp, String syn) {
        dictNode nd = new dictNode(w, m, tp, syn);
        root = insert(this.root, nd);
    }

    //*****//create********//
    public void Create() {

        System.out.print("\nEnter word : ");
        String wr1 = myObj.next();

        System.out.println();

        System.out.print("Enter meaning : ");
        myObj.next();
        String mn1 = myObj.nextLine();
        System.out.println();

        System.out.print("Enter whether it is adjective / adverb / noun / verb / pronoun / preposition : ");
        String tpe = myObj.next();
        System.out.println();

        System.out.print("Enter synonym : ");
        String syno = myObj.next();
        System.out.println();

        dictNode temp = new dictNode(wr1, mn1, tpe, syno);
        root = insert(this.root, temp);
    }


    //******//in order display//******//
    public void recIn(dictNode locRoot) {

        if(locRoot!=null){
            recIn(locRoot.left);
            System.out.println("Word \t\t: " + locRoot.word + "\n" + "Meaning \t: " + locRoot.mean + "\n" + "Parts of Speech : " + locRoot.type + "\n" + "Synonym \t: " + locRoot.synonym + "\n");
            recIn(locRoot.right);
        }
        else
            return;
    }

    public void Display() {
        System.out.println("\n************ DICTIONARY ************\n");
        recIn(root);
    }

    public void display(dictNode ptr) {
        System.out.println("Word : "+ptr.word);
        System.out.println("Meaning : "+ptr.mean);
        System.out.println("Parts of speech : "+ptr.type);
        System.out.println("Synonym : "+ptr.synonym);
        System.out.println();
    }


    //******//search//*********//
    void search() {
        System.out.print("Which word you want to search : ");
        String word = myObj.next();

        int f = 0;
        dictNode ptr;
        ptr = root;

        while(ptr!=null) {

            if(word.compareToIgnoreCase(ptr.word) == 0) {
                f = 1;
                System.out.println("\nWord Found !! ");
                System.out.println("Word \t\t: "+ptr.word);
                System.out.println("Meaning\t\t: "+ptr.mean);
                System.out.println("Parts of speech : "+ptr.type);
                System.out.println("Synonym\t\t: "+ptr.synonym);
                //********************
                s.voice(ptr.word);

                break;
                //********************
            }

            else if(word.compareToIgnoreCase(ptr.word) < 0) {
                ptr = ptr.left;
            }

            else
                ptr = ptr.right;
        }

        if(f == 0)
            System.out.println("\n" + word + " is not found in the dictionary");
    }



    //******//update//*******//
    void update() {
        int f = 0;

        System.out.println("Which word you want update : ");
        String word = myObj.next();

        System.out.println();

        int choice;
        System.out.println("What you want to update : ");
        System.out.println("1. Meaning");
        System.out.println("2. Part of speech");
        System.out.println("3. Synonym");
        System.out.print("Enter choice : ");
        choice = myObj.nextInt();

        System.out.println();

        dictNode ptr;
        ptr = root;

        if (choice == 1) {
            System.out.println("Enter meaning : ");
            myObj.next();
            String updm = myObj.nextLine();
            System.out.println();

            while (ptr != null) {

                if (word.compareToIgnoreCase(ptr.word) == 0) {
                    System.out.println("Previous Word - ");
                    display(ptr);
                    f = 1;
                    ptr.mean = updm;
                    System.out.println("Updated word - ");
                    display(ptr);
                    return;
                } else if (word.compareToIgnoreCase(ptr.word) < 0) {
                    ptr = ptr.left;
                } else
                    ptr = ptr.right;
            }

            if (f == 0) {
                System.out.println(" " + word + " is not in dictionary!!");
            }
        } else if (choice == 2) {
            f = 0;
            ptr = root;

            System.out.println("Enter type : ");
            String updt = myObj.next();
            System.out.println();

            while (ptr != null) {

                if (word.compareToIgnoreCase(ptr.word) == 0) {
                    f = 1;
                    System.out.println("Previous Word - ");
                    display(ptr);
                    ptr.type = updt;
                    System.out.println("Updated word - ");
                    display(ptr);
                    return;
                } else if (word.compareToIgnoreCase(ptr.word) < 0) {
                    ptr = ptr.left;
                } else
                    ptr = ptr.right;
            }

            if (f == 0) {
                System.out.println(" " + word + " is not in dictionary!!");
            }
        } else if (choice == 3) {
            f = 0;
            ptr = root;

            System.out.println("Enter synonym : ");
            String upds = myObj.next();
            System.out.println();

            while (ptr != null) {

                if (word.compareToIgnoreCase(ptr.word) == 0) {
                    f = 1;
                    System.out.println("Previous Word");
                    display(ptr);
                    ptr.synonym = upds;
                    System.out.println("Updated word : ");
                    display(ptr);
                    return;
                } else if (word.compareToIgnoreCase(ptr.word) < 0) {
                    ptr = ptr.left;
                } else
                    ptr = ptr.right;
            }

            if (f == 0) {
                System.out.println(" " + word + " is not in dictionary!!");
            }
        }
    }
}