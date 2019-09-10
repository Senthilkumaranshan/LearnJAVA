public class ArrayPrinter {

    public<E> void printArray(E[] array){

        for(E e: array){
            System.out.println(e);
        }
        System.out.println();
    }
}
