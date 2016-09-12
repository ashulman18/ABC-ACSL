//Andrea Shulman
//3/4/16
//ACSL3
//import java.util.Arrays;
import java.util.*; 
public class Abc
{
   private String filled;
   private String[] grid2, grid3;
   private String[][] endArr,adder;
   private boolean A,B,C;
   private int nullCount;
   
   public Abc(String str)
   {
      grid2=new String[36];
      grid3=new String[16];
      endArr=new String[4][4];
      for(int i=0;i<4;i++){
         grid2[Integer.parseInt(str.substring(0,str.indexOf(",")))-1]="+";
         str=str.substring(str.indexOf(",")+1);
      }
      int size=Integer.parseInt(str.substring(0,str.indexOf(",")));
      str=str.substring(str.indexOf(",")+1);
      int commas = 0;
      for(int i = 0; i < str.length(); i++) 
         if(str.charAt(i) == ',') commas++;
      String holdLetter="";
      int holdPos=0;
      for(int i=0;i<commas-1;i+=2){
         holdLetter=str.substring(0,str.indexOf(","));
         str=str.substring(str.indexOf(",")+1);
         holdPos=Integer.parseInt(str.substring(0,str.indexOf(",")))-1;
         grid2[holdPos]=holdLetter;
         str=str.substring(str.indexOf(",")+1);
      }
      holdLetter=str.substring(0,str.indexOf(","));
      str=str.substring(str.indexOf(",")+1);
      holdPos=Integer.parseInt(str)-1;
      grid2[holdPos]=holdLetter;
      fillEmUp();
      fillGrid3();
      boolean theresANull=false;
      
       for(int y=0;y<4;y++)
         for(int b=0;b<4;b++)
         {
            if(endArr[y][b]==null)
               theresANull=true;           
            else
               theresANull=false;
         }
      if(theresANull){
         adder=endArr;
         endArr=adder;
      }
        
         for(int y=0;y<4;y++)
         for(int b=0;b<4;b++)
            if(!(endArr[y][b].equals("+")))
               System.out.print(endArr[y][b]);
      System.out.print("\n");
   }

   public void fillGrid3()
   {
      int num=0;
      for(int j=7;j<31;j+=6)
         for(int i=j;i<j+4;i++){
            grid3[num]=grid2[i];
            num++;
         }
      String array2d[][] = new String[4][4];
      for(int i=0; i<4;i++)
         for(int j=0;j<4;j++)
            array2d[i][j] = grid3[(j*4) + i];              
      solveIt(array2d);
   }
   
   public void solveIt(String[][] arr)
   {
      int nullbro=1;
      int l=0;
      while(l<4){
      nullbro=0;
      for(int z=0;z<arr.length;z++)
         for(int h=0;h<arr[z].length;h++)
            if(arr[z][h]==null)
               nullbro++;
      
      if(nullbro==0)
      {
         endArr=arr;
         return;
      }
      l++;
      for(int j=0;j<4;j++)
      {
         nullCount=0; 
         A=false;
         B=false;
         C=false;
         for(int i=0;i<4;i++)
         {
            if(arr[j][i]==null)
               nullCount=i;
            else if(arr[j][i].equals("A"))
               A=true;
            else if(arr[j][i].equals("B"))
               B=true;
            else if(arr[j][i].equals("C"))
               C=true;
         }
         if(A&&B&&!C)
            arr[j][nullCount]="C";
         else if(A&&C&&!B)
            arr[j][nullCount]="B";
         else if(B&&C&&!A)
            arr[j][nullCount]="A";  
      }    
      for(int i=0;i<4;i++)
      {
         nullCount=0; 
         A=false;
         B=false;
         C=false;
         for(int j=0;j<4;j++)
         {
            if(arr[j][i]==null)
               nullCount=i;
            else if(arr[j][i].equals("A")){
               A=true;}
            else if(arr[j][i].equals("B"))
               B=true;
            else if(arr[j][i].equals("C"))
               C=true;
         }
         if(A&&B&&!C)
            arr[nullCount][i]="C";
         else if(A&&C&&!B)
            arr[nullCount][i]="B";
         else if(B&&C&&!A)
            arr[nullCount][i]="A";}}
      adder=arr;
      please();
      please();
      please();
      arr=adder;
      for(int d=0;d<4;d++)
         for(int q=0;q<4;q++)
            if(adder[d][q]!=null)
               endArr[q][d]=adder[d][q];
      
      adder=endArr;
      please();
      please();
   }
   
   public boolean contains(ArrayList<String> arr, String item)
   {
      for (int n=0;n<arr.size();n++)
         if (item.equals(arr.get(n)))
            return true;
      return false;
   }
   
   
   public void please()
   {
      for(int y=0;y<4;y++){
         ArrayList<String> possibl = new ArrayList<String>(Arrays.asList("A", "B","C","+"));
         ArrayList<String> possiblCol = new ArrayList<String>(Arrays.asList("A", "B","C","+"));
         ArrayList<Integer> nullPoint=new ArrayList<Integer>();
         for(int b=0;b<4;b++)
         {
            if(adder[y][b]==null)
               nullPoint.add(b);
            else if(adder[y][b].equals("A"))
               possibl.remove(possibl.indexOf("A"));
            else if(adder[y][b].equals("B"))
               possibl.remove(possibl.indexOf("B"));
            else if(adder[y][b].equals("C"))
               possibl.remove(possibl.indexOf("C"));
            else if(adder[y][b].equals("+"))
               possibl.remove(possibl.indexOf("+"));
         }
         if(possibl.size()==1)
         {
            adder[y][nullPoint.get(0)]=possibl.get(0);
         }
         else if(possibl.size()==2){
            for(int p=0;p<4;p++){
               if(adder[p][nullPoint.get(0)]==null)
                  nullPoint.add(p);
               else if(adder[p][nullPoint.get(0)].equals("A"))
                  possiblCol.remove(possiblCol.indexOf("A"));
               else if(adder[p][nullPoint.get(0)].equals("B"))
                  possiblCol.remove(possiblCol.indexOf("B"));
               else if(adder[p][nullPoint.get(0)].equals("C"))
                  possiblCol.remove(possiblCol.indexOf("C"));
               else if(adder[p][nullPoint.get(0)].equals("+"))
                  possiblCol.remove(possiblCol.indexOf("+"));
            }
            if(contains(possibl,"A")&&contains(possiblCol,"A"))
               adder[y][nullPoint.get(0)]="A";
            else if((contains(possiblCol,"B"))&&(contains(possibl,"B")))
               adder[y][nullPoint.get(0)]="B";
            else if(contains(possibl,"C")&&contains(possiblCol,"C"))
               adder[y][nullPoint.get(0)]="C";
            else if(contains(possibl,"+")&&contains(possiblCol,"+"))
               adder[y][nullPoint.get(0)]="+"; 
         }
       }

   }
   
   
   public void manip(String[][]arr){
      
      boolean aRow=false;
      boolean bRow=false;
      boolean cRow=false;
      boolean aCol=false;
      boolean bCol=false;
      boolean cCol=false;
      int nullRow=5;
      int nullCol=5;
      for(int i=0;i<arr.length;i++){
         for(int z=0;z<4;z++){
            if(arr[i][z]==null)
               nullRow=i;
            else if(arr[i][z].equals("A"))
               aCol=true;
            else if(arr[i][z].equals("B"))
               bCol=true;
            else if(arr[i][z].equals("C"))
               cCol=true;}
      }
      for(int j=0;j<arr[0].length&&nullRow<arr[0].length;j++){
         if(arr[nullRow][j]==null)
            nullCol=j;
         else if(arr[nullRow][j].equals("A"))
            aRow=true;
         else if(arr[nullRow][j].equals("B"))
            bRow=true;
         else if(arr[nullRow][j].equals("C"))
            cRow=true;
      }
      if(nullCol<arr.length){
         if(aCol&&!aRow)
            arr[nullRow][nullCol]="A";
         if(cCol&&!cRow)
            arr[nullRow][nullCol]="C";
         if(bCol&&!bRow)
            arr[nullRow][nullCol]="B";
         manip(arr);
       }
   }
   
   public void fillEmUp()
   {
      String[] temp=grid2;
      for(int i=1;i<5;i++)
         if(temp[i]!=null)
            if(grid2[i+6]==null)
               grid2[i+6]=temp[i];
            else
               grid2[i+7]=temp[i];   
      for(int i=6;i<27;i+=6)
         if(temp[i]!=null)
            if(grid2[i+1]==null)
               grid2[i+1]=temp[i];
            else
               grid2[i+2]=temp[i];
      for(int i=11;i<27;i+=6)
         if(temp[i]!=null)
            if(grid2[i-1]==null)
               grid2[i-1]=temp[i];
            else
               grid2[i-2]=temp[i];
      for(int i=31;i<36;i++)
         if(temp[i]!=null)
            if(grid2[i-6]==null)
               grid2[i-6]=temp[i];
            else
               grid2[i-12]=temp[i]; 
   }

   public static void main(String[]args)
   {
      /*Abc test1=new Abc("9,17,22,26,4,A,7,C,18,C,19,C,32");
      Abc test2=new Abc("11,16,20,27,4,A,7,B,19,A,24,B,30");
      //Abc test3=new Abc("9,14,23,28,3,B,7,C,25,A,30");
      //Abc test4=new Abc("8,15,23,28,4,A,7,C,24,C,33,A,30");
      Abc test5=new Abc("9,16,23,26,4,A,7,B,19,B,25,B,18");*/
      
      /*Abc test1=new Abc("10,17,20,27,3,A,7,C,18,B,30");
      //Abc test2=new Abc("20,27,10,17,3,A,25,B,12,A,18");
      //Abc test3=new Abc("8,16,21,29,3,B,19,B,4,A,30");
      Abc test4=new Abc("10,15,23,26,3,B,25,B,4,A,30");
      Abc test5=new Abc("10,15,20,29,4,A,13,B,32,C,30,B,24");*/
   }
}