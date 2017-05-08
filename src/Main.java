import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/*
* program will take in a list of body parts to work on and randomly assign at most 2 to one day, max 5 days.
* "Cardio" would be considered a day on it's own.
*/
public class Main {
    public static void main(String[] args) throws IOException {
        String []exerciseList=listParser(args[0]);
        ArrayList<ArrayList> Days=new ArrayList<ArrayList>();
        
        for(int i=0;i<5;i++){
            Days.add(DaysCreator(exerciseList));
        }
        
        for(int j=0;j<5;j++){
            System.out.println("These are the following exercises to do for Day "+(j+1)+":");
                System.out.println(Days.get(j));
        }
    }
    
    /*
     * method DaysCreator
     * @param exerciseList- choice of exercises
     * Takes an array of exercises and randomly picks 2 exercises and adds them to an array.
     * Cardio counts as 2 exercises so weight training and cardio do not mix into 1 day.
     * Returns and arrayList of exercises.
     */
    private static ArrayList<String> DaysCreator(String[] exerciseList) {
        ArrayList<String> aDay=new ArrayList<String>();
        int exerciseCount=0;
        
        while(exerciseCount!=2){
            int RandomNum=ThreadLocalRandom.current().nextInt(0,8);
            
            if(exerciseList[RandomNum]!=null){
                String exercise=exerciseList[RandomNum];
                
                if(!exercise.equals("Cardio")){
                    aDay.add(exerciseList[RandomNum]);
                    exerciseList[RandomNum]=null;
                    exerciseCount++;
                }
                else if(exercise.equals("Cardio")&& exerciseCount==0){
                    aDay.add(exerciseList[RandomNum]);
                    exerciseList[RandomNum]=null;
                    exerciseCount=2;
                }
            }
        }
        return aDay;
    }
    
    /*
     * method listParser
     * @param FileName- name of the file containing a list of exercises
     * Method takes the file and parses it into an Array.
     * returns an array of exercises 
     */
    private static String[] listParser(String FileName) throws IOException {
        Scanner reader = null;
        long count = Files.lines(Paths.get(FileName)).count();
        String []exerciseList=new String[(int) count];
        
        try{
            File file = new File(FileName);
            reader= new Scanner(file);
        }
        
        catch(Exception e){
            System.out.println("Could not open file");
        }
        
        int i=0;
        
        while(reader.hasNextLine()){
            String line = reader.nextLine();
            exerciseList[i]=line;
            i++;
        }
        
        reader.close();
        return exerciseList;
    }
}