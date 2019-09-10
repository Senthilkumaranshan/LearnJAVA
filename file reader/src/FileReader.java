import java.io.*;


class FileRead {

    public static void main(String[] args) throws IOException {


//        readFile();
        writeFile();
//        readFileWithTry();
//        writeFileWithTry();
    }





    public static void readFile(){

        BufferedReader breader = null;

        String curLine;

        try{
           breader = new BufferedReader(new FileReader("/home/user/Documents/SK_Micro/untitled/src/readme.txt"));

           while((curLine = breader.readLine()) != null){

               System.out.println(curLine);
           }


           } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (breader != null)
                    breader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void readFileWithTry() throws IOException {

        String curLine;

           try(BufferedReader breader = new BufferedReader(new FileReader("/home/user/Documents/SK_Micro/untitled/src/readme.txt"))) {
               while ((curLine = breader.readLine()) != null) {

                   System.out.println(curLine);

               }
           }
    }

    public static void writeFile(){

        BufferedWriter bwritter = null;

        String line="";

        try{

            for(int i=0;i<10;i++){

                line +=i+1;

            }
            System.out.println(line);

            bwritter = new BufferedWriter(new FileWriter("/home/user/Documents/SK_Micro/untitled/src/writeMe.txt"));
            bwritter.write(line);

            System.out.println("File was successfully wrote");
            bwritter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeFileWithTry() throws IOException {

        String line="";
        for(int i=0;i<10;i++){

            line +=i+1;

        }
        System.out.println(line);

        try( BufferedWriter bwritter= new BufferedWriter(new FileWriter("/home/user/Documents/SK_Micro/untitled/src/writeMe.txt")))
        { bwritter.write(line);

            System.out.println("File was successfully wrote");

        }
    }






}
