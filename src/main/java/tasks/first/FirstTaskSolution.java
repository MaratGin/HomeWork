package tasks.first;

import java.util.ArrayDeque;
import java.util.Deque;

public class FirstTaskSolution implements FirstTask {

    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        String a="";

        Deque dd= new ArrayDeque();
        int k=0;
        boolean flag=false;
        String answer="";
        int isUsed[]=new int[adjacencyMatrix.length];
        Deque deque= new ArrayDeque();
        deque.addFirst(startIndex);




        while (k!=isUsed.length){
            startIndex=(int) deque.pollFirst();
            addToIsUsed(isUsed,startIndex,deque);
            answer=addToAnswer(answer,startIndex,dd);
            //addToAnswer(answer,indexStart,dd);
            findNeighboors(adjacencyMatrix,startIndex,isUsed,deque,false);
            k++;

        }
        a=finalAnswer(answer);
        return a;
    }


    void findNeighboors(boolean [] [] adjacencyMatrix,int index,int[] isUsed, Deque deque,boolean flag){
        for (int i = 0; i <adjacencyMatrix.length ; i++) {
            if((adjacencyMatrix[index][i]==true)){
                for (int j = 0; j <isUsed.length ; j++) {
                    if (i==(isUsed[j]-1)){
                        flag=true;
                    }

                }
                if (flag==false){
                    if (deque.contains(i)){

                    } else {
                        deque.add(i);
                    }
                }
                flag=false;
            }
        }

    }
    String addToAnswer(String answer,int indexStart,Deque dd){
        dd.add(indexStart);
        answer=answer.concat(String.valueOf(indexStart));
        return answer;

    }
    void addToIsUsed(int[] isUsed, int a, Deque deque){

        for (int i = 0; i < isUsed.length ; i++) {
            if (isUsed[i]==0){
                isUsed[i]=a+1;
                break;
            }

        }
    }

    public String finalAnswer(String answer){
        String a="";
        char [] arr = answer.toCharArray();
        for (int i = 0; i < arr.length-1; i++) {
            a=a.concat(String.valueOf(arr[i]));
            a=a.concat(",");
        }
        a=a.concat(String.valueOf(arr[arr.length-1]));
        return a;
    }






    @Override
    public Boolean validateBrackets(String s) {
        String openBrackets = "({[";
        String closeBrackets = ")}]";
        char[] array;
        array = s.toCharArray();

        boolean flag = true;
        Deque deque = new ArrayDeque();

        for (int i = 0; i < array.length; i++) {
            if ((array[i] == '(') || (array[i] == '{') || (array[i] == '[')) {
                deque.addLast(array[i]);
                System.out.println(deque);
            } else if (array[i]==')'){
                //char k= (char) deque.getLast();
                if (deque.isEmpty()||(((char)deque.getLast())!='(')){
                    return false;
                } else if (((char) deque.getLast())=='('){
                    deque.removeLast();
                }
            } else if (array[i]==']') {
                if (deque.isEmpty() || (((char) deque.getLast()) != '[')) {
                    return false;
                } else if (((char) deque.getLast())=='['){
                    deque.removeLast();
                }
            } else if (array[i]=='}'){
                if (deque.isEmpty()||(((char)deque.getLast())!='{')){
                    return false;

                } else if (((char) deque.getLast())=='{'){
                    deque.removeLast();
                }
            }
        }
        return deque.isEmpty();

    }

    @Override
    public Long polishCalculation(String s) {
        long a=0;
        boolean flag=true;
        int i=0;
        String operations="+-*/";
        String[] array = s.split("\\s");
        long answer=0;
        boolean ready=false;

        Deque deque = new ArrayDeque();

        while (!ready) {
            while (i<array.length) {
                if (!operations.contains(array[i])) {
                    a = Long.parseLong(array[i]);
                    deque.addLast(a);
                    //int k= (int) deque.getFirst()+3;
                } else {
                    break;
                }
                i++;
            }

            if (i!= array.length) {
                long second= (long) deque.pollLast();
                long first= (long) deque.pollLast();
                if (array[i].equals("+")){
                    deque.addLast(first+second);
                } else  if (array[i].equals("-")){
                    deque.addLast(first-second);
                } else if (array[i].equals("*")){
                    deque.addLast(first*second);
                } else if (array[i].equals("/")){
                    deque.addLast(first/second);
                }


            } else {
                ready=true;
            }

            i++;

        }
        answer=(long) deque.pollLast();

        return answer;
    }
}
