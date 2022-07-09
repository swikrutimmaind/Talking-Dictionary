public class dictNode {
    dictNode left, right;
    int h;
    String word;
    String mean;
    String type; //whether word is adjective/noun/verb
    String synonym;

    dictNode(String w, String m, String tp, String syn){
        word = w;
        mean = m;
        type = tp;
        synonym = syn;
        h = 0;
        left = right = null;
    }
}