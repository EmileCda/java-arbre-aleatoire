
import java.util.ArrayList;

public class Main {

// ----------------------------------------------------------------------------
    public static void main(String[] args) {    
        System.out.print("\033\143");
    //    testRandom();
    //    testFeuille();
    //    testBranche();
       testArbre();
    
    }

// ----------------------------------------------------------------------------
    public static int testArbre() {
        
         Branche branch = new Branche(10);
        System.out.println(branch);
        System.out.println(branch.getWeight());
        System.out.printf("total leaves %d\n",branch.getTotalLeave());

        Arbre tree = new Arbre(branch);

        System.out.println(tree);
        System.out.printf("total w %.2f\n",tree.totalWeight());
        
        ;
        return 0;
    }
// ----------------------------------------------------------------------------
    public static int testBranche() {
        
        for (int index = 1 ; index <= 10 ; index ++){

            Branche branch = new Branche(index);
            System.out.printf("%d %s\n",index,branch);
        }
        return 0;
    }


// ----------------------------------------------------------------------------
    public static int testFeuille() {
        
        for (int index = 1 ; index <= 10 ; index ++){
            System.out.printf("%d %s\n",index,new Feuille());
        }
        return 0;
    }


// ----------------------------------------------------------------------------
    public static int testRandom() {
        
        for (int index = 0 ; index < 10 ; index ++){
            System.out.printf("%d %d\n",index,Utils.randInt(1,3));
        }
        return 0;
    }


}


interface  Weight {

    public  double  getWeight();
    public  void  setWeight(double value);
    

}

// 1.	Créer une classe Feuille 
class Feuille implements Weight{

// a.	Elle dispose d’un poids aléatoire entre 10 et 70 grammes
    private double weight ;
    public Feuille(){
        this.setWeight(Utils.randDouble(10,70));
    }

    public  void  setWeight(double value){ this.weight = value ;  }
    public  double  getWeight(){ return this.weight ; }
  
  
    public  String toString() {
        return String.format ("f:%.2fgr ",this.getWeight());
    }

}


// 2.	Créer une classe Branche
class Branche implements Weight{
// a.	Elle dispose d’une liste de Branches et de Feuilles, 

    private ArrayList<Branche> branchList ;
    private ArrayList<Feuille> leavesList ;
// ainsi que de son propre poids, aléatoire entre 250 grammes et 25 Kg
    private double branchWeight ;
    private double LeaveWeight ;
    private double totalWeight ;
    private int totalLeave ;

    public Branche(int destiny){
        this.branchList = new ArrayList<Branche>();
        this.leavesList = new ArrayList<Feuille>();
        this.setTotalLeave(0);
        this.setBranchWeight(0);
        this.setLeaveWeight(0);


        this.setBranchWeight(Utils.randInt(250, 25*1000)/1000.0);

        switch (destiny){
            case 1 :this.addLeave(5); break ;
            case 2 :this.addLeave(3); break ;
            case 9 :this.addBranch(1);
                    this.addLeave(2); 
                    break ;
            case 10 :this.addBranch(2);
                    this.addLeave(1); 
                    break ;
            default : break ; 
        }
        this.setWeight(this.getBranchWeight()+this.getLeaveWeight());
    }

    public Branche(){
    
        this (Utils.randInt(1,10));
    }


    public  void  setWeight(double value){ this.totalWeight = value ;  }
    public  double  getWeight(){ return this.totalWeight ; }

    public  int  getTotalLeave(){ return this.totalLeave ; }
    public  void  setTotalLeave(int value){  this.totalLeave = value ; }
    public  void  addTotalLeave(int value){  this.totalLeave += value ; }

    public  void  addBranchWeigt(double value){ this.branchWeight += value ;  }
    public  void  setBranchWeight(double value){ this.branchWeight = value ;  }
    public  double  getBranchWeight(){ return this.branchWeight ; }

    public  void  addLeaveWeight(double value){ this.LeaveWeight += value ;  }
    public  void  setLeaveWeight(double value){ this.LeaveWeight = value ;  }
    public  double  getLeaveWeight(){ return this.LeaveWeight ; }


    public  double  getTotalWeight(){ 
        return this.getWeight() + this.getBranchWeight() + this.getLeaveWeight()  ; 
        }

    public  void  addLeave(int count){ 
         
         for (int index = 0 ; index < count ; index ++){
            Feuille leave = new Feuille();
            this.addLeaveWeight(leave.getWeight());
            this.leavesList.add(leave);
            this.addTotalLeave(1);
         }
    }

    public  void  addBranch(int count){ 
         
         for (int index = 0 ; index < count ; index ++){

            Branche branch = new Branche(Utils.randInt(1,10));
            this.addBranchWeigt(branch.getTotalWeight());
            this.branchList.add(branch);
            this.addTotalLeave(branch.getTotalLeave());
         }
    }

   public  String toString() {

        String stringReturn = String.format ("B: %.2fkg->",this.getWeight());
        for (Branche branch  : branchList){
            stringReturn += branch.toString();
        }
        for (Feuille leave  : leavesList){
            stringReturn += leave.toString();
        }
 
        return stringReturn;

    }

}

// 3.	Créer une classe Arbre
class Arbre extends Branche{
    
    private Branche tronc;

    public Arbre(){
        this.setTronc(new Branche());
    }

    public Arbre(Branche branch){
        this.setTronc(branch);
    }


    public  void  setTronc(Branche value){ this.tronc = value ;  }
    public  Branche  getTronc(){ return this.tronc ; }


    public  double  totalWeight(){ 
        return this.getTronc().getWeight();
    }

 

    public  String toString() {
 

        return this.getTronc().toString();
    }

}

class Utils{


//-----------------------------------------------------------------------
// fonction random qui renvoie une fonction entre minValue et maxValue
// attention dans le cas de l'utilisation pour indexer un tableau (maxIndex = length)    
        // minvalue et maxValue inclus
    public static int randInt(int minValue, int maxValue) {
        return   (int)(Math.random() * (maxValue + 1-minValue) + minValue );  
    }


//-----------------------------------------------------------------------
// fonction random qui renvoie une fonction entre minValue et maxValue
// attention dans le cas de l'utilisation pour indexer un tableau (maxIndex = length)    
// minvalue et maxValue inclus
    public static double randDouble(int minValue, int maxValue) {
        return   (Math.random() * (maxValue + 1-minValue) + minValue );  
    }

}

/*
L’arbre aléatoire
Objectif : Simuler la pousse d’un arbre aléatoire
Implémentation
1.	Créer une classe Feuille 
a.	Elle dispose d’un poids aléatoire entre 10 et 70 grammes
2.	Créer une classe Branche
a.	Elle dispose d’une liste de Branches et de Feuilles, ainsi que de son propre poids, aléatoire entre 250 grammes et 25 Kg
b.	Lors de la construction, on effectue un jet aléatoire entre 1 et 10
i.	A 1, on lui ajoute 5 feuilles
ii.	A 2, on lui ajoute 3 feuilles
iii.	de 3 à 8 compris, la branche ne contient rien
iv.	A 9, la branche contient une branche et 2 feuilles
v.	A 10, la branche contient 2 branches et une feuille
3.	Créer une classe Arbre
a.	Lui ajouter un attribut de type Branche appelé tronc
b.	Lui ajouter une méthode permettant d’afficher le poids total de l’arbre
c.	Lui ajouter une méthode permettant de compter le nombre de feuilles
Questions
1.	Quel design-pattern utilise en grande partie l’arbre envers les classes Branche et Feuille ?
2.	Quel autre design pattern pourrait également convenir ?

*/