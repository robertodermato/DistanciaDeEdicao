public class DistanciaDeEdicao {

    public static int editNaive = 0;
    public static int editDP = 0;

    public static void main(String[] args) {
        editNaive = 0;
        editDP = 0;

        String s = "Casablanca";
        String t = "Portentoso";

        s = "Maven, a Yiddish word meaning accumulator of knowledge, began as an attempt to " +
                "simplify the build processes in the Jakarta Turbine project. There were several" +
                " projects, each with their own Ant build files, that were all slightly different." +
                "JARs were checked into CVS. We wanted a standard way to build the projects, a clear "+
                "definition of what the project consisted of, an easy way to publish project information" +
                "and a way to share JARs across several projects. The result is a tool that can now be" +
                "used for building and managing any Java-based project. We hope that we have created " +
                "something that will make the day-to-day work of Java developers easier and generally help " +
                "with the comprehension of any Java-based project.";
        t = "This post is not about deep learning. But it could be might as well. This is the power of " +
                "kernels. They are universally applicable in any machine learning algorithm. Why you might" +
                "ask? I am going to try to answer this question in this article." +
                "Go to the profile of Marin Vlastelica Pogančić" +
                "Marin Vlastelica Pogančić Jun";
//
        int i = s.length()-1;
        int j = t.length()-1;

        //System.out.println("Naive --> Result: " + editDistanceNaive(s, t, i, j) + "; Number of operations: " + editNaive);
        System.out.println("Dinamic Programming --> Result: " + editDistanceDP(s,t) + "; Number of operations: " + editDP);

    }

    // S: String inicial, T: String final, i: [1..m], j:[1..n]
    // retorna o número mínimo de edições quando comparando
    // S[i] com T[j]. m é o tamanho de S, n o tamanho de T
    public static int editDistanceNaive (String s, String t, int i, int j){
        //Casos base
        if (s.length()==0 && t.length()==0) return 0;
        if (s.length()==0) return j;
        if (t.length()==0) return i;
        if (i==0) return j;
        if (j==0) return i;

        //Casos recursivos

        //foi match, não precisa fazer nada nesta posição, o custo é zero.
        if (s.charAt(i)==t.charAt(j)){
            editNaive++;
            return editDistanceNaive(s, t, i-1, j-1);
        }

        //Se não, três chamadas recursivas são necessárias:

        //Substituição
        editNaive++;
        int substituicao = editDistanceNaive(s, t, i-1, j-1) +1;

        //Inserção
        editNaive++;
        int insercao = editDistanceNaive(s, t, i, j-1) +1;

        //Remoção
        editNaive++;
        int remocao = editDistanceNaive(s, t, i-1, j) +1;

        // Retorne a que resultar em menor custo
        editNaive++;
        return Math.min(substituicao, (Math.min(insercao, remocao)));

    }

    public static int editDistanceDP (String A, String B){
        int m = A.length();
        int custoExtra = 0;
        int n = B.length();
        int matriz[][] = new int [m][n];

        for(int i = 1; i<m; i++){
            editDP++;
            matriz[i][0] = (matriz[i-1][0] + 1);
        }

        for(int j =1; j<n; j++){
            editDP++;
            matriz[0][j] = (matriz[0][j-1] + 1);
        }



        for(int i =1; i<m; i++){
            editDP++;
            for(int j =1; j<n; j++){
                editDP++;
                if(A.charAt(i) == B.charAt(j)){
                    custoExtra = 0;
                }else{
                    custoExtra = 1;
                }
                editDP++;
                matriz[i][j] = Math.min((matriz[i-1][j] +1), Math.min((matriz[i][j-1] +1), (matriz[i-1][j-1] + custoExtra)));
            }
        }
        editDP++;
        return matriz[m-1][n-1];
    }

}
